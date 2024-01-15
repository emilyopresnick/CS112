
public class triArea{
    public static double triArea(int b, int h) {
        double area = 0.5*b*h;
        return area;
    }
    public static void main(String[]args){
        String s1 = "Don't string";
        String s2 = "me along!";
        System.out.println(s1.substring(6,7)+s1.substring(9)+s2.substring(2,8));
        System.out.println((s1.substring(0,1)+s1.substring(9)).toUpperCase()+s2.substring(s2.length()-1));
        System.out.println((s1.substring(0,1)).toLowerCase()+s1.substring(9,10)+s2.substring(0,2));
        System.out.println(s1.charAt(8));
        System.out.println(s1.indexOf('i'));
        System.out.println(s1.replace('t', 'u'));
        

        
    }
}
