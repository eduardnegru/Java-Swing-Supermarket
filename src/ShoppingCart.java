import java.util.ArrayList;
import java.util.Collections;


public class ShoppingCart extends ItemList implements Visitor {

    private double budget;

    public ShoppingCart(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public boolean add(Item element) {

        Item cloneItem = new Item(element.getName(), element.getID(), element.getPrice(), element.getDepartmentID());
        if (budget >= element.getPrice()) {
            super.add(cloneItem);
            budget -= element.getPrice();
            return true;
        }

        return false;
    }

    @Override
    public void visit(BookDepartment bookDepartment) {
        for(int i = 0; i < size(); i++){
            if(getItem(i).getDepartmentID().equals(bookDepartment.getID())) {
                double price = getItem(i).getPrice();
                price -= 0.1 * price;
                setItemPrice(i, price);

            }
        }
        sort("price");
    }

    @Override
    public void visit(MusicDepartment musicDepartment) {
        double totalPrice = 0;

        for (int i = 0; i < size(); i++)
            if(getItem(i).getDepartmentID().equals(musicDepartment.getID()))
                totalPrice += getItem(i).getPrice();
        sort("price");
        budget += 0.1 * totalPrice;
    }

    @Override
    public void visit(SoftwareDepartment softwareDepartment) {
        double minPrice = Double.MAX_VALUE;
        int size = size();
        double price;

        ArrayList<Item> items = Store.getInstance().getDepartment(softwareDepartment.getID()).getItems();

        for (int i = 0; i < items.size(); i++)
            if(items.get(i).getPrice() < minPrice)
                minPrice = items.get(i).getPrice();


        if (budget < minPrice) {

            for (int i = 0; i < size; i++) {
                if(getItem(i).getDepartmentID().equals(softwareDepartment.getID())) {
                    Item item = getItem(i);
                    price = item.getPrice();
                    price -= 0.2 * price;
                    item.setPrice(price);
                }
            }
            sort("price");
        }
    }

    @Override
    public void visit(VideoDepartment videoDepartment) {

        double maxPrice = 0;
        int size = size();
        double price;
        double priceBefore = 0;

        for (int i = 0; i < this.size(); i++)
            if(getItem(i).getDepartmentID().equals(videoDepartment.getID()))
                priceBefore += getItem(i).getPrice();   // pretul produselor din shopping cart care sunt si in videodepartment

        ArrayList<Item> items = Store.getInstance().getDepartment(videoDepartment.getID()).getItems();
        for (int i = 0; i < items.size(); i++)
            if(items.get(i).getPrice() > maxPrice)
                maxPrice = items.get(i).getPrice(); // maximul produselor din videodepartment

        if(priceBefore > maxPrice) {

            for (int i = 0; i < size; i++) {
                if(getItem(i).departmentID.equals(videoDepartment.getID())) {
                    Item item = getItem(i);
                    price = item.getPrice();
                    price -= 0.15 * price;
                    item.setPrice(price);
                }
            }

            sort("price");
        }

        budget += 0.05 * priceBefore;
     }
}
