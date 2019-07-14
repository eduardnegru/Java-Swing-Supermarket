import java.util.ArrayList;

public abstract class Department extends Store implements Subject{

    private String departmentName;
    private Integer ID;
    private ArrayList<Item> products = new ArrayList<>();
    private ArrayList<Customer> observers = new ArrayList<>();
    private ArrayList<Customer> clients = new ArrayList<>();

    public void addItem(Item item){
        products.add(item);

    }

    public void removeItem(Integer ID){

        for (int i = 0; i < products.size(); i++){
            if(products.get(i).getID().equals(ID))
                products.remove(i);
        }

    }
    public boolean containsProduct(Integer ID){

        if(products.contains(ProductMap.getInstance().get(ID)))
            return true;

        return false;

    }

    public Item getItem(Integer ID){
        for (int i = 0; i < products.size(); i++)
            if (products.get(i).getID().equals(ID))
                return products.get(i);
        return null;
    }

    public void enter(Customer c){
        if(!clients.contains(c)){
            clients.add(c);
        }
     }

    public void exit(Customer c){
        if(clients.contains(c)){
            clients.remove(c);
        }
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ArrayList<Item> getItems() {
        return products;
    }

    public void setID(Integer ID){
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }


    public void addObserver(Customer customer){
        if(!observers.contains(customer))
            observers.add(customer);
    }

    public void removeObserver(Customer c){

            int index = observers.indexOf(c);
            observers.remove(index);
    }

    public void removeAllObservers(){
        for (int i = 0; i < observers.size(); i++)
            observers.remove(i);
    }

    public ArrayList<Customer> getObservers() {
        return observers;
    }

    public ArrayList<Customer> getClients() {
        return clients;
    }

    public void notifyAllObservers(Notification notification){

        ArrayList<Customer> obs = new ArrayList<>(observers);

        for(int i = 0; i < obs.size(); i++) {

            obs.get(i).update(notification);
        }

    }

    public abstract void accept(Visitor visitor);


}


