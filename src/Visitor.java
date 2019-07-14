/**
 * Created by AdrianEduard on 12/18/2016.
 */

public interface Visitor {
    void visit(BookDepartment bookDepartment);
    void visit(MusicDepartment musicDepartment);
    void visit(SoftwareDepartment softwareDepartment);
    void visit(VideoDepartment videoDepartment);
}
