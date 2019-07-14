import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class StrategyA implements Strategy{

    @Override
    public Item execute(WishList wishList){

        ArrayList<Item> clone = wishList.getItems();

        Collections.sort(clone, new ItemList.ItemComparator());

        return  clone.get(0);
    }
}

class StrategyB implements Strategy{

    @Override
    public Item execute(WishList wishList) {
        ArrayList<Item> items = wishList.getItems();
        ArrayList<Item> clone = items;
        Collections.sort(clone, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

         return  clone.get(0);
    }
}

class StrategyC implements Strategy{

    @Override
    public Item execute(WishList wishList) {
        ArrayList<Item> clone = wishList.getItems();

        Collections.sort(clone, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o2.getIndex() - o1.getIndex();
            }
        });

        Item item = clone.get(0);
        return item;
    }
}
