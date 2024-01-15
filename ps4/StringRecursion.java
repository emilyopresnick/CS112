/*
 * StringRecursion.java
 *
 * 
 * Computer Science 112, Boston University
 *
 * Modified by: Emily Opresnick, eopres@bu.edu
 */


public class StringRecursion {

    //uses recursion to print the individual characters in the string str
    public static void printLetters(String str){
        if(str==null || str.equals("")){
            return;
        }
        else{
            System.out.print(str.charAt(0));
            if(str.length()>1){
                System.out.print(", ");
            }
            printLetters(str.substring(1));
            

        }

    }

    

    //uses recursion to return a string formed by replacting all the occurences of old char with new char
    public static String replace(String str, char oldChar, char newChar){
        if(str==null || str.equals("")){
            return str;
        }
        else{
            if(str.charAt(0)==(oldChar)){
                return newChar + replace(str.substring(1), oldChar, newChar);
            }
            return str.charAt(0) + replace(str.substring(1), oldChar, newChar);
        }

    }
    
    //uses recursion to find and return the index of the last occurence of the char ch in str and returns -1 if not in str
    public static int findLast(char ch, String str){
        if(str==null || str.equals("")){
            return -1;
        }
        else{
            int rest= findLast(ch, str.substring(0,str.length()-1));
            if(str.charAt(str.length()-1)==ch){
                return str.length()-1;
            }
            else{
                return rest;
            }
        }

    }

    
    
    public static void main(String[] args){
        printLetters("Rabbit");
        System.out.println();
        printLetters("I like to recurse!");
        System.out.println();
        System.out.println(replace("base case", 'e', 'y'));
        System.out.println(replace("base case", 'r', 'y'));
        System.out.println(findLast('r', "recurse"));
        System.out.println(findLast('p', "recurse"));

    }
    
}
