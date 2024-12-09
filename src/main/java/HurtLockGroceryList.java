
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HurtLockGroceryList {
    Map<String, Map<String, Integer>> itemData = new LinkedHashMap<>();
    int exceptionCount =0;

    //Created method to upload Hash map with grocery list
    public Map<String, Map<String, Integer>> uploadGroceryMap() {


        String inputText = "";
        try {
            inputText = (new Main()).readRawDataToString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


         exceptionCount = 0;

        // Split on '##'
        String[] items = inputText.split("##");

        for (String item : items) {
            try {
                // Match key-value pairs
                Pattern keyValuePattern = Pattern.compile("([a-zA-Z]+)[@:^*%]([^;##]*)");
                Matcher matcher = keyValuePattern.matcher(item);

                String name = null;
                String price = null;

                while (matcher.find()) {
                    String key = matcher.group(1).trim();
                    String value = matcher.group(2).trim();

                    // Handle missing values
                    if (value.isEmpty()) {
                        exceptionCount++;
                        throw new IllegalArgumentException("Missing value for key: " + key);
                    }

                    // Normalize names
                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("(?i)c[o0]{2}kies")) {
                            value = "Cookies";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }
                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("[Bb][Rr][Ee][Aa][Dd]")) {
                            value = "Bread";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }

                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("[Mm][Ii][Ll][Kk]")) {
                            value = "Milk";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }

                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("[Aa][Pp]{2}[Ll][Ee][Ss]")) {
                            value = "Apples";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }

                }

                // Aggregate data
                if (name != null && price != null) {
                    itemData.putIfAbsent(name, new LinkedHashMap<>());
                    Map<String, Integer> prices = itemData.get(name);
                    prices.put(price, prices.getOrDefault(price, 0) + 1);
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        // Print the aggregated data
       // printData(itemData, exceptionCount);
        return itemData;

    }



    public int getExceptionCount() {
        return exceptionCount;
    }

}
