/*
 * Methods7.java
 * 
 * Code added by: Emily Opresnick, eopres@bu.edu
 *
 * Practice with static methods, part I
 */

public class Methods7 {
    /*
     * 0) printVertical - takes a string s and prints the characters of 
     *    the string vertically -- with one character per line.
     */
    public static void printVertical(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
        }
    }


    //prints every other character of the string s
    public static void printEveryOther(String s){
        for (int i=0;i<s.length();i+=2){
            char c=s.charAt(i);
            System.out.print(c);
        }
        System.out.println();
    }


    //takes in two strings and returns the length of the longer string
    public static int longerLen(String s1, String s2){
        int s1Len=s1.length();
        int s2Len=s2.length();
        
        if(s1Len>s2Len){
            return s1Len;
        }
        return s2Len;
    }


    //returns the index of the second instance of a character c in string s
    public static int secondIndex(String s, char c){
        String cStr=""+c;
        int count=0;

        for(int i=0;i<s.length();i++){
            if (s.substring(i,i+1).equals(cStr) && count==0){
                count++;
            }
            else if (s.substring(i,i+1).equals(cStr) && count==1){
                return i;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        /* Sample test call */
        printVertical("method");
        printEveryOther("method");       
        int len = longerLen("to", "hi");
        System.out.println("the longer length is: " + len);
        System.out.println(secondIndex("banana", 'a'));
        System.out.println(secondIndex("banana", 'n'));
        System.out.println(secondIndex("banana", 'b'));
        System.out.println(secondIndex("banana", 'x'));
    }
}
