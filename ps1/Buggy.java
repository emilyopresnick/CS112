import java.util.*;

public class Buggy{
    /*
     * This static method should take an integer x and return:
     *    x + 1 if x is even
     *    the unchanged value of x when x is odd
     */
    public static int makeOdd(int x)
    {
        if (x % 2 == 0){
            x++;
        }
        return x;
        
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter an integer x: ");
        int x = console.nextInt();
        System.out.println("makeOdd(x) = "+ makeOdd(x));
    }
}