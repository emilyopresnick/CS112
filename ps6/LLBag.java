/*
 *LLBag.java
 *
 * Computer Science 112, Boston University
 * 
 * modified by: Emily Opresnick, eopres@bu.edu
 */

import java.util.*;
public class LLBag implements Bag {
    //inner node class
    private class Node {
        private Object item;
        private Node next;

        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }

    private int numItems;
    private Node head; 

    //constructs an LLbag object
    public LLBag(){
        numItems=0;
        head=new Node(null,null);

    }

    //adds one occurence of specified item in arraybag object
    public boolean add(Object item){
        if(item==null){
            throw new NullPointerException();
        }
        Node newNode = new Node(item, null);
        newNode.next = head.next;
        head.next=newNode;
        numItems++;
        return true;

    }
    //removes one occurence of specified item in arraybag object
     public boolean remove(Object item){
         if(item==null){
             throw new NullPointerException();
         }

         Node trav=head;
         while(trav.next!=null){
            if(item.equals(trav.next.item)){
                trav.next=trav.next.next;
                numItems--;
                return true;
            }
            trav=trav.next;
         }
         return false;

    }
    
    //returns true if bag contains item
    public boolean contains(Object item){
        if(item==null){
            throw new NullPointerException();
        }
        Node trav=head.next;
         while(trav!=null){
             if(trav.item.equals(item)){
                 return true;
             }
             trav=trav.next;
         }
         return false;

    }

    //helper method that returns the node at index i
    private Node getNode(int i) {
        Node trav = head;
        int travIndex = -1;

        while (travIndex < i) {
            travIndex++;
            trav = trav.next;
        }

        return trav;
    }


    //returns the number of items in the Bag.
    public int numItems(){
        return numItems;
    }
    
    //returns reference to randomly chosen item Bag
    public Object grab(){
        if (numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int bagIndex = (int)(Math.random() * numItems);
        return getNode(bagIndex).item ;


    }
    
    //returns an array of current bag
    public Object[] toArray(){
        Object[] bag = new Object[numItems];
        int i=0;
        Node trav=head.next;
        while(trav!=null){
            bag[i]=trav.item;
            i++;
            trav=trav.next;
        }
        return bag;

    }
    //toString method for LLbag
    public String toString(){
        String str = "{";
        Node trav=head.next;
        while(trav!=null){
            str= str+ trav.item;
            if (trav.next != null) {
                str += ", ";
            }
            trav=trav.next;
        }
        str = str + "}";
        return str;
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("How many items would you like to add right now: ");
        int numItems = scan.nextInt();
        Bag bag1 = new LLBag();
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < numItems; i++) {
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
        
        // Iterate over the objects in bag1, printing them one per line.
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
        
        scan.close();
    }


}
