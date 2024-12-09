import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class HurtLockerTest {
    private String inputText;
    Main mainJava = new Main();


    public void setup(){
        try {
            this.inputText = mainJava.readRawDataToString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private HurtLockGroceryList parser;

    @Before
    public void setUp() {
        // Initialize the parser before each test
        parser = new HurtLockGroceryList();
    }

    // Test for loading the raw data from the file
    @Test
    public void testLoadFile() {



    }


    @Test
    public void mapHasEntry(){



    }



@Test
    public void countNumberOfItemsTest(){



    }

  @Test
    public void checkSpelling(){



    }










}
