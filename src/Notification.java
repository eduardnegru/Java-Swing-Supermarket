import java.util.Date;

/**
 * Created by AdrianEduard on 12/18/2016.
 */
public class Notification {

    private Date date = new Date();
    private Integer departmentID;
    private Integer itemID;
    private NotificationType type;
    public enum NotificationType {
        ADD,
        REMOVE,
        MODIFY,
    }

    public Notification(NotificationType type, Integer departmentID, Integer itemID){
        this.type = type;
        this.departmentID = departmentID;
        this.itemID = itemID;
     }

    public Date getDate() {
        return date;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public NotificationType getType() {
        return type;
    }

    public String toString(){

        String s = "";
        s += getType() + ";" + getItemID() + ";" + getDepartmentID() ;
        return  s;
    }
}
