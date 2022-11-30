package pattern.inteface;

import java.io.IOException;

public interface Inter {
    public static final int a=1;

    //static method should have body
    public static void get(){

    };

    //default should have body
    public default void get1(){

    };

    Number ge2() throws IOException;

    //cannot have constructor
//    public Inter(){
//
//    }
}
