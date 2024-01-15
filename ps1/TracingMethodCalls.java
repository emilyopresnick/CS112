public class TracingMethodCalls {
    public static int compute(int x, int y) {
        y += x;
        x = 10 - y;
        System.out.println(x + " " + y);
        return x;
    }

    public static void main(String[] args) {
        int x = 6;
        int y = 3;
        System.out.println(x + " " + y);
        y = compute(x, y);
        System.out.println(x + " " + y);
        x = compute(y, x) + 1;
        System.out.println(x + " " + y);
        compute(x, x);
        System.out.println(x + " " + y);
    }
}