package Flyweight_Pattern.With;

import java.util.HashMap;
import java.util.Map;

// Flyweight Factory: Manages pool/cache of Flyweight objects
public class TreeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "_" + color + "_" + texture;
        TreeType result = treeTypes.get(key);

        if (result == null) {
            result = new TreeType(name, color, texture);
            treeTypes.put(key, result);
            System.out.println("Created new Flyweight TreeType: [" + name + ", " + color + "]");
        }

        return result;
    }

    public static int getUniqueTreeTypesCount() {
        return treeTypes.size();
    }
}
