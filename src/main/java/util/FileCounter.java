package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Olha_Chuchuk on 10/12/2017.
 */
public class FileCounter {

    public static List<String> getAllDbFiles() throws IOException {
        return Files.walk(Paths.get(""))
                .map(Path::toString)
                .filter(f -> f.endsWith(".db"))
                .map(f -> f.substring(0, f.length() - 3))
                .collect(Collectors.toList());
    }

    public static String getCurrentDirectory() throws IOException {
        File curFile = new File("");
        System.out.println(getAllDbFiles().toString());
        return curFile.getAbsolutePath();
    }
}
