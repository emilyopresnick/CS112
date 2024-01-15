import java.util.*;
public class YourClassNameHere {
  public static void mystery(int[] values) {
    int val = values[0];

    for (int i = 0; i < values.length - 1; i++) {
        values[i] = values[i + 1];
        values[i]++;
        // 1-1: What does the array look like here,
        // for each value of the loop variable `i`?
    }

    values[values.length - 1] = val + 1;
}
    // 1-2: What does the array look like here?
    public static void main(String[] args) {
      int[] arr = {1, 3, 5, 7, 9, 11, 13};
    mystery(arr);
    System.out.println(Arrays.toString(arr));

    }
}