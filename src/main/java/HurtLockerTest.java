import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;



public class HurtLockerTest {

    private HurtLockGroceryList groceryList;

    @BeforeEach
    public void setUp() {
        groceryList = new HurtLockGroceryList();
    }

    // Test for correct collection of data and prices
    @Test
    public void testCorrectPriceAggregation() {
        String rawData = "Name:Milk@Price:3.23##Name:Milk@Price:3.23##Name:Bread@Price:1.23##";
        HurtLockGroceryList parser = new HurtLockGroceryList() {
           // @Override
            public String readRawDataToString() {
                return rawData;
            }
        };

        parser.uploadGroceryMap(rawData);
        Map<String, Map<String, Integer>> itemData = parser.getItemData();

        // Check Milk
        Map<String, Integer> milkPrices = itemData.get("Milk");
        assertEquals(2, milkPrices.get("3.23"), "Milk price 3.23 should appear 2 times.");

        // Check Bread
        Map<String, Integer> breadPrices = itemData.get("Bread");
        assertEquals(1, breadPrices.get("1.23"), "Bread price 1.23 should appear 1 time.");
    }









//
//
//    @Test
//    public void testUploadGroceryMap_NormalizedNames() {
//        // Mock data with various names for normalization
//        String mockRawData = "Name:c00Kies@Price:2.25##Name:BrEAd@Price:1.50##Name:ApPlEs@Price:2.00##";
//        Main mainMock = new Main() {
//            @Override
//            public String readRawDataToString() {
//                return mockRawData;
//            }
//        };
//
//
//        // Set mock raw data and process it
//        HurtLockGroceryList mockList = new HurtLockGroceryList() {
//            @Override
//            public Map<String, Map<String, Integer>> uploadGroceryMap() {
//                return super.uploadGroceryMap();
//            }
//        };
//
//
//
//        Map<String, Map<String, Integer>> itemData = groceryList.uploadGroceryMap();
//
//        // Check normalized names
//        assertNotNull(itemData.get("Cookies"), "Cookies should be a normalized name in the data.");
//        assertNotNull(itemData.get("Bread"), "Bread should be a normalized name in the data.");
//        assertNotNull(itemData.get("Apples"), "Apples should be a normalized name in the data.");
//
//        // Check counts for each normalized item
//        assertEquals(1, itemData.get("Cookies").get("2.25"), "Cookies price 2.25 should appear 1 time.");
//        assertEquals(1, itemData.get("Bread").get("1.50"), "Bread price 1.50 should appear 1 time.");
//        assertEquals(1, itemData.get("Apples").get("2.00"), "Apples price 2.00 should appear 1 time.");
//    }
//
//
//
//    @Test
//    public void testUploadGroceryMap_MultipleOccurrences() {
//        // Mock data with multiple occurrences of the same item
//        String mockRawData = "Name:Milk@Price:3.23##Name:Milk@Price:3.23##Name:Bread@Price:1.50##";
//        Main mainMock = new Main() {
//            @Override
//            public String readRawDataToString() {
//                return mockRawData;
//            }
//        };
//
//        Map<String, Map<String, Integer>> itemData = groceryList.uploadGroceryMap();
//
//        // Check counts for Milk
//        assertNotNull(itemData.get("Milk"), "Milk should be present in the data.");
//        assertEquals(2, itemData.get("Milk").get("3.23"), "Milk price 3.23 should appear 2 times.");
//
//        // Check counts for Bread
//        assertNotNull(itemData.get("Bread"), "Bread should be present in the data.");
//        assertEquals(1, itemData.get("Bread").get("1.50"), "Bread price 1.50 should appear 1 time.");
//    }
//
//
//
//    @Test
//    public void testUploadGroceryMap_MissingValues() {
//        // Mock data with missing values
//        String mockRawData = "Name:Milk@Price:##Name:Bread@Price:1.50##";
//        Main mainMock = new Main() {
//            @Override
//            public String readRawDataToString() {
//                return mockRawData;
//            }
//        };
//
//        Map<String, Map<String, Integer>> itemData = groceryList.uploadGroceryMap();
//        int exceptionCount = groceryList.getExceptionCount();
//
//        // Check that Milk with missing price is not added
//        assertNull(itemData.get("Milk"), "Milk with missing price should not be added.");
//
//        // Check that Bread is added correctly
//        assertNotNull(itemData.get("Bread"), "Bread should be present in the data.");
//        assertEquals(1, itemData.get("Bread").get("1.50"), "Bread price 1.50 should appear 1 time.");
//
//        // Check exception count
//        assertEquals(1, exceptionCount, "There should be 1 exception for the missing price.");
//    }
//
//
//
//    @Test
//    public void testUploadGroceryMap_EmptyData() {
//        // Mock empty data
//        String mockRawData = "";
//        Main mainMock = new Main() {
//            @Override
//            public String readRawDataToString() {
//                return mockRawData;
//            }
//        };
//
//        Map<String, Map<String, Integer>> itemData = groceryList.uploadGroceryMap();
//
//        // Check that the data is empty
//        assertTrue(itemData.isEmpty(), "The item data should be empty for empty input.");
//        assertEquals(0, groceryList.getExceptionCount(), "There should be no exceptions for empty input.");
//    }
//
//
//
//    @Test
//    public void testUploadGroceryMap_MalformedData() {
//        // Mock malformed data
//        String mockRawData = "Name@Price:3.23##Price@Milk@##";
//        Main mainMock = new Main() {
//            @Override
//            public String readRawDataToString() {
//                return mockRawData;
//            }
//        };
//
//        Map<String, Map<String, Integer>> itemData = groceryList.uploadGroceryMap();
//        int exceptionCount = groceryList.getExceptionCount();
//
//        // Check that no valid data is added
//        assertTrue(itemData.isEmpty(), "No valid data should be added for malformed input.");
//        assertEquals(2, exceptionCount, "There should be 2 exceptions for malformed input.");
//    }


}





















//    private String inputText;
//    Main mainJava = new Main();
//
//
//    public void setup(){
//        try {
//            this.inputText = mainJava.readRawDataToString();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//    private HurtLockGroceryList parser;
//
//    @Before
//    public void setUp() {
//        // Initialize the parser before each test
//        parser = new HurtLockGroceryList();
//    }
//
//    // Test for loading the raw data from the file
//    @Test
//    public void testLoadFile() {
//
//
//
//    }
//
//
//    @Test
//    public void mapHasEntry(){
//
//
//
//    }
//
//
//
//@Test
//    public void countNumberOfItemsTest(){
//
//
//
//    }
//
//  @Test
//    public void checkSpelling(){
//
//
//
//    }
//
//
//
//
//






