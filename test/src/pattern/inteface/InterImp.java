package pattern.inteface;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InterImp implements Inter{

    @Override
    public void get1() {
        Inter.super.get1();
    }

    @Override
    public Integer ge2() throws FileNotFoundException {
        return new Integer(2);
    }
}
