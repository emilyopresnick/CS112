public class ArrayMethodCheck {
    public static int maxSorted(int[] values){
        int maxcount = 0;
        int count;
        int j;
        int arraylength = values.length;
        if (values == null){
            throw new IllegalArgumentException();
        }
        if(arraylength == 0){
            return 0;
        }else{
            if(arraylength == 1){
                return 1;
            }
        }
        int i =0;
        boolean descending = false;
        while(i<arraylength-1 && !descending){
            if (values[i] < values[i + 1]){
                i +=1 ;
            }else{
                descending = true;
            }
        }
        if (descending == false){
            return 1;
        }
    
    
        for(i = 0; i<arraylength-2; i++){
            boolean done = false;
            count = 1;
            j = i;
            while (!done){
                if (values [j] <= values[j+1]){
                    count += 1;
                    j += 1;
                }else{
                    done = true;
                }
            }
            if (count > maxcount){
                maxcount = count;
            }
        }
        return maxcount;
    }

    public static void main(String[] args){
        int[] a00 = {-5, -4, -3, -2, 20, 26, 5, 1, 2, 3, 4};
        System.out.println(ArrayMethods.maxSorted(a00));
    }
    
}
