import java.math.BigInteger;

/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 * 
 * Emily Opresnick, eopres@bu.edu
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }


    //constructor for a BigInt object that takes in an array
    public BigInt(int[]arr){
        if(arr.length>SIZE || arr==null){
            throw new IllegalArgumentException();
        }

        if(checkArr(arr)==false){
            throw new IllegalArgumentException();
        }

        this.digits=new int[SIZE];
        this.numSigDigits=computeSigDig(arr);
        int j=0;

        for(int i=SIZE-numSigDigits;i<SIZE;i++){
            
           this.digits[i]=arr[arr.length-numSigDigits+j];
           j++;
        }
        
    }

    //constructor for BigInt Object that takes in an integer n
    public BigInt(int n){
        if(n<0){
            throw new IllegalArgumentException("Invalid number");
        }

        digits=numToArray(n);
        numSigDigits=countDig(n);
 
    }

    //helper method to turn an int n into an array representing each digit of n
    public static int[] numToArray(int n){
        int[]arr= new int[SIZE];
        int dig=countDig(n);

        for(int i=arr.length-1;i>=arr.length-dig;i--){ 

            arr[i]=n%10;
            n=n/10;
        }

        return arr;
       
    }
 
    //helper method to count the digits in an integer
    public static int countDig(int n){
        int count=0;

        while(n>=1){
            n=n/10;
            count++;
        }

        if(count==0){
            count=1;
        }
        return count;

    }


    //getter method that returns the number of significant digits a BigInt object has
    public int getNumSigDigits(){
        return this.numSigDigits;
    }


    //returns the String version of BigInt Number
    public String toString(){
        String digits="";

        if(getNumSigDigits()==0){
            return "0";
        }

        for(int i=SIZE-getNumSigDigits();i<SIZE;i++){
            digits+=this.digits[i];
        }

        return digits;
    }

    //helper method that computes and returns the number of significant digits
    public static int computeSigDig(int[]arr){
        int dig=0;
        boolean start=false;

        for(int i=0;i<arr.length;i++){

            if(arr[i]!=0&&start==false){
                start=true;  
            }

            if(start==true){
                dig++;
            }
    
        }
        if(dig==0){
            dig=1;
        }
    
        return dig;
    }


    //checks the passed array and makes sure each index is valid
    public static boolean checkArr(int[]arr){
        for(int i=0;i<arr.length;i++){

            if(arr[i]<0||arr[i]>9){

                return false;
            }

        }
        return true;
    }


    //compares one big int object to another and returns 1 if the called object is greater than the other, -1 if other is greater, 0 if equal
    public int compareTo(BigInt other){
        if(other==null){
            throw new IllegalArgumentException();
        }
 
        for(int i=0;i<SIZE;i++){

            if(this.digits[i]>other.digits[i]){
                return 1;
            }

            else if(this.digits[i]<other.digits[i]){
                return -1;
            }

        }
 
        return 0;
    }
 
     
    //helper method that returns true if BigInt object is 0
    public static boolean isZero(BigInt bigInt){
        for(int i=0;i<SIZE;i++){

            if(bigInt.digits[i]!=0){
                return false;
            }
        }
        return true;
    }

    //adds two BigInt objects and returns the sum
    public BigInt add(BigInt other){
        int[] arr= new int[SIZE];

        if(isZero(this)){
            return other;
        }

        else if(isZero(other)){
            return this;
        }

        int carry=0;
        int sum=0;
        int newSum=0;

        for(int i=SIZE-1;i>=0;i--){

            if(i==0){

                if(this.digits[i]+other.digits[i]+carry>9){
                    throw new ArithmeticException();
                }

            }

            sum=this.digits[i]+other.digits[i]+carry;
            newSum=sum%10;

            if(sum>9){
                carry=1;
            }

            else{
                carry=0;
            }

            arr[i]=newSum;
        }
        
        BigInt sumInt = new BigInt(arr);
        return sumInt;
    }
 
    //multiplies two big int objects together and returns the product as a big int object
    public BigInt mul(BigInt other){
        if(other==null){
            throw new IllegalArgumentException();
        }
        BigInt sum = new BigInt();
        int index = 0;
        int carry = 0;

        BigInt[] arr = new BigInt[SIZE];
        for(int i = 0; i < arr.length; i++){
            arr[i] = new BigInt();
        } 

        for(int i = SIZE-1; i >= SIZE-other.numSigDigits; i--) {

            for(int j = SIZE-1; j >= SIZE-this.numSigDigits; j--) {
                
                int prod=this.digits[j] * other.digits[i] + carry;

                if (prod>9 && j==0 ){
                    throw new ArithmeticException();
                } 

                if(prod >= 10){
                    carry=prod/10;
                }

                else {
                    
                    carry=0;
                }

                arr[index].digits[j-index]=prod%10;
                
            }
            index++;
        }

        for(int i = 0; i<arr.length; i++) {
            sum = sum.add(arr[i]);
            
        }

        sum.numSigDigits=computeSigDig(sum.digits);
        return sum;
    }

        

    
    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();


        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();


                      
        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();



        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();



        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();



        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        BigInt pp=new BigInt(999999999);
        BigInt bb=new BigInt(666);
        System.out.println(bb.getNumSigDigits());
        System.out.println(pp.mul(bb));
        
    }
}
