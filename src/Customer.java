import java.util.ArrayList;

/**
 * Created by AdrianEduard on 12/18/2016.
 */

public class Customer implements Observer {

    private static final String shopping = "ShoppingCart";
    private static final String wish = "WishList";
    private static final String book= "BookDepartment";
    private static final String video = "VideoDepartment";
    private static final String software = "SoftwareDepartment";
    private static final String music = "MusicDepartment";
    private String name;
    private double budget;
    private ShoppingCart shoppingCart;
    private WishList wishList;
    private ArrayList<Notification> notifications = new ArrayList<>();

    public Customer(String name, double budget) {
        this.name = name;
        this.budget = budget;
        shoppingCart =  new ShoppingCart(budget);
        wishList = new WishList();
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean emptyWishList(){
        return wishList.isEmpty();
    }

    public boolean notInWishList(Integer departmentID){

        for (int i = 0; i < wishList.size(); i++){
            if(wishList.getItem(i).getDepartmentID().equals(departmentID))
                return true;
        }

        return false;
    }

    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }

    public void getNewShoppingCart(){
        shoppingCart = new ShoppingCart(getShoppingCart().getBudget());
    }

    public WishList getWishList(){
        return wishList;
    }

    public double getShoppingCartDTotal(){

        return shoppingCart.getTotalPrice();
    }

    public ArrayList<Item> getItems(String identifier){
        if(identifier.equals(shopping))
            return getShoppingCart().getItems();

        return getWishList().getItems();
    }
    public double getWishListTotal(){

        return wishList.getTotalPrice();
    }

    public double getTotal(String identifier){
        if(identifier.equals(shopping))
            return getShoppingCartDTotal();
        return getWishListTotal();
    }


    public void accept(Integer deptID){

        String name = Store.getInstance().getDepartment(deptID).getDepartmentName();
        switch (name){

            case book:
                Department bookDepartment = new DepartmentBuilder().departmentName(DepartmentBuilder.book).setID(deptID).build(DepartmentBuilder.book);
                bookDepartment.accept(getShoppingCart());
                break;

            case music:
                Department musicDepartment = new DepartmentBuilder().departmentName(DepartmentBuilder.music).setID(deptID).build(DepartmentBuilder.music);
                musicDepartment.accept(getShoppingCart());
                break;

            case software:
                Department softwareDepartment = new DepartmentBuilder().departmentName(DepartmentBuilder.software).setID(deptID).build(DepartmentBuilder.software);
                softwareDepartment.accept(getShoppingCart());
                break;

            case video:
                Department videoDepartment = new DepartmentBuilder().departmentName(DepartmentBuilder.video).setID(deptID).build(DepartmentBuilder.video);
                videoDepartment.accept(getShoppingCart());
                break;

            default:
                break;
        }
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public Department getDepartmentFromProductID(Integer ID){

        ArrayList<Department> departments = Store.getInstance().getDepartments();
        for (Department d : departments){
            if(d.containsProduct(ID))
                return d;
        }

        return null;    // no depts found to have this id;
    }

    public void addItem(Integer ID, String identifier){

        if(identifier.equals(shopping)){

            if(ProductMap.getInstance().get(ID).getPrice() < shoppingCart.getBudget()) {

                Integer depID = ProductMap.getInstance().get(ID).getDepartmentID();
                shoppingCart.add(ProductMap.getInstance().get(ID));

                boolean hasDepartment = false;

                if (wishList.getItem(ID) != null) {   // daca adaug in shopping cart un element existent in wishlist

                    wishList.remove(wishList.getItem(ID));

                    for (int i = 0; i < wishList.size(); i++)
                        if(wishList.getItem(i).getDepartmentID().equals(depID))
                            hasDepartment = true;

                    if (!hasDepartment){
                        Store.getInstance().getDepartment(depID).removeObserver(this);

                    }

                }


            }
        }
        else if(identifier.equals(wish)){
            wishList.add(ProductMap.getInstance().get(ID));
            Department d = getDepartmentFromProductID(ID);
            d.addObserver(this);
        }

    }

    public void delItem(Integer ID, String identifier){

        boolean ok = false;
        boolean hasDepartment = false;

        if(identifier.equals(shopping)){
            for (int j = 0; j < shoppingCart.size(); j++)
                 if(shoppingCart.getItem(j).getID().equals(ID)) {
                    double money = shoppingCart.getBudget();
                    shoppingCart.setBudget(money + shoppingCart.getItem(j).getPrice());
                     Integer deptID = shoppingCart.getItem(j).getDepartmentID();
                     shoppingCart.remove(shoppingCart.getItem(j));
                     for (int k = 0; k < shoppingCart.size(); k++){
                        if (shoppingCart.getItem(k).getDepartmentID().equals(deptID)) {
                            hasDepartment = true;
                            break;
                        }
                    }

                    if (!hasDepartment)
                        Store.getInstance().getDepartment(deptID).getClients().remove(this);

                    break;
                }

         }
        else if(identifier.equals(wish)){

            Integer departmentID = wishList.getItem(ID).getDepartmentID();
            for (int k = 0; k < wishList.size(); k++)
                if(wishList.getItem(k).getID().equals(ID))
                    wishList.remove(wishList.getItem(k));

            for (int i = 0; i < wishList.size(); i++)
                if(wishList.getItem(i).getDepartmentID().equals(departmentID)) {
                    ok = true;
                    break;
                }

            if(!ok){    // daca nu mai exista in wishlist un produs din dep asta il sterg de la observers.
                Department d = getDepartmentFromProductID(ID);
                if(Store.getInstance().getDepartment(departmentID).getObservers().contains(this))
                    d.removeObserver(this);
            }

        }

    }

    @Override
    public void update(Notification notification) {

        notifications.add(notification);
        Notification.NotificationType type  = notification.getType();

        switch (type){

            case ADD:

                break;

            case MODIFY:

                if(shoppingCart.contains(notification.getItemID())) {
                    Item itemToModify = shoppingCart.getItem(notification.getItemID());
                    int index = shoppingCart.indexOf(itemToModify.getID());
                    double oldPrice = shoppingCart.getItem(index).getPrice();
                    double newPrice = ProductMap.getInstance().get(notification.getItemID()).getPrice();
                    shoppingCart.setItemPrice(index, newPrice);
                    double differece = oldPrice - newPrice;
                    budget += differece;
                    shoppingCart.setBudget(budget);
                    shoppingCart.sort("price");
                }

                if(wishList.contains(notification.getItemID())){

                    Item itemToModify = wishList.getItem(notification.getItemID());
                    int index = wishList.indexOf(itemToModify.getID());
                    double newPrice = ProductMap.getInstance().get(notification.getItemID()).getPrice();
                    wishList.setItemPrice(index, newPrice);
                }
                break;

            case REMOVE:

                if(shoppingCart.contains(notification.getItemID())){

                    Item itemToDelete = shoppingCart.getItem(notification.getItemID());
                    int index = shoppingCart.indexOf(itemToDelete.getID());
                    double oldPrice = shoppingCart.getItem(index).getPrice();
                    budget += oldPrice;
                    shoppingCart.setBudget(budget);
                    shoppingCart.remove(itemToDelete);

                }

                if(wishList.contains(notification.getItemID())){

                    Item itemToDelete = wishList.getItem(notification.getItemID());
                    wishList.remove(itemToDelete);
                    Integer deptID = itemToDelete.getDepartmentID();

                    boolean ok = false;

                    ArrayList<Item> items = wishList.getItems();
                    for (int i = 0; i < items.size(); i++)
                        if (items.get(i).getDepartmentID().equals(deptID))
                            ok = true;  // daca mai exista itemuri din departamentul respectiv clientul ramane observator

                    if (!ok)
                        if(Store.getInstance().getDepartment(deptID).getObservers().contains(this))
                            Store.getInstance().getDepartment(deptID).removeObserver(this);

                }

                break;

            default:
                break;


        }
    }

}