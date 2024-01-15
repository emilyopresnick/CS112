import java.util.*;
public class Problem5{

    //finds and returns the intersection of two arrays using a method that walks down the two arrays similar to merge
    public static int[] intersect(int[] arr1, int[] arr2){
        int len=0;
        if(arr1.length>arr2.length){
            len=arr2.length;
        }
        else{
            len=arr1.length;
        }
        int inter[]=new int[len];

        Sort.quickSort(arr1);
        Sort.quickSort(arr2);
        int i = 0;    
        int j = 0;   
        int k = 0;   
        
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            }
            else if (arr1[i]>arr2[j]){
                j++;
            }
            else{
                inter[k]=arr1[i];
                k++; i++; j++;
            }

        }

        return inter;
    }

    public static void main(String[] args){
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = intersect(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));

        int[] a3 = {0, 2, -4, 6, 10, 8};            
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = intersect(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));
    }
   
}