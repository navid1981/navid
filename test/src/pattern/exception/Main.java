package pattern.exception;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Main <T>{
    public static void main(String[] args){
        //lambda
        Comparator<Computer> c = (c1, c2) -> c1.getAge().compareTo(c2.getAge());
        //method reference
        Comparator c2 = Comparator.comparing(Computer::getAge);
        Function<Computer, Integer> getAge = Computer::getAge;
        Integer computerAge = getAge.apply(new Computer());

        List<Parent> a=new ArrayList<>();
        List<Child> b=new ArrayList<>();
        //error
//        a=b;

        List<? extends Parent> e=new ArrayList<>();
        e=b;

        List<? super Child> f=new ArrayList<>();
        f=a;
    }


    //    The wildcard can be used in a variety of situations such as the type of a parameter, field,
//    or local variable; sometimes as a return type
    public List<? extends Number> get1(List<? extends Number> a){
        return a;
    }

    public <T extends Number> void get2(T a){
    }



//Generic
    public <T> void get(T a){
    }

    public <T> T get(T a,T b){
        return a;
    }
}

class Computer{
    Integer getAge(){
        return 3;
    }
    void get(){

    }
}