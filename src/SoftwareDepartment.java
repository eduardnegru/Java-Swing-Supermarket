/**
 * Created by AdrianEduard on 12/18/2016.
 * Visitable
 */

public class SoftwareDepartment extends Department {

    private String departmentName;
    private Integer ID;

    public SoftwareDepartment(DepartmentBuilder builder) {
        this.departmentName = builder.getDepartmentName();
        this.ID = builder.getID();
    }

    public SoftwareDepartment(Integer ID) {
        setID(ID);
        setDepartmentName(this.getClass().toString().substring(6));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public Integer getID() {
        return ID;
    }

}
