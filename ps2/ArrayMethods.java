/* Name: Emily Opresnick
   cs112 ps2 part 2
   Array Methods
*/


import java.util.*;
public class ArrayMethods{
    public static final String[] COLLEGES = {
        "CAS", "CFA", "CGS", "COM", "ENG", "QST", 
        "SAR", "SED", "SHA", "OTHER"};
    
    //takes a reference to a string and returns the index of that String in the array COLLEGES
    public static int getCollegeNum(String coll){
        for(int i=0;i<COLLEGES.length;i++){
            if(COLLEGES[i].equals(coll)){
                return i;
            }
        }
        return -1;
    }

    //takes in an array arr and negates all of the even numbers in the array
    public static void negateEvens(int[]arr){
        if(arr==null){
            throw new IllegalArgumentException();
        }

        for(int i=0;i<arr.length;i++){
            if(arr[i]%2==0){
                arr[i]*=-1;
            }
        }
    }

    //returns an array that is a slide from start to end of array values
    public static int[] slice(int[] values, int start, int end){
        int arr[];
        if((values==null) || (start<0) || (start>values.length) || (end>values.length)){
            throw new IllegalArgumentException();
        }

        if(end<=start){
            arr=new int[0];
        }

        else {
            arr=new int[end-start];
            int j=0;

            for(int i=start;i<end;i++){
                arr[j]=values[i];
                j++;
            }
        }
        return arr;
    }


    public static boolean isSorted(int[] arr){
        if(arr==null){
            throw new IllegalArgumentException();
        }

        for(int i=0;i<arr.length-1;i++){
            if (arr[i]>arr[i+1]){
                return false;
            }
        }
        return true;
    }

    //takes in an array values and returns the max number of elements that are sorted
    public static int maxSorted(int[] values){
        if(values==null){
            throw new IllegalArgumentException();}
        int max=0;
        int count=0;
        int maxEnd=0;

        for(int i=0;i<values.length-1;i++){

            if(values[i]<=values[i+1]){
                count++;
                if(i==values.length-2){
                  maxEnd=count+1;}
            }

            else{
                if (count>=max){
                    max=count+1;
                }
                count=0;
            }
        }
        if(maxEnd>max){
            return maxEnd;
        }
        return max;
    }

    public static void main(String args[]){
        int[] a1 = {1, 2, 4, 5, -8, -10, -11};
        negateEvens(a1);
        System.out.println(Arrays.toString(a1));
        int[] a2 = {2, 5, 6, 3, 7, 4, 1};
        int[] a3 = ArrayMethods.slice(a2, 2, 5);
        System.out.println(Arrays.toString(a3));
        int[] a4 = ArrayMethods.slice(a2, 5, 2);
        System.out.println(Arrays.toString(a4));
        int[] a5 = {2, 5, 6, 6, 9, 9, 9, 10};
        int[] a6 = {2, 5, 6, 4, 9, 9, 7, 10};
        System.out.println(ArrayMethods.isSorted(a5));
        System.out.println(ArrayMethods.isSorted(a6));
        int[] a={};
        System.out.println(ArrayMethods.isSorted(a));
        int[] a7 = {3, 8, 6, 14, -3, 0, 14, 207, 98, 12};
        System.out.println(ArrayMethods.maxSorted(a7));
        int[] a8 = {17, 42, 3, 5, 5, 5, 8, 4, 6, 1, 19};
        System.out.println(ArrayMethods.maxSorted(a8));
        System.out.println(ArrayMethods.maxSorted(a));
        int[] d ={5,4,3,2,1};
        System.out.println(ArrayMethods.maxSorted(d));
        int[] i= {1, 2, 3, 4, 5, 6, 7};
        System.out.println(ArrayMethods.maxSorted(i));
        int[] iend= {1,2,3,4,5,6,2};
        System.out.println(ArrayMethods.maxSorted(iend));
        int[] start={9,1,2,3,4,5};
        System.out.println(ArrayMethods.maxSorted(start));


    }

}