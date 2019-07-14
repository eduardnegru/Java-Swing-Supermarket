import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by AdrianEduard on 12/18/2016.
 */
public class Store {

    private static Store store = null;
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Department> departments = new ArrayList<>();

    public static Store getInstance(){

        if(store == null)
            store = new Store();

        return store;
    }

    public void enter(Customer c){
        customers.add(c);
    }

    public void exit(Customer c){
        customers.remove(c);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department d){
        departments.add(d);
    }

    public void resetDepartments(){
        departments = new ArrayList<>();
    }

    public Department getDepartment(Integer ID){

        for (int i = 0; i < departments.size(); i++){
            if(departments.get(i).getID().equals(ID))
                return departments.get(i);

        }

        return null;    // no department with this id
    }


    public void addProduct(Integer departmentID, Item item){
        getDepartment(departmentID).addItem(item);
        ProductMap.getInstance().put(item.getID(),item);
        getDepartment(departmentID).notifyAllObservers(new Notification(Notification.NotificationType.ADD, departmentID, item.getID()));
    }

    public void modifyProduct(Integer departmentID, Integer ID, double price){

        getDepartment(departmentID).getItem(ID).setPrice(price);    // modific in store
        ProductMap.getInstance().get(ID).setPrice(price);
        getDepartment(departmentID).notifyAllObservers(new Notification(Notification.NotificationType.MODIFY, departmentID, ID));
    }

    public void delProduct(Integer ID){

        Item item = ProductMap.getInstance().get(ID);
        ProductMap.getInstance().remove(ID);
        Integer departmentID = item.getDepartmentID();
        getDepartment(departmentID).removeItem(ID);
        getDepartment(departmentID).notifyAllObservers(new Notification(Notification.NotificationType.REMOVE, departmentID, ID));

    }

}
