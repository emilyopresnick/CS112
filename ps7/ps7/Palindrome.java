/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Emily Opresnick
 *     email: eopres@bu.edu
 */
   
public class Palindrome {
    //takes in a string object and returns true if it is a palindrome, false otherwise
    public static boolean isPal(String str){
        if(str==null){
            throw new NullPointerException("Error: String is Null, try again");
        }
        Stack<Character> stack = new LLStack<Character>();
        Queue<Character> q = new LLQueue<Character>();
        
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);

            if((c>96&&c<123)||(c>64&&c<91)){
                c=Character.toLowerCase(c);
                stack.push(c);
                q.insert(c);
            }
        }

        while(!stack.isEmpty()){
            if(stack.pop()!=q.remove()){
                return false;
            }
        }
        return true;

    }
    
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        

        System.out.println("(1) Testing on empty string");
        try {
            boolean results = isPal("");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on String with length of 1");
        try {
            boolean results = isPal("A");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"pUlL-uP\"");
        try {
            boolean results = isPal("pUlL-uP");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
  


        System.out.println("(4) Testing on non palindrome: \"yellow\"");
        try {
            boolean results = isPal("yellow");
            boolean expected = false;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(5) Testing on null");
        try {
            boolean results = isPal(null);
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("CORRECTLY THREW AN EXCEPTION: " + e);
        }
    }
}