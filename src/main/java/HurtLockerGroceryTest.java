import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class HurtLockerGroceryTest {

    private HurtLockGroceryList hurt = new HurtLockGroceryList();


    @Test
    public void uploadGroceryMapTest() {
        String mockRawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016" +
                "##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016" +
                "##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016" +
                "##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016" +
                "##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016";

        Main mainMock = new Main() {
            @Override
            public String readRawDataToString() throws Exception {
                return mockRawData;
            }
        };


        Map<String, Map<String, Integer>> result = hurt.uploadGroceryMap(mockRawData);

        // Validate aggregated data
        assertNotNull(result);
        assertEquals(3, result.size());

        // Check Milk normalization and aggregation
        assertTrue(result.containsKey("Milk"));
        assertEquals(2, (int) result.get("Milk").get("3.23"));

        // Check Bread normalization and aggregation
        assertTrue(result.containsKey("Bread"));
        assertEquals(1, (int) result.get("Bread").get("1.23"));

        // Check Cookies normalization and aggregation
        assertTrue(result.containsKey("Cookies"));
        assertEquals(2, (int) result.get("Cookies").get("2.25"));

        // Verify exception count
        assertEquals(0, hurt.getExceptionCount());
    }

    @Test
    public void handleMissingValuesTest() {
        String mockRawData = "naMe:;price:3.23;type:Food;expiration:1/04/2016##" +
                "naMe:Milk;price:;type:Food;expiration:1/11/2016";

        Main mainMock = new Main() {
            @Override
            public String readRawDataToString() throws Exception {
                return mockRawData;
            }
        };

        hurt.itemData.clear(); // Ensure no residual data
        hurt.uploadGroceryMap(mockRawData);

        // Verify no valid items were added
        assertTrue(hurt.itemData.isEmpty());

        // Verify exception count for invalid inputs
        assertEquals(2, hurt.getExceptionCount());
    }

    @Test
    public void normalizedNamesTest() {
        String mockRawData = "naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##" +
                "naMe:cOOkIeS;price:2.25;type:Food;expiration:3/22/2016##" +
                "naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##" +
                "naMe:APples;price:0.23;type:Food;expiration:5/02/2016";

        Main mainMock = new Main() {
            @Override
            public String readRawDataToString() throws Exception {
                return mockRawData;
            }
        };

        hurt.itemData.clear(); // Ensure no residual data
        hurt.uploadGroceryMap(mockRawData);

        Map<String, Map<String, Integer>> result = hurt.itemData;

        // Check normalized names
        assertTrue(result.containsKey("Cookies"));
        assertTrue(result.containsKey("Apples"));

        // Check counts for normalized names
        assertEquals(2, (int) result.get("Cookies").get("2.25"));
        assertEquals(1, (int) result.get("Apples").get("0.25"));
        assertEquals(1, (int) result.get("Apples").get("0.23"));
    }

    @Test
    public void testUploadGroceryMap_ExceptionHandling() {
        String mockRawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##" +
                "naMe:MiLK;price:;type:Food;expiration:1/11/2016##" +
                "naMe:;price:3.23;type:Food;expiration:1/04/2016";

        Main mainMock = new Main() {
            @Override
            public String readRawDataToString() throws Exception {
                return mockRawData;
            }
        };

        hurt.itemData.clear(); // Ensure no residual data
        hurt.uploadGroceryMap(mockRawData);

        // Verify exception count
        assertEquals(2, hurt.getExceptionCount());

        // Ensure valid data is still processed
        Map<String, Map<String, Integer>> result = hurt.itemData;
        assertTrue(result.containsKey("Milk"));
        assertEquals(1, (int) result.get("Milk").get("3.23"));
    }


}

