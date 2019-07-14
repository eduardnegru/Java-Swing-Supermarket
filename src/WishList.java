public class WishList extends ItemList {

    Strategy strategy;
    private static Integer index = 0;

    @Override
    public boolean add(Item element) {
        Integer i = WishList.index + 1;
        element.setIndex(i);
        WishList.index = i;
        super.add(element);
        this.sort("name");
        return true;
    }

    @Override
    public boolean remove(Item item) {
        if(!isEmpty()) {
            super.remove(item);
            return true;
        }
        return false;
    }

    public Item executeStrategy(){
        return strategy.execute(this);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static Integer getIndex() {
        return index;
    }

    public static void setIndex(Integer index) {
        WishList.index = index;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
