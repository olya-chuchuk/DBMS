package util;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Olha_Chuchuk on 10/12/2017.
 */

public class FileCounterTest {

    @Test
    public void getCurrentDirectoryTest() throws IOException {
        System.out.println(FileCounter.getCurrentDirectory());
    }
}