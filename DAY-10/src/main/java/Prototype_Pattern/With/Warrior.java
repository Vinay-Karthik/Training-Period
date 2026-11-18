package Prototype_Pattern.With;

import java.util.List;

/**
 * Concrete Prototype 1: Warrior character.
 */
public class Warrior extends GameCharacter {
    private String weapon;
    private int shieldDefense;

    public Warrior(String name, int health, int attackPower, List<String> skills, String weapon, int shieldDefense) {
        super(name, "Warrior", health, attackPower, skills);
        this.weapon = weapon;
        this.shieldDefense = shieldDefense;
    }

    /**
     * Copy Constructor for Warrior.
     */
    public Warrior(Warrior source) {
        super(source);
        this.weapon = source.weapon;
        this.shieldDefense = source.shieldDefense;
    }

    @Override
    public Warrior clonePrototype() {
        return new Warrior(this);
    }

    @Override
    public void displayInfo() {
        System.out.println("  ⚔️ [WARRIOR] Name: " + name
                + " | HP: " + health
                + " | ATK: " + attackPower
                + " | DEF: " + shieldDefense
                + " | Weapon: " + weapon
                + " | Skills: " + skills);
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getShieldDefense() {
        return shieldDefense;
    }

    public void setShieldDefense(int shieldDefense) {
        this.shieldDefense = shieldDefense;
    }
}
