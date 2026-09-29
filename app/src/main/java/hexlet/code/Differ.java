package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> dataOne, Map<String, Object> dataTwo) {
        StringBuilder result = new StringBuilder();
        result.append("{").append(System.lineSeparator());

        Map<String, String> symbols = Map.of(
            "empty", "    ",
            "add", "  + ",
            "remove", "  - ",
            "separator", ": "
        );

        Set<String> allKeys = new TreeSet<>(dataOne.keySet());
        allKeys.addAll(dataTwo.keySet());

        for (String key: allKeys) {
            boolean findKeyDataOne = dataOne.containsKey(key);
            boolean findKeyDataTwo = dataTwo.containsKey(key);

            if (findKeyDataOne && findKeyDataTwo) {
                if(Objects.equals(dataOne.get(key), dataTwo.get(key))) {
                    result
                        .append(symbols.get("empty"))
                        .append(key)
                        .append(symbols.get("separator"))
                        .append(dataOne.get(key))
                        .append(System.lineSeparator());
                } else {
                    result
                        .append(symbols.get("remove"))
                        .append(key)
                        .append(symbols.get("separator"))
                        .append(dataOne.get(key))
                        .append(System.lineSeparator());

                    result
                        .append(symbols.get("add"))
                        .append(key)
                        .append(symbols.get("separator"))
                        .append(dataTwo.get(key))
                        .append(System.lineSeparator());
                }
            } else if (findKeyDataTwo) {
                result
                    .append(symbols.get("add"))
                    .append(key)
                    .append(symbols.get("separator"))
                    .append(dataTwo.get(key))
                    .append(System.lineSeparator());
            } else {
                result
                    .append(symbols.get("remove"))
                    .append(key)
                    .append(symbols.get("separator"))
                    .append(dataOne.get(key))
                    .append(System.lineSeparator());
            }
        }

        return result.append("}").toString();
    }
}
