public class Test  {

    
    public static void main(String[]args){

        LLQueue Q= new LLQueue<Object>();
        LLStack S= new LLStack<Object>();
        S.push(1);
        S.push(2);
        S.push(3);
        S.push(4);
        Object I=3;
        System.out.println(S.toString());
        boolean found=false;
        while(!S.isEmpty()){

            Object item = S.pop();
            if(I.equals(item)){
                found=true;
            }
            Q.insert(item);

        }
        while(!Q.isEmpty()){
            S.push(Q.remove());

        }
        while(!S.isEmpty()){
            Q.insert(S.pop());
        }
        while(!Q.isEmpty()){
            S.push(Q.remove());

        }
        System.out.println(found);
        System.out.println(S.toString());
    }
   
    
}
