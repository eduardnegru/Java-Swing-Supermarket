import java.util.*;

/**
 * Created by AdrianEduard on 12/18/2016.
 */

public abstract class ItemList {

    private Node<Item> head = null;
    private Node<Item> tail = null;

    static class Node<Item> {

        private Node<Item> next;
        private Node<Item> previous;
        private Item item;

        Node(Node<Item> previous, Node<Item> next, Item item) {
            this.previous = previous;
            this.next = next;
            this.item = item;
        }
    }

    static class ItemComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Item i1 = (Item) o1;
            Item i2 = (Item) o2;
            if(i1.getPrice() == i2.getPrice())
                return i1.getName().compareTo(i2.getName());
            return i1.getPrice() < i2.getPrice() ? -1: 1;
        }
    }
    static class NameComparator implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            Item one = (Item)o1;
            Item two = (Item)o2;
            return one.getName().compareTo(two.getName());
        }
    }
    static class descItemComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Item i1 = (Item) o1;
            Item i2 = (Item) o2;
            if(i1.getPrice() == i2.getPrice())
                return -i1.getName().compareTo(i2.getName());
            return i1.getPrice() < i2.getPrice() ? 1: -1;
        }
    }
    static class descNameComparator implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            Item one = (Item)o1;
            Item two = (Item)o2;
            return -one.getName().compareTo(two.getName());
        }
    }

    class ItemIterator<Item> implements ListIterator<Item>{

        Node<Item> currentNext = (Node<Item>) getHead();
        Node<Item> currentPrev = (Node<Item>) getTail();


        @Override
        public boolean hasNext() {
            if(currentNext != null)
                return true;

            return false;
        }

        public Item get(){
            return currentNext.item;
        }

        @Override
        public Item next() {
            Item item = currentNext.item;
            currentNext = currentNext.next;
            return item;
        }

        @Override
        public boolean hasPrevious() {
            if(currentPrev != null)
                return true;
            return false;
        }

        @Override
        public Item previous() {
            Item item = currentPrev.item;
            currentPrev = currentPrev.previous;
            return item;
        }

        @Override
        public int nextIndex() {
            Item item = currentNext.next.item;
            Node<Item> aux = (Node<Item>) getHead();

            int i = 0;

            while (aux != null){
                aux = aux.next;
                i++;
            }
            return i;
        }

        @Override
        public int previousIndex() {
            Item item = currentPrev.previous.item;
            Node<Item> aux = (Node<Item>) getTail();

            int i = 0;

            while (aux != null){
                aux = aux.previous;
                i++;
            }
            return i;
        }

        @Override
        public void remove() {

            if (currentNext.equals(head)) {
                currentNext.next = (Node<Item>) head.next;
                return;
            }

            if (currentNext.next.equals(null)){
                if (currentNext.previous != null){
                    currentNext = currentNext.previous;
                    currentNext.next = null;
                    return;
                }
                currentNext = null;
                return;
            }

            Node<Item> aux = currentNext.next;
            currentNext = currentNext.previous;
            aux.previous = currentNext;
            currentNext.next = aux;

        }

        @Override
        public void set(Object o) {
             currentNext.item = (Item)o;
        }

        @Override
        public void add(Object o) {
            currentNext.next = new Node<>(currentNext,null,(Item)o);

        }
    }

    public String toString(){

        String s = "";
        Node<Item> aux = head;

        while (aux != null) {
            s += aux.item.getName() + " " + aux.item.getPrice() + "\n";
            aux = aux.next;
        }

        return s;
    }

    public String reverseToString(){

        String s = "";
        Node<Item> aux = tail;

        while (aux != null) {
            s += aux.item.getName() + " " + aux.item.getPrice() + "\n";
            aux = aux.previous;
        }

        return s;

    }


    public boolean add(Item element){

        if (element == null)
            return false;

        if (head == null){

            head = new Node<>(null, null, element);
            tail = head;
            head.item = element;
            return true;

        }

        Node<Item> aux = tail;

        Node<Item> node = new Node<>(tail, null, element);

        if (head.next == null) {
            head.next = node;
            node.previous = head;
        }

        node.item = element;
        aux.next = node;
        tail = node;
        sort("price");
        return true;
    }

    public boolean addAll(Collection<? extends Item> c){

            if(c == null)
                return false;

            Iterator<Item> it = (Iterator<Item>) c.iterator();
            while (it.hasNext()){
                add(it.next());
            }

            return true;
        }

    public ArrayList<Item> toArray(ItemList itemList){

        ArrayList<Item> array = new ArrayList<>();
        Node<Item> aux = head;

        while (aux != null){
            array.add(aux.item);
            aux = aux.next;
        }

        return array;
    }

    public void toList(ArrayList<Item> arrayList){
        Node<Item> aux = head;
        int index = 0;
        while (aux != null) {
            aux.item = arrayList.get(index);
            aux = aux.next;
            index++;
        }
    }


    public void sort(String identifier){

        ArrayList<Item> arrayList = toArray(this);

        if(identifier.equals("price"))
            Collections.sort(arrayList,new ItemComparator());
        if(identifier.equals("name"))
            Collections.sort(arrayList,new NameComparator());

        toList(arrayList);
    }

    public Item getItem(int index){
        Node<Item> aux = head;
        for (int i = 0; i < index; i++)
            aux = aux.next;

        return aux.item;
    }

    public double getMinPrice(){
        Node<Item> aux = head;
        double min = Double.MAX_VALUE;

        while (aux != null){
            if(aux.item.getPrice() < min)
                min = aux.item.getPrice();
            aux = aux.next;
        }

        return min;
    }

    public double getMaxPrice(){
        Node<Item> aux = head;
        double max = 0;

        while (aux != null){
            if(aux.item.getPrice() > max)
                max = aux.item.getPrice();
            aux = aux.next;
        }

        return max;
    }

    public Item getItem(Integer ID){

        ItemIterator<Item> it = (ItemIterator<Item>) this.listIterator();
        while (it.hasNext()){
            if(it.get().getID().equals(ID)){
                return it.get();
            }
            it.next();
        }

        return null;
    }

    public ArrayList<Item> getItems(){

        ArrayList<Item> items = new ArrayList<>();
        ItemIterator<Item> it = (ItemIterator<Item>) this.listIterator();

        while (it.hasNext()){
            items.add(it.get());
            it.next();
        }

        return items;
    }

    public void setItemPrice(int index, double price){

        Item item = getItem(index);
        item.setPrice(price);

    }

    public Node<Item> getNode(int index){
        Node<Item> aux = head;
        for (int i = 0; i < index; i++)
            aux = aux.next;

        return aux;
    }

    public int indexOf(Item item){
        Node<Item> aux = head;
        int index = 0;

        while (aux != null){

            if (aux.item.equals(item))
                return index;

            aux = aux.next;
            index++;
        }

        return index;
    }

    public int indexOf(Integer ID){

        Node<Item> aux = head;
        int index = 0;

        while (aux != null){
            if (aux.item.getID().equals(ID))
                return index;
            aux = aux.next;
            index++;
        }

        return index;
    }

    public boolean contains(Node<Item> node){

        Node<Item> aux = head;

        while (aux != null){
            if(aux.equals(node))
                return true;
            aux = aux.next;
        }

        return false;
    }

    public boolean contains(Item item){

        Node<Item> aux = head;

        while (aux != null){
            if(aux.item.equals(item))
                return true;
            aux = aux.next;
        }

        return false;
    }

    public boolean contains(Integer ID){

        Node<Item> aux = head;

        while (aux != null){
            if(aux.item.getID().equals(ID))
                return true;
            aux = aux.next;
        }

        return false;


    }

    public Item remove(int index){

        if(index == 0){
            Item item = head.item;
            head = head.next;
            head.previous = null;
            return item;
        }

        Node<Item> nodeToDelete = getNode(index);
        Node<Item> aux = nodeToDelete.next;
        Item item = nodeToDelete.item;
        nodeToDelete.previous.next = aux;

        if(aux != null)
            aux.previous = nodeToDelete.previous;
        else
            tail = nodeToDelete.previous;

        return item;
    }

    public void print(Node<Item> aux){

        while (aux != null){
            System.out.println(aux.item.getID());
            aux = aux.next;
        }

    }

    public boolean remove(Item item){
         if(!contains(item.getID()))
           return false;

        if(item.getID().equals(head.item.getID())){
             head = head.next;
             return true;
        }

        int index = indexOf(item.getID());
        Node<Item> nodeToDelete = head;

        for(int i = 0; i < index; i++)
            nodeToDelete = nodeToDelete.next;

         if(nodeToDelete.next != null) {
             nodeToDelete.previous.next = nodeToDelete.next;
             nodeToDelete.next.previous = nodeToDelete.previous;
         }
         else {
             nodeToDelete.previous.next = null;
             tail = nodeToDelete.previous;
         }

        return true;

    }

    public int size(){

        Node<Item> aux = head;
        int i = 0;

        while (aux != null){
            i++;
            aux = aux.next;
        }

        return i;
    }

    public boolean removeAll(Collection<? extends Item> collection){

            if(collection == null || collection.size() == 0)
                return false;

            for(int i = 0; i < collection.size(); i++){
                remove(i);
            }

            return true;

        }

    public boolean isEmpty(){
        return head == null;
    }


    public ListIterator<Item> listIterator(int index){

        ListIterator<Item> iterator = new ItemIterator<>();

        for (int i = 0; i < index; i++){
            iterator.next();
        }

        return iterator;
    }

    public ListIterator<Item> listIterator(){
        return new ItemIterator<>();
    }


    public Double getTotalPrice(){

        Node<Item> aux = head;
        Double price = new Double(0.0);

        while (aux != null){
            price += aux.item.getPrice();
            aux = aux.next;
        }

        return price;
    }

    public  Node<Item> getHead() {
        return head;
    }

    public Node<Item> getTail() {
        return tail;
    }
}
