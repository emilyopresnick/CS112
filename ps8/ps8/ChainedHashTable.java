/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Emily Opresnick
 *     email: eopres@bu.edu
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size){
        if(size<1){
            throw new IllegalArgumentException();
        }
        table=new Node[size];
        numKeys=0;
    }
    
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        if(key==null ){
            throw new NullPointerException();
        }
        Node obj=new Node(key,value);
        Node head=table[h1(key)];
        
        //if table is empty at position given by hash table, add key and value to the table
        if(head==null){
            table[h1(key)]=obj;
            numKeys++;
            return true;
        }

        //check for any duplicates and adds value to that key if so
        while(head!=null){
            if(head.key.equals(key)){
                head.values.insert(value);
                return true;
            }
            head=head.next;
        }

        //if there are no duplicates, add key and value
        head=table[h1(key)];
        table[h1(key)]=obj;
        obj.next=head;
        numKeys++;
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        if(key==null){
            throw new NullPointerException();
        }
        int index=h1(key);
        Node i=table[index];
        while(i!=null){
            
            if(i.key.equals(key)){
                return i.values;
            }
            i=i.next;
        }
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
        if(key==null){
            throw new NullPointerException();
        }
        int index = h1(key);

        if(table[index].key.equals(key)){
            LLQueue<Object> removedVals =table[index].values;
            table[index]=table[index].next;
            numKeys--;
            return removedVals;

        }

        else{
            Node head=table[index];
            Node prev=null;

            while(head!=null){

                if(head.key.equals(key)){
                    LLQueue<Object> removedVals =head.values;
                    prev.next=head.next;
                    numKeys--;
                    return removedVals;
                }

                prev=head;
                head=head.next;
            }
        }
        return null;
    }

    
    /*** Add the other required methods here ***/

   //returns the number of keys in the table 
    public int getNumKeys(){
        return numKeys;
    }


    //returns the load factor of the table, the num keys divided by table length
    public double load(){
        return (double) numKeys/table.length;
    }

    public Object[] getAllKeys(){
        Object[] k = new Object[numKeys];
        int index=0;

        for(int i=0;i<table.length;i++){
            if(table[i]==null){
                continue;
            }
            Node trav=table[i];

            while(trav!=null){
                k[index]=trav.key;
                index++;
                trav=trav.next;
            }
        }
        return k;
    }


    //takes in an integer representing the new size and creates a new hashtable of this size, rehasing all current values
    public void resize(int size){
        if(size<table.length){
            throw new IllegalArgumentException();
        }
        if(size>table.length){
            ChainedHashTable t=new ChainedHashTable(size);
            int hNew;

            for(int i=0;i<table.length;i++){
                if(table[i]==null){
                    continue;
                }

                Node trav=table[i];

                while(trav!=null){
                    hNew=h1(trav.key);
                    t.insert(trav.key, hNew);
                    trav=trav.next;
                }
            }
            this.table=t.table;
            this.numKeys=t.numKeys;
        }
    }

    
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        ChainedHashTable table1 = new ChainedHashTable(0);
        table1.insert("howdy", 15);

        System.out.println("(0) Testing insert method for chained hash table: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            String results = table.toString();
            String expected = "[{apple; howdy}, null, null, {goodbye}, null]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(1) Testing insert method for chained hash table with duplicates: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 45);
            table.insert("howdy", 35);
            table.insert("howdy", 65);
            String results = table.toString();
            String expected = "[{apple; howdy}, null, null, {goodbye}, null]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing search method for chained hash table with duplicates: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 45);
            table.insert("howdy", 35);
            table.insert("howdy", 65);
            Queue results = table.search("howdy");
            Queue expected = new LLQueue<Object>();
            expected.insert(15);
            expected.insert(45);
            expected.insert(35);
            expected.insert(65);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            while(expected.isEmpty()){
                if(expected.remove()!=results.remove()){
                    System.out.println(false);
                    break;
                }
            }
            System.out.println(true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing remove method for chained hash table when you want to remove NULL: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("dadada", 15);
            table.insert("apple", 5);
            table.remove(null);
        } catch (Exception e) {
            System.out.println("CORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests


        System.out.println("(4) Testing remove method for chained hash table: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("dadada", 15);
            table.insert("apple", 5);
            table.remove("dadada");
            String results = table.toString();
            String expected = "[{apple; howdy}, null, null, {goodbye}, null]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(5) Testing search method for chained hash table when key is not in table: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("banana", 15);
            table.insert("apple", 5);
            table.search("pickles");
        } catch (Exception e) {
            System.out.println("CORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(6) Testing load method for chained hash table : ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            double results=table.load();
            double expected=0.6;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results==(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }


        System.out.println();    // include a blank line between tests

        System.out.println("(7) Testing load method for chained hash table : ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("pear", 6);
            double results=table.load();
            double expected=0.8;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results==(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(8) Testing accessor method for amount of keys in chained hash table : ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println(table.getNumKeys());
            int results=table.getNumKeys();
            int expected=3;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results==(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(9) Testing accessor method for amount of keys in chained hash table with a duplicate : ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 25);     // insert a duplicate
            int results=table.getNumKeys();
            int expected=3;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results==(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(10) Testing getAllKeys method : ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 25);    // insert a duplicate
            Object[] k = table.getAllKeys();
            String results= Arrays.toString(k);
            String expected="[apple, howdy, goodbye]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(11) Testing getAllKeys method when hash table is empty: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            Object[] k = table.getAllKeys();
            String results= Arrays.toString(k);
            String expected="[]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(12) Testing resize method on chained hash table, resizing from 5 to 7: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.resize(7);
            String results=table.toString();
            String expected="[null, {apple}, null, null, null, {howdy}, {goodbye}]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests


        System.out.println("(13) Testing resize method on chained hash table that resizes by the same amount: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.resize(5);
            String results=table.toString();
            String expected="[{apple; howdy}, null, null, {goodbye}, null]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(15) Testing resize method on chained hash table that resizes by negative number: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.resize(-5);
        } catch (Exception e) {
            System.out.println("CORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests
        
        System.out.println("(16) Testing resize method on chained hash table that resizes by smaller number: ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.resize(3);
        } catch (Exception e) {
            System.out.println("CORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(17) Testing resize method on chained hash table : ");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("calculator", 15);
            table.insert("desk", 10);
            table.insert("computer", 5);
            table.resize(8);
            int c=table.h1("calculator");
            int d=table.h1("desk");
            int o=table.h1("computer");
            System.out.println("calculator at position: " + c +" desk position:  " + d + " computer at position : " + o );
            String results=table.toString();
            String expected="[null, {desk}, {calculator}, {computer}, null, null, null, null]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

    


    }
}
