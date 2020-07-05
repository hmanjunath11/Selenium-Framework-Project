package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileReader {
    public static InputStream read(String filePath) {
    	InputStream input = null;
		try {
			input = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return input;
    }
}