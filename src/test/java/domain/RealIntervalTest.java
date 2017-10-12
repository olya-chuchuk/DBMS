package domain;

import org.junit.Test;

/**
 * Created by Olha_Chuchuk on 10/12/2017.
 */
public class RealIntervalTest {

    @Test
    public void parseStringTest() {
        RealInterval.valueOf("[ 4.6 , 234.678  ]");
    }

}