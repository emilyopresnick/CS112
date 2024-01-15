/*
 * Methods8.java
 * 
 * Code added by: Emily Opresnick, eopres@bu.edu
 *
 * practice with static methods part II
 */


public class Methods8 {

    //prints each character of string s on a diagnal
    public static void printDiag(String s){
        for(int i=0;i<s.length();i++){
            for(int j=0;j<i;j++){
                System.out.print(" ");
            }
            System.out.println(s.substring(i,i+1));
        }
    }


    //returns a string consisting of the last n characters of string s 
    public static String lastN(String s, int n){
        if(n>s.length()){
            return s;
        }
        else{
            return s.substring(s.length()-n);
        }
    }


    //returns the string produced when you remove the first occurence of the second string from the first string
    public static String remSub(String s1, String s2){
        
        if(s1.contains(s2)){
            int startIndex= s1.indexOf(s2);
            int s2L=s2.length();
            int endIndex = startIndex+(s2L);
            return s1.substring(0,startIndex)+s1.substring(endIndex);
        }
        return s1;
    }

    //returns the string formed by interleaving the characters from the first and seconf string
    public static String interleave(String s1, String s2){
        int s1Len= s1.length();
        int s2Len=s2.length();
        String shortStr="";
        String newStr="";
        String longStr="";

        if (s1Len>s2Len){
            shortStr=s2;
            longStr=s1;
        }
        else {
            shortStr=s1;
            longStr=s2;
        }

        for (int i=0;i<shortStr.length();i++){
            newStr+=s1.substring(i,i+1);
            newStr+=s2.substring(i,i+1);
        }
        return newStr+=longStr.substring(shortStr.length());
        
    }

    public static void main(String[] args){
        printDiag("method");
        System.out.println(lastN("programming", 5));
        System.out.println(lastN("programming", 1));
        System.out.println(lastN("programming", 15));

        System.out.println(remSub("ding-a-ling", "ing"));
        System.out.println(remSub("variable", "var"));
        System.out.println(remSub("Boston", "ten"));
        System.out.println(remSub("Boston", "ton"));

        System.out.println(interleave("leaving", "NOW"));
        System.out.println(interleave("", "hello"));
        System.out.println(interleave("aaaa", "bbbb"));
        System.out.println(interleave("hello", "world"));
    }
}
