package pattern.exception;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Parent {
    void doSomething() throws IOException, ParseException {
        // ...
    }

    Number doSomethingElse() throws IOException {
        return null;
    }

    void doSomethingElse2() {
        // ...
    }

    //overloading
    void overloading() {
        // ...
    }

}

class Child extends Parent {
    void doSomething() throws IOException {
        // ...
    }

    Integer doSomethingElse() throws FileNotFoundException, EOFException {
        return null;
    }

    //Unchecked exception are alowded event parent doesn't have exception
    void doSomethingElse2() throws RuntimeException{
        // ...
    }

    //overloading
    void overloading(int i) throws Exception{
        // ...
    }

    String overloading(String s) {
        // ...
        return s;
    }
}
