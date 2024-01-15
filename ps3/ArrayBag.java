/* 
 * ArrayBag.java
 *
 * A blueprint class for objects that represent a *bag* of other objects --
 * i.e., a collection of items in which the items do not have a position.
 * 
 * Computer Science 112
 *
 * modified by: Emily Opresnick, eopres@bu.edu
 */

import java.util.*;

/**
 * An implementation of a bag data structure using an array.
 */
public class ArrayBag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }

    //returns the max number of items that the ArrayBag can hold
    public int capacity(){
        return items.length;
    }

    //returns true if the arraybag is empty and false otherwise
    public boolean isEmpty(){
        if (numItems==0){
            return true;
        }
        return false;
    }

    //decreases the capacity of the called ArrayBag by int decrease
    public boolean shrink(int decrease){
        if(decrease<0){
            throw new IllegalArgumentException();
        }

        if((items.length-decrease)>numItems){
            Object[] arr= new Object[items.length-decrease];

            for(int i=0;i<arr.length;i++){
                arr[i]=items[i];
            }

            items=arr;
            return true;

        }
        else {
            return false;
        }
    }


    //adds the items from other into the called arraybag and returns true if possible and false otherwise
    public boolean addItems(ArrayBag other){
        if(other==null){
            throw new IllegalArgumentException();
        }

        else if(isEmpty()){
            return true;
        }

        else if(this.capacity()-other.numItems<0){
            return false;
        }

        else{
            for(int i=0;i<other.numItems();i++){
                this.add(other.items[i]);
            }
            return true;
        }


    }


    //creates a new arraybag with one occurence of any item that is found in either the called array bag or other
    public ArrayBag unionWith(ArrayBag other){
        if(other==null){
            throw new IllegalArgumentException();
        }
        ArrayBag b = new ArrayBag(this.capacity() + other.capacity());

        for(int i=0;i<this.numItems();i++){
            if(b.contains(this.items[i])==false){
                b.add(this.items[i]);
               
            }
        }

        for(int j=0;j<other.numItems();j++){
            if(b.contains(other.items[j])==false){
                b.add(other.items[j]);
               
            }
        }
        return b;

    }




    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("size of bag 1: ");
        int size = scan.nextInt();
        ArrayBag bag1 = new ArrayBag(size);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();

        //test capacity should be 10
        ArrayBag b1 = new ArrayBag(10);
        System.out.println(b1.capacity());

        //test is empty, T F
        ArrayBag b2 = new ArrayBag(5);
        System.out.println(b2.isEmpty());
        b2.add("hello");
        System.out.println(b2.isEmpty());

        //tests shrink
        ArrayBag b3 = new ArrayBag(8);
        b3.add("hello");
        b3.add("world");
        System.out.println(b3);
        System.out.println("capacity before: " + b3.capacity());

        b3.shrink(5);
        System.out.println(b3);
        System.out.println("capacity after: " + b3.capacity());

        ArrayBag d = new ArrayBag(8);
        d.shrink(0);
        

        //testsAddItems
        ArrayBag b4 = new ArrayBag(15);
        String[] words1 = {"hello", "you", "how", "are", "you", "today?"};
        for (String w : words1) {
            b4.add(w);
        }        
        System.out.println(b4);

        ArrayBag b5 = new ArrayBag(10);
        String[] words2 = {"not", "bad", "how", "are", "you"};
        for (String w : words2) {
            b5.add(w);
        }        
        System.out.println(b5);

        System.out.println(b4.addItems(b5));
        System.out.println(b4);
        System.out.println(b5);

        ArrayBag empty= new ArrayBag();
 



        //checks union with
        ArrayBag b6 = new ArrayBag(15);
        String[] letters1 = {"a", "a", "b", "d", "f", "f", "f", "g"};
        for (String ltr: letters1) {
            b6.add(ltr);
        }
        System.out.println(b6);

        ArrayBag b7 = new ArrayBag(10);
        String[] letters2 = {"a", "b", "c", "d", "d", "e", "f"};
        for (String ltr: letters2) {
            b7.add(ltr);
        }
        System.out.println(b7);

        ArrayBag b8 = b6.unionWith(b7);
        System.out.println(b8);
        System.out.println(b8.numItems());
        System.out.println(b8.capacity());
        System.out.println(b6);   // make sure original bags are unchanged
        System.out.println(b7);

        ArrayBag l8 = b6.unionWith(empty);    }
}
