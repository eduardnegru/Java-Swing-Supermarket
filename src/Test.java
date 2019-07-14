import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;


public class Test extends JFrame{

    private static final String storePath = "store.txt";
    private static final String customersPath = "customers.txt";
    private static final String eventsPath = "events.txt";
    private static final int nrDepartments = 4;
    private static TreeMap<Integer, Item> allProducts;

    public static HashMap<String, Customer> customers;
    public static Store store;
     private int ok = 0;
    private boolean alreadySortedAlpha = false;

    public static void main(String args[]){

        Gui gui = new Gui();
        gui.setVisible(true);
        
    }



    public static Store readStore(String filePath){

        Store store = Store.getInstance();
        FileReader fr = null;
        LineNumberReader lnr = null;
        String buffer;
        HashMap<String, Department> hashMap = new HashMap<>();   // initializez hashMap-ul
        Integer departmentID;

        hashMap.put("MusicDepartment", new DepartmentBuilder().departmentName(DepartmentBuilder.music).setID(1).build(DepartmentBuilder.music));
        hashMap.put("BookDepartment", new DepartmentBuilder().departmentName(DepartmentBuilder.book).setID(1).build(DepartmentBuilder.book));
        hashMap.put("SoftwareDepartment", new DepartmentBuilder().departmentName(DepartmentBuilder.software).setID(1).build(DepartmentBuilder.software));
        hashMap.put("VideoDepartment", new DepartmentBuilder().departmentName(DepartmentBuilder.video).setID(1).build(DepartmentBuilder.video));

        try {

            fr = new FileReader(filePath);
            lnr = new LineNumberReader(fr);

            buffer = lnr.readLine();
            store.setName(buffer);

            for(int i = 0; i < nrDepartments; i++){

                buffer = lnr.readLine();
                String []s = buffer.split(";"); //  we get the name and id of a dept
                Department department = hashMap.get(s[0]);
                departmentID = Integer.parseInt(s[1]);
                department.setID(departmentID);
                store.addDepartment(department);
                buffer = lnr.readLine();
                int size = Integer.parseInt(buffer);

                for (int j = 0; j < size; j++){
                    buffer = lnr.readLine();
                    s = buffer.split(";");
                    department.addItem(new Item(s[0], Integer.parseInt(s[1]), Double.parseDouble(s[2]), departmentID));
                }

            }

        } catch (IOException e){

            e.printStackTrace();

        } finally {

            try {

                if (lnr != null)
                    lnr.close();

                if (fr != null)
                    fr.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return store;
    }

    public static HashMap<String, Customer> readCustormers(String filePath){

        FileReader fr = null;
        LineNumberReader lnr = null;
        String buffer;
        HashMap<String, Customer> customers = null;
        HashMap<String,Strategy> strategyMap = new HashMap<>();
        strategyMap.put("A", new StrategyA());
        strategyMap.put("B", new StrategyB());
        strategyMap.put("C", new StrategyC());

        try {

            fr = new FileReader(filePath);
            lnr = new LineNumberReader(fr);

            buffer = lnr.readLine();
            int size = Integer.parseInt(buffer);
            customers = new HashMap<>(size);

            for(int i = 0; i < size; i++){

                buffer = lnr.readLine();
                String []s = buffer.split(";"); //  we get the name and id of a dept
                customers.put(s[0], new Customer(s[0], Double.parseDouble(s[1])));
                customers.get(s[0]).getWishList().setStrategy(strategyMap.get(s[2]));

            }

        } catch (IOException e){

            e.printStackTrace();

        } finally {

            try {

                if (lnr != null)
                    lnr.close();

                if (fr != null)
                    fr.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return customers;
    }

    public static void readEvents(String filePath, HashMap<String, Customer> customers){

        FileReader fr = null;
        LineNumberReader lnr = null;
        String buffer;
        Integer deptID;
        Integer itemID;
        double price;
        int k = 0 ;
        try {

            fr = new FileReader(filePath);
            lnr = new LineNumberReader(fr);
            buffer = lnr.readLine();
            int size = Integer.parseInt(buffer);

            for(int i = 0; i < size; i++){

                buffer = lnr.readLine();

                String []s = buffer.split(";");

                switch (s[0]){

                    case "addItem":
                          customers.get(s[3]).addItem(Integer.parseInt(s[1]), s[2]);
                        break;
                    case "delItem":
                        customers.get(s[3]).delItem(Integer.parseInt(s[1]), s[2]);
                        break;
                    case "addProduct":
                        deptID = Integer.parseInt(s[1]);
                        itemID = Integer.parseInt(s[2]);
                        price = Double.parseDouble(s[3]);
                        Store.getInstance().addProduct(deptID, new Item(s[4], itemID, price, deptID));
                        break;
                    case "modifyProduct":
                        deptID = Integer.parseInt(s[1]);
                        itemID = Integer.parseInt(s[2]);
                        price = Double.parseDouble(s[3]);
                        Store.getInstance().modifyProduct(deptID, itemID, price);
                        break;
                    case "delProduct":
                        itemID = Integer.parseInt(s[1]);
                        Store.getInstance().delProduct(itemID);
                        break;
                    case "getItem":
                        Item item = customers.get(s[1]).getWishList().executeStrategy();
                        customers.get(s[1]).delItem(item.getID(), "WishList");
                        boolean ok = false;
                        ArrayList<Item> wish = new ArrayList<>(customers.get(s[1]).getItems("WishList"));
                        for (Item it : wish){
                            if (it.getDepartmentID().equals(item.getDepartmentID())) {
                                ok = true;
                                break;
                            }
                        }
                        if (!ok){
                            if ( Store.getInstance().getDepartment(item.getDepartmentID()).getObservers().contains(customers.get(s[1])))
                                Store.getInstance().getDepartment(item.getDepartmentID()).removeObserver(customers.get(s[1]));
                        }

                        customers.get(s[1]).getShoppingCart().add(item);
                        System.out.println(item);
                        k++;
                        break;
                    case "getItems":

                        ArrayList<Item> items = customers.get(s[2]).getItems(s[1]);
                        System.out.println(items);
                        k++;
                        break;
                    case "getTotal":
                        double total = customers.get(s[2]).getTotal(s[1]);
                        System.out.printf("%.2f\n",total);
                        k++;
                        break;
                    case "accept":
                        deptID = Integer.parseInt(s[1]);
                        customers.get(s[2]).accept(deptID);
                        break;
                    case "getObservers":
                        deptID = Integer.parseInt(s[1]);
                        ArrayList<Customer> observers = Store.getInstance().getDepartment(deptID).getObservers();
                        System.out.println(observers);
                        k++;
                        break;
                    case "getNotifications":
                        ArrayList<Notification> notifications = customers.get(s[1]).getNotifications();
                        System.out.println(notifications);
                        k++;
                        break;
                    default:
                        break;

                }


            }

        } catch (IOException e){

            e.printStackTrace();

        } finally {

            try {

                if (lnr != null)
                    lnr.close();

                if (fr != null)
                    fr.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        }




    }

}
