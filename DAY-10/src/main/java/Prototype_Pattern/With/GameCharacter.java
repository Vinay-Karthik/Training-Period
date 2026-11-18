package Prototype_Pattern.With;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract GameCharacter acting as the base Prototype.
 * <p>
 * Key Advantages:
 * 1. Fast Cloning: Uses a Copy Constructor that skips the heavy asset-loading process.
 * 2. Deep Copying: Creates a new, independent ArrayList for mutable lists (skills),
 * preventing accidental side effects between clones.
 * 3. Resource Sharing: Reuses immutable heavy asset references (textures/meshes)
 * safely across all clones without reloading from disk.
 */
public abstract class GameCharacter implements Prototype<GameCharacter> {
    protected String name;
    protected String category;
    protected int health;
    protected int attackPower;
    protected List<String> skills;
    protected String heavyAssetData;

    /**
     * Standard Constructor:
     * Used ONLY when creating the original master template prototype.
     * Executes the expensive disk/network asset-loading operation.
     */
    public GameCharacter(String name, String category, int health, int attackPower, List<String> skills) {
        this.name = name;
        this.category = category;
        this.health = health;
        this.attackPower = attackPower;
        this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();
        this.heavyAssetData = loadHeavyAssets(category);
    }

    /**
     * Copy Constructor:
     * Used by clonePrototype() to duplicate existing characters in sub-milliseconds.
     * Skips the heavy disk loading and performs a DEEP COPY of mutable state!
     */
    protected GameCharacter(GameCharacter source) {
        this.name = source.name;
        this.category = source.category;
        this.health = source.health;
        this.attackPower = source.attackPower;

        // DEEP COPY: Create a new independent list so modifying a clone's skills
        // never alters the original prototype's skills!
        this.skills = new ArrayList<>(source.skills);

        // SAFE SHARING: Reuse already-loaded immutable asset reference (0ms latency!)
        this.heavyAssetData = source.heavyAssetData;
    }

    /**
     * Simulates heavy asset loading from disk/network.
     * Only executed once per archetype!
     */
    private String loadHeavyAssets(String category) {
        System.out.println("  ⏳ [HEAVY ASSET LOADER] Loading 3D model, sound clips & textures for " + category + " from disk...");
        try {
            Thread.sleep(100); // Simulates 100ms I/O latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Loaded 128MB 3D Model & 4K Textures for [" + category + "]";
    }

    /**
     * Abstract cloning method to be implemented by concrete classes.
     */
    @Override
    public abstract GameCharacter clonePrototype();

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
        this.skills = new ArrayList<>(skills);
    }

    public String getHeavyAssetData() {
        return heavyAssetData;
    }
}
