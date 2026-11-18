package Prototype_Pattern.With;

import java.util.List;

/**
 * Concrete Prototype 2: Mage / Spellcaster character.
 */
public class Mage extends GameCharacter {
    private String spellElement;
    private int mana;

    public Mage(String name, int health, int attackPower, List<String> skills, String spellElement, int mana) {
        super(name, "Mage", health, attackPower, skills);
        this.spellElement = spellElement;
        this.mana = mana;
    }

    /**
     * Copy Constructor for Mage.
     */
    public Mage(Mage source) {
        super(source);
        this.spellElement = source.spellElement;
        this.mana = source.mana;
    }

    @Override
    public Mage clonePrototype() {
        return new Mage(this);
    }

    @Override
    public void displayInfo() {
        System.out.println("  🔮 [MAGE]    Name: " + name
                + " | HP: " + health
                + " | ATK: " + attackPower
                + " | Mana: " + mana
                + " | Element: " + spellElement
                + " | Skills: " + skills);
    }

    public String getSpellElement() {
        return spellElement;
    }

    public void setSpellElement(String spellElement) {
        this.spellElement = spellElement;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
