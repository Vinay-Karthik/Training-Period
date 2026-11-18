package Prototype_Pattern.With;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Prototype Registry (or Prototype Cache / Factory).
 * <p>
 * Manages a catalog of pre-configured prototype instances.
 * Instead of clients knowing how to construct specific character classes,
 * they request ready-to-use clones from this registry using simple string keys.
 */
public class CharacterRegistry {
    private final Map<String, GameCharacter> prototypeCache = new HashMap<>();

    public CharacterRegistry() {
        loadInitialPrototypes();
    }

    /**
     * Initializes the standard prototypes once at system startup.
     * Heavy disk and asset loading occurs ONLY during this phase.
     */
    private void loadInitialPrototypes() {
        System.out.println("[REGISTRY] Pre-loading master character prototypes into cache...");

        // Master Warrior Prototype
        Warrior masterWarrior = new Warrior(
                "Standard Warrior Template",
                100,
                25,
                List.of("Slash", "Shield Block"),
                "Iron Broadsword",
                30
        );
        prototypeCache.put("BASIC_WARRIOR", masterWarrior);

        // Master Mage Prototype
        Mage masterMage = new Mage(
                "Standard Mage Template",
                70,
                40,
                List.of("Magic Dart", "Mana Barrier"),
                "Fire",
                100
        );
        prototypeCache.put("BASIC_MAGE", masterMage);

        System.out.println("[REGISTRY] Master templates cached successfully!\n");
    }

    /**
     * Retrieves an independent clone of the requested prototype.
     *
     * @param key The prototype identifier (e.g., "BASIC_WARRIOR", "BASIC_MAGE")
     * @return A deep copy clone of the prototype.
     * @throws IllegalArgumentException if the key does not exist.
     */
    public GameCharacter getPrototype(String key) {
        GameCharacter prototype = prototypeCache.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Unknown prototype key: " + key);
        }
        // Returns a freshly cloned instance in 0ms!
        return prototype.clonePrototype();
    }

    /**
     * Dynamically registers a new custom prototype into the registry at runtime.
     */
    public void registerPrototype(String key, GameCharacter prototype) {
        prototypeCache.put(key, prototype);
        System.out.println("[REGISTRY] Registered new custom prototype: [" + key + "]");
    }

    public boolean hasPrototype(String key) {
        return prototypeCache.containsKey(key);
    }
}
