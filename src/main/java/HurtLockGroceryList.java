
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


/*
    private void printData(Map<String, Map<String, Integer>> itemData, int exceptionCount) {
        for (Map.Entry<String, Map<String, Integer>> entry : itemData.entrySet()) {
            String name = entry.getKey();
            Map<String, Integer> prices = entry.getValue();

            System.out.println("name:    " + name + "\t\t seen: " + prices.values().stream().mapToInt(Integer::intValue).sum() + " times");
            System.out.println("=============  \t\t =============");

            for (Map.Entry<String, Integer> priceEntry : prices.entrySet()) {
                System.out.println("Price:   " + priceEntry.getKey() + "\t\t seen: " + priceEntry.getValue() + " " + (priceEntry.getValue() > 1 ? "times" : "time"));
                System.out.println("-------------\t\t -------------");
            }

            System.out.println();
        }

        System.out.println("Errors\t\t\t seen: " + exceptionCount + " times");
    }

*/

    public int getExceptionCount() {
        return exceptionCount;
    }

}
