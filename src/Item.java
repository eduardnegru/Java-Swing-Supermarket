import java.text.DecimalFormat;

/**
 * Created by AdrianEduard on 12/18/2016.
 */
public class Item {

    String name;
    Integer ID;
    double price;
    Integer index;
    Integer departmentID;

    public Item() {
    }

    public Item(String name, Integer ID, double price, Integer departmentID) {
        this.name = name;
        this.ID = ID;
        this.price = price;
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String toString(){

        String s = "";
        s += getName() + ";" + getID() + ";" +  String.format("%.2f",getPrice());
        return s;
    }
}
