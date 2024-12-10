
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HurtLockGroceryList {

   //creating Linked Hash Map instead of Hash Map to keep the values in order of insertion
    //Have a nested Map as value which in turn stores item as key and number of times
    //Item occurs corresponding to a price value as Value in Map
    Map<String, Map<String, Integer>> itemData = new LinkedHashMap<>();
    int exceptionCount =0;

    //Created method to upload Linked Hash map with grocery list
    public Map<String, Map<String, Integer>> uploadGroceryMap() {

    //String variable to take the data from file into it
        String inputText = "";
        try {
            inputText = (new Main()).readRawDataToString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //variable to keep track of exceptions raised for value not found
         exceptionCount = 0;

        // Splitting the whole text in file based on '##' and storing the values in array
        String[] items = inputText.split("##");


        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            try {
                // Creating Pattern to use for matching it against input file
                Pattern keyValuePattern = Pattern.compile("([a-zA-Z]+)[@:^*%]([^;##]*)");
                Matcher matcher = keyValuePattern.matcher(item);

                //Variables to store the values of Name of Item and its Value for Display
                String name = null;
                String price = null;

                while (matcher.find()) {
                    String key = matcher.group(1).trim();
                    String value = matcher.group(2).trim();

                    // Handle missing values and increment exception count
                    if (value.isEmpty()) {
                        exceptionCount++;
                        throw new IllegalArgumentException("Missing value for key: " + key);
                    }

                    // Identifying different ways the names of Items are written
                    //And defining a definite name String
                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("(?i)c[o0]{2}kies")) {
                            value = "Cookies";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }
                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("(?i)BREAD")) {
                            value = "Bread";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }

                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("(?i)MILK")) {
                            value = "Milk";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }

                    if (key.equalsIgnoreCase("Name")) {
                        if (value.matches("(?i)APPLES")) {
                            value = "Apples";

                        }
                        name = value;

                    } else if (key.equalsIgnoreCase("Price")) {

                        price = value;
                    }

                }

                // Collect the data in a Map to return for display
                if (name != null && price != null) {

                    //putIfAbsent method of HashMap is used to add value
                    // and if value is null set it to default value
                    itemData.putIfAbsent(name, new LinkedHashMap<>());
                    Map<String, Integer> prices = itemData.get(name);
                    prices.put(price, prices.getOrDefault(price, 0) + 1);
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        // Call PrintData method to print the collected data
        printData(itemData, exceptionCount);
        return itemData;

    }


    private void printData(Map<String, Map<String, Integer>> itemData, int exceptionCount) {
        for (Map.Entry<String, Map<String, Integer>> entry : itemData.entrySet()) {
            String name = entry.getKey();
            Map<String, Integer> prices = entry.getValue();

            // Print the name and total count with formatting
            System.out.println(String.format("name:    %-14s seen: %2d times", name,
                    prices.values().stream().mapToInt(Integer::intValue).sum()));
            System.out.println("=============           =============");

            // Print each price and its count
            for (Map.Entry<String, Integer> priceEntry : prices.entrySet()) {
                System.out.println(String.format("Price:   %-14s seen: %2d %s",
                        priceEntry.getKey(),
                        priceEntry.getValue(),
                        priceEntry.getValue() > 1 ? "times" : "time"));
                System.out.println("-------------           -------------");
            }

            System.out.println();
        }

        // Print the error count
        System.out.println(String.format("Errors                seen: %2d times", exceptionCount));
    }

    public int getExceptionCount() {
        return exceptionCount;
    }

    public Map<String, Map<String, Integer>> getItemData() {
        return itemData;
    }
}
