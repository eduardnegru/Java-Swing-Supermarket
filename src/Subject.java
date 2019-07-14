/**
 * Created by AdrianEduard on 12/18/2016.
 */

public interface Subject{

    static void addObserver(Customer customer){};
    static void removeObserver(Customer customer){};
    static void notifyAllObservers(Notification notification){};

}
