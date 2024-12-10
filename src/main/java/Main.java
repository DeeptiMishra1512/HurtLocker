import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result;
        result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) {
        String output = null;
        try {
            output = (new Main()).readRawDataToString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(output);
        HurtLockGroceryList groceryList = new HurtLockGroceryList();
        groceryList.uploadGroceryMap();

    }

}




