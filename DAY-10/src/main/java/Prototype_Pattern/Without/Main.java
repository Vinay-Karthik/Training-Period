package Prototype_Pattern.Without;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates character creation and duplication WITHOUT the Prototype Pattern.
 * <p>
 * Notice the two major flaws:
 * 1. Performance Problem: Creating multiple similar characters repeatedly loads
 * heavy assets from disk from scratch, wasting CPU and memory.
 * 2. The Shallow Copy Trap: When attempting to manually copy an object, shared
 * mutable references (like lists/arrays) corrupt the original object's state!
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("  GAME SPAWNER - WITHOUT PROTOTYPE PATTERN (EXPENSIVE & BUGGY)   ");
        System.out.println("=================================================================");

        // --- PROBLEM 1: Expensive Repeated Object Instantiation ---
        System.out.println("\n[SCENARIO 1] Spawning 3 Orc Warriors using direct 'new' constructor calls...");
        long startTime = System.currentTimeMillis();

        List<String> baseSkills1 = new ArrayList<>(List.of("Slash", "Block"));
        GameCharacter orc1 = new GameCharacter("Orc Warrior #1", "Orc", 100, 25, baseSkills1);

        List<String> baseSkills2 = new ArrayList<>(List.of("Slash", "Block"));
        GameCharacter orc2 = new GameCharacter("Orc Warrior #2", "Orc", 100, 25, baseSkills2);

        List<String> baseSkills3 = new ArrayList<>(List.of("Slash", "Block"));
        GameCharacter orc3 = new GameCharacter("Orc Warrior #3", "Orc", 100, 25, baseSkills3);

        long endTime = System.currentTimeMillis();
        System.out.println("  ⏱️ Total time to spawn 3 characters: " + (endTime - startTime) + " ms");
        System.out.println("  ⚠️ Notice: Heavy disk/mesh loading was executed 3 separate times!");

        System.out.println("\nSpawned characters:");
        orc1.displayInfo();
        orc2.displayInfo();
        orc3.displayInfo();

        // --- PROBLEM 2: The Shallow Copy Trap (Accidental State Mutation) ---
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("⚠️ [SCENARIO 2] Manual Copy Attempt -> The Shallow Copy Bug!");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A developer attempts to copy 'Orc Warrior #1' manually using getters/setters:");

        // The developer manually copies fields from orc1 to create an elite orc
        List<String> sharedSkills = orc1.getSkills(); // SHIFTING REFERENCE, NOT DUPLICATING!
        GameCharacter eliteOrc = new GameCharacter(
                "Elite Orc",
                orc1.getCategory(),
                150,
                40,
                sharedSkills // Passing same reference!
        );

        System.out.println("\nBefore elite upgrade:");
        System.out.print("Original Orc 1: ");
        orc1.displayInfo();
        System.out.print("Elite Orc:      ");
        eliteOrc.displayInfo();

        System.out.println("\nGranting a new elite skill '🔥 Berserker Bloodlust' to Elite Orc only...");
        eliteOrc.addSkill("🔥 Berserker Bloodlust");

        System.out.println("\nAfter elite upgrade:");
        System.out.print("Elite Orc:      ");
        eliteOrc.displayInfo();
        System.out.print("Original Orc 1: ");
        orc1.displayInfo();

        System.out.println("\n💥 BUG DETECTED! 'Orc Warrior #1' received '🔥 Berserker Bloodlust' too!");
        System.out.println("Reason: Both characters share the exact same 'skills' List in memory!");

        System.out.println("\n=================================================================");
        System.out.println("  SUMMARY OF ISSUES WITHOUT PROTOTYPE PATTERN:");
        System.out.println("  1. Repetitive heavy initialization freezes game loops.");
        System.out.println("  2. Client is tightly coupled to constructor parameters.");
        System.out.println("  3. Manual copying is error-prone and leads to shallow copy mutations.");
        System.out.println("=================================================================");
    }
}
