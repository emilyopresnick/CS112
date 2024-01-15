/*
 * Test program for PS 1, problem 7.
 *
 * Put this program in the same folder as your Methods7.java.
 *
 * If it doesn't compile, that means that one or more of your methods
 * does not have the correct header -- i.e., either the name, the 
 * return type, or the parameters are incorrect.
 *
 * The correct results to these method calls are given in the assignment.
 * 
 * We encourage you to add additional test cases.
 */

public class Test7 {
    public static void main(String[] args) {
        System.out.println("** part 1 **");
        Methods7.printEveryOther("method");
        System.out.println();
        
        System.out.println("** part 2 **");
        int len = Methods7.longerLen("bye", "hello");
        System.out.println(len);
        System.out.println();      
                
        System.out.println("** part 3, example 1 **");
        int index1 = Methods7.secondIndex("banana", 'a');
        System.out.println(index1);
        
        System.out.println("** part 3, example 2 **");
        int index2 = Methods7.secondIndex("banana", 'n');
        System.out.println(index2);
        
        System.out.println("** part 3, example 3 **");
        int index3 = Methods7.secondIndex("banana", 'b');
        System.out.println(index3);
        
        System.out.println("** part 3, example 4 **");
        int index4 = Methods7.secondIndex("banana", 'x');
        System.out.println(index4);
    }
}