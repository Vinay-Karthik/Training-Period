package Prototype_Pattern.Without;

import java.util.ArrayList;
import java.util.List;

/**
 * GameCharacter class WITHOUT the Prototype Pattern.
 * <p>
 * Problems:
 * 1. Expensive Construction: Each instance re-runs heavy initialization
 * (simulating loading 3D meshes, textures, animations from disk/database).
 * 2. Tight Client Coupling: The client must know all constructor parameters and internal details.
 * 3. Fragile Manual Copying: If the client tries to duplicate a character, it must manually copy
 * every field via getters/setters, easily leading to bugs and shallow-copy reference leaks.
 */
public class GameCharacter {
    private String name;
    private String category;
    private int health;
    private int attackPower;
    private List<String> skills;
    private String heavyAssetData;

    /**
     * Standard constructor that executes an expensive resource-loading process.
     */
    public GameCharacter(String name, String category, int health, int attackPower, List<String> skills) {
        this.name = name;
        this.category = category;
        this.health = health;
        this.attackPower = attackPower;
        this.skills = skills != null ? skills : new ArrayList<>();

        // Simulating heavy resource loading (textures, sound clips, 3D polygon meshes)
        this.heavyAssetData = loadHeavyAssets(category);
    }

    /**
     * Simulates loading heavy 3D assets from disk/network.
     * Takes noticeable time and resources.
     */
    private String loadHeavyAssets(String category) {
        System.out.println("  ⏳ [HEAVY ASSET LOADER] Fetching 3D mesh, textures & sound clips for " + category + " from disk...");
        try {
            Thread.sleep(100); // Simulates 100ms I/O latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Loaded 128MB 3D Model & 4K Textures for [" + category + "]";
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void displayInfo() {
        System.out.println("  -> Name: " + name
                + " | Category: " + category
                + " | HP: " + health
                + " | ATK: " + attackPower
                + " | Skills: " + skills
                + " | Assets: " + heavyAssetData);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getHeavyAssetData() {
        return heavyAssetData;
    }

    public void setHeavyAssetData(String heavyAssetData) {
        this.heavyAssetData = heavyAssetData;
    }
}
