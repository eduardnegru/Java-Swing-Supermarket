import java.util.ArrayList;
import java.util.TreeMap;

public class ProductMap {

    public static TreeMap<Integer, Item> hashMap = null;

    public static TreeMap<Integer, Item> getHashMap(){
            TreeMap<Integer, Item> hashMap = new TreeMap<>();
            ArrayList<Department> arrayList = Store.getInstance().getDepartments();
            for (int i = 0; i < arrayList.size(); i++) {
                ArrayList<Item> items = arrayList.get(i).getItems();
                for (int j = 0; j < items.size(); j++) {
                    hashMap.put(items.get(j).getID(), items.get(j));
                }
            }

        return hashMap;
    }

    public static TreeMap<Integer, Item> getInstance(){
        if(hashMap != null)
            return hashMap;
        hashMap = getHashMap();
        return hashMap;
    }
}

