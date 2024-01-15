/*
 * StringNode1.java
 *
 * Computer Science 112, Boston University
 * 
 * modified by:
 *   name:
 *   email:
 */

import java.io.*;
import java.util.*;

/*
 * A class that can be used to represent a string using a linked list.
 * Each character of the string is stored in a separate node.  
 *
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list. The methods in this class are static methods that
 * take a reference to a string linked-list as a parameter. This
 * approach allows us to use recursion to write many of the methods,
 * and it also allows the methods to handle empty strings, which are 
 * represented using a value of null.
 */
public class StringNode1 {
    private char ch;
    private StringNode1 next;

    /*
     * Constructor
     */
    public StringNode1(char c, StringNode1 n) {
        this.ch = c;
        this.next = n;
    }

    /*
     * getNode - private helper method that returns a reference to
     * node i in the given linked-list string.  If the string is too
     * short or if the user passes in a negative i, the method returns null.
     */
    private static StringNode1 getNode(StringNode1 str, int i) {
        if (i < 0 || str == null) {    // base case 1: not found
            return null;
        } else if (i == 0) {           // base case 2: just found
            return str;
        } else {
            return getNode(str.next, i - 1);
        }
    }

    /****************************************************
     * Public methods (in alphabetical order)
     *****************************************************/

    /*
     * charAt - returns the character at the specified index of the
     * specified linked-list string, where the first character has
     * index 0.  If the index i is < 0 or i > length - 1, the method
     * will end up throwing an IllegalArgumentException.
     */
    public static char charAt(StringNode1 str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("the string is empty");
        } 
          
        StringNode1 node = getNode(str, i);

        if (node != null) {
            return node.ch;     
        } else {
            throw new IllegalArgumentException("invalid index: " + i);
        }
    }
    
    /*
     * compareAlpha - compares two linked-list strings to determine
     * which comes first alphabetically (i.e., according  to the ordering 
     * used for words in a dictionary). 
     * 
     * It returns:
     *    1 if str1 comes first alphabetically
     *    2 if str2 comes first alphabetically
     *    0 if str1 and str2 represent the same string
     * 
     * The empty string comes before any non-empty string, 
     * and the prefix of a string comes before the string
     * itself (e.g., "be" comes before "become").
     */
    public static int compareAlpha(StringNode1 str1, StringNode1 str2) {
        if (str1 == null && str2 == null) {
            return 0;
        } else if (str1 == null) {
            return 1;
        } else if (str2 == null) {
            return 2;
        } else if (str1.ch < str2.ch) {
            return 1;
        } else if (str2.ch < str1.ch) {
            return 2;
        } else {
            int compareRest = compareAlpha(str1.next, str2.next);
            return compareRest;
        }
    }

    /*
     * convert - converts a standard Java String object to a linked-list
     * string and returns a reference to the linked-list string
     */
    public static StringNode1 convert(String s) {
        if (s.length() == 0) {
            return null;
        }

        StringNode1 firstNode = new StringNode1(s.charAt(0), null);
        StringNode1 prevNode = firstNode;
        StringNode1 nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNode1(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }
    
    /*
     * copy - returns a copy of the given linked-list string
     */
    public static StringNode1 copy(StringNode1 str) {
        if (str == null) {
            return null;
        }

        // make a recursive call to copy the rest of the list
        StringNode1 copyRest = copy(str.next);
           
        // create and return a copy of the first node, 
        // with its next field pointing to the copy of the rest
        return new StringNode1(str.ch, copyRest);
    }

    /*
     * copy2 - a slightly different version of the above method;
     * it also returns a copy of the given linked-list string
     */
    public static StringNode1 copy2(StringNode1 str) {
        if (str == null) {
            return null;
        }

        // Create the first node of the copy, copying the
        // first character into it.
        StringNode1 copyFirst = new StringNode1(str.ch, null);
        
        // Make a recursive call to get a copy of the rest, 
        // and store the result in the first node's next field.
        copyFirst.next = copy2(str.next);
        return copyFirst;
    }

    /*
     * deleteChar - deletes character i in the given linked-list string and
     * returns a reference to the resulting linked-list string
     */
    public static StringNode1 deleteChar(StringNode1 str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("string is empty");
        } else if (i < 0) { 
            throw new IllegalArgumentException("invalid index: " + i);
        } else if (i == 0) { 
            str = str.next;
        } else {
            StringNode1 prevNode = getNode(str, i-1);
            if (prevNode != null && prevNode.next != null) {
                prevNode.next = prevNode.next.next;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }

    /*
     * doubleChar - doubles all occurrences of the character c
     * in the linked-list string to which str refers.
     * For example, if str represents the string "banana",
     * doubleChar('a', str) should make str represent the string
     * "baanaanaa". Note that the method does not return a value,
     * because it modifies the existing linked list, and the first
     * node before the method is called is still the first node
     * after the method is called.
     */
    public static void doubleChar(char c, StringNode1 str) {
        if (str == null) {
            return;
        }
        
        // make a recursive call to process the rest of the list
        doubleChar(c, str.next);
        
        // if the character in the current node matches the character c, 
        // insert a double of it after the current node.
        if (str.ch == c) {
            StringNode1 doubleCurrent = new StringNode1(c, str.next);
            str.next = doubleCurrent;
        }
    }
    
    /* 
     * insertBefore - inserts the specified new character (newChar) 
     * before the first occurrence of the specified character (afterChar)
     * in the linked-list string to which str refers.
     * If beforeChar is not in the string, the method adds the character
     * to the end of the string. Returns a reference to the first node
     * in the modified linked list, because the first node can change.
     */
    public static StringNode1 insertBefore(StringNode1 str, char newChar, 
                                         char beforeChar) 
    {
        StringNode1 newNode = new StringNode1(newChar, null);
        
        // If the string is empty or beforeChar is in the current first node, 
        // return the new node, which is the new first node.
        if (str == null) {
            return newNode;
        } else if (str.ch == beforeChar) {
            newNode.next = str;
            return newNode;
        }
        
        StringNode1 trail = null;
        StringNode1 trav = str;
        while (trav != null) {
            if (trav.ch == beforeChar) {
                // Perform the insertion. Given the else-if check above,
                // we know that trail is non-null if we get here,
                // so we don't need to worry about a null-pointer exception.
                trail.next = newNode;
                newNode.next = trav; 
                
                // We're done. Return the first node as required.
                return str;
            }
            
            trail = trav;
            trav = trav.next;
        }
        
        // If we get here, we didn't find beforeChar,
        // so we insert the new node at the end of the list
        // by using trail, which is pointing to the current last node.
        trail.next = newNode;
        return str;
    }

    /*
     * insertChar - inserts the character ch before the character
     * currently in position i of the specified linked-list string.
     * Returns a reference to the resulting linked-list string.
     */
    public static StringNode1 insertChar(StringNode1 str, int i, char ch) {
        StringNode1 newNode, prevNode;

        if (i < 0) { 
            throw new IllegalArgumentException("invalid index: " + i);
        } else if (i == 0) {
            newNode = new StringNode1(ch, str);
            str = newNode;
        } else {
            prevNode = getNode(str, i - 1);
            if (prevNode != null) {
                newNode = new StringNode1(ch, prevNode.next);
                prevNode.next = newNode;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }

    /*
     * insertSorted - inserts character ch in the correct position
     * in a sorted list of characters (i.e., a sorted linked-list string)
     * and returns a reference to the resulting list.
     */
    public static StringNode1 insertSorted(StringNode1 str, char ch) {
        StringNode1 newNode, trail, trav;

        // Find where the character belongs.
        trail = null;
        trav = str;
        while (trav != null && trav.ch < ch) {
            trail = trav;
            trav = trav.next;
        }

        // Create and insert the new node.
        newNode = new StringNode1(ch, trav);
        if (trail == null) {
            // We never advanced the prev and trav references, so
            // newNode goes at the start of the list.
            str = newNode;
        } else { 
            trail.next = newNode;
        }
            
        return str;
    }

    /*
     * length - recursively determines the number of characters in the
     * linked-list string to which str refers
     */
    public static int length(StringNode1 str) {
        if (str == null) {
            return  0;
        } else {
            return 1 + length(str.next);
        }
    }

    /*
     * numOccur - find the number of occurrences of the character
     * ch in the linked list to which str refers
     */
    public static int numOccur(StringNode1 str, char ch) {
        if (str == null) {
            return 0;
        }
     
        int numInRest = numOccur(str.next, ch);
        if (str.ch == ch) {
            return 1 + numInRest;
        } else {
            return numInRest;
        }
    }

    /*
     * print - recursively writes the specified linked-list string to System.out
     */
    public static void print(StringNode1 str) {
        if (str == null) {
            return;
        } else {
            System.out.print(str.ch);
            print(str.next);
        }
    }

    /*
     * read - reads a string from an input stream and returns a
     * reference to a linked list containing the characters in the string
     */
    public static StringNode1 read(InputStream in) throws IOException { 
        char ch = (char)in.read();

        if (ch == '\n') {    // the string ends when we hit a newline character
            return null;         
        } else {
            StringNode1 restOfString = read(in);
            StringNode1 first = new StringNode1(ch, restOfString);
            return first;
        }
    }
    
    /*
     * toString - creates and returns the Java string that
     * the current StringNode1 represents.  Note that this
     * method -- unlike the others -- is a non-static method.
     * Thus, it will not work for empty strings, since they
     * are represented by a value of null, and we can't use
     * null to invoke this method.
     */
    public String toString() {
        String str = "";
        
        StringNode1 trav = this;   // start trav on the current node    
        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }
         
        return str;
    }
    
    /*
     * toUpperCase - converts all of the characters in the specified
     * linked-list string to upper case.  Modifies the list itself,
     * rather than creating a new list.
     */
    public static void toUpperCase(StringNode1 str) {        
        StringNode1 trav = str; 
        while (trav != null) {
            trav.ch = Character.toUpperCase(trav.ch); 
            trav = trav.next;
        }
    } 
              
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        
        // toUpperCase
        System.out.print("Enter a string: ");
        String s = in.nextLine();
        StringNode1 str1 = StringNode1.convert(s);
        System.out.print("Here it is in upper-case letters: "); 
        StringNode1.toUpperCase(str1);
        System.out.println(str1);
        
        // length
        int len = StringNode1.length(str1);
        System.out.println("Its length is " + len);

        // copy and deleteChar
        System.out.print("\nEnter another string: ");
        s = in.nextLine();
        StringNode1 str2 = StringNode1.convert(s);
        System.out.print("Making a copy of it...");
        StringNode1 copyStr2 = StringNode1.copy(str2);

        int n = -1;
        while (n < 0) {
            System.out.print("\nIndex of character to delete (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        try {
            str2 = StringNode1.deleteChar(str2, n);
            StringNode1.print(str2);
        } catch (IllegalArgumentException e) {
            System.out.println("That index is too big for the string.");
        }

        // The copy should be unchanged!
        System.out.print("\nThe copy should be unchanged: ");
        System.out.println(copyStr2);
        
        // compareAlpha
        System.out.print("\nEnter another string: ");
        s = in.nextLine();
        StringNode1 str3 = StringNode1.convert(s);
        System.out.print("comparing " + str2 + " and " + str3 + " gives: ");
        System.out.println(StringNode1.compareAlpha(str2, str3));   
        
        // insertBefore
        str2 = copyStr2;
        System.out.print("\nWhat character to insert in " + str2 + ": ");
        char c = in.nextLine().charAt(0);
        System.out.print("What character to insert before? ");
        char before = in.nextLine().charAt(0);
        str2 = StringNode1.insertBefore(str2, c, before);
        System.out.println(str2);

        // doubleChar
        System.out.print("\nEnter another string: ");
        s = in.nextLine();
        StringNode1 str4 = StringNode1.convert(s);
        System.out.print("What character to double? ");
        c = in.nextLine().charAt(0);
        StringNode1.doubleChar(c, str4);
        System.out.println(str4);

        in.close();
    }
}
