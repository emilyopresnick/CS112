public class Participant {
    private String name;
    private int age;

    public Participant(String name, int age){
        setName(name);
        setAge(age);
    }

    //sets age of participant object
    public void setAge(int age){
        if(age<0){
            throw new IllegalArgumentException();
        }
        else {
            this.age=age;
        }    
        
    }

    //sets name of participant object
    public void setName(String name){
        if(name==null){
            throw new IllegalArgumentException();
        }
        else{
            this.name=name;
        }
    }


    //gets name of participant object
    public String getName(){
        return this.name;
    }

    //gets age of participant object
    public int getAge(){
        return this.age;
    }
}