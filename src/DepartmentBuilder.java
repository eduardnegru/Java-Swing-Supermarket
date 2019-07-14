/**
 * Created by AdrianEduard on 12/21/2016.
 */

public class DepartmentBuilder {

    public static final String book = "BookDepartment";
    public static final String music = "MusicDepartment";
    public static final String software = "SoftwareDepartment";
    public static final String video = "VideoDepartment";

    private String departmentName;
    private Integer ID;

    public DepartmentBuilder departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public DepartmentBuilder setID(Integer ID) {
        this.ID = ID;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Integer getID() {
        return ID;
    }

    public Department build(String identifier){

        switch (identifier){
            case music:
                return new MusicDepartment(this);
            case book:
                return new BookDepartment(this);
            case software:
                return new SoftwareDepartment(this);
            case video:
                return new VideoDepartment(this);
            default:
                break;
        }

        return null;
    }


}

