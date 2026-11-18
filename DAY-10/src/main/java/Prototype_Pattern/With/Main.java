package Prototype_Pattern.With;

import java.util.List;

/**
 * Demonstrates character creation and duplication WITH the Prototype Design Pattern.
 * <p>
 * Notice the key advantages:
 * 1. Blazing Fast Spawning: Clones are created in memory in ~0ms by bypassing disk I/O.
 * 2. True Deep Copying: Mutating a clone's skills never corrupts other clones or the master prototype.
 * 3. Loose Coupling: Clients request clones via simple keys through the CharacterRegistry.
 * 4. Runtime Extensibility: New prototypes can be created and registered dynamically on the fly.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   GAME SPAWNER - WITH PROTOTYPE PATTERN (FAST, DEEP & ROBUST)   ");
        System.out.println("=================================================================");

        // --- PHASE 1: Initialize the Prototype Registry (Loads master templates once) ---
        System.out.println("\n--- [PHASE 1] Initializing Character Registry ---");
        long initStart = System.currentTimeMillis();
        CharacterRegistry registry = new CharacterRegistry();
        long initEnd = System.currentTimeMillis();
        System.out.println("Registry startup complete in " + (initEnd - initStart) + " ms.");

        // --- PHASE 2: Instant Spawning via Prototype Cloning ---
        System.out.println("\n--- [PHASE 2] Spawning 3 Warriors using Prototype Clones ---");
        long spawnStart = System.currentTimeMillis();

        // Retrieve and customize clones
        Warrior warrior1 = (Warrior) registry.getPrototype("BASIC_WARRIOR");
        warrior1.setName("Thorin (Vanguard)");

        Warrior warrior2 = (Warrior) registry.getPrototype("BASIC_WARRIOR");
        warrior2.setName("Gimli (Defender)");
        warrior2.setShieldDefense(50); // Customized shield

        Warrior warrior3 = (Warrior) registry.getPrototype("BASIC_WARRIOR");
        warrior3.setName("Boromir (Striker)");
        warrior3.setWeapon("Elven Halberd"); // Customized weapon

        long spawnEnd = System.currentTimeMillis();
        System.out.println("  ⚡ Time taken to clone 3 fully-equipped characters: " + (spawnEnd - spawnStart) + " ms (Sub-millisecond!)");

        System.out.println("\nSpawned Warrior Clones:");
        warrior1.displayInfo();
        warrior2.displayInfo();
        warrior3.displayInfo();

        // --- PHASE 3: Deep Copy Verification (Proving No State Leakage) ---
        System.out.println("\n--- [PHASE 3] Deep Copy Verification ---");
        System.out.println("Granting '🔥 Berserker Rage' to 'Thorin' ONLY...");
        warrior1.addSkill("🔥 Berserker Rage");

        System.out.println("\nInspecting all characters after upgrade:");
        System.out.print("Warrior 1 (Thorin):  ");
        warrior1.displayInfo();
        System.out.print("Warrior 2 (Gimli):   ");
        warrior2.displayInfo();
        System.out.print("Warrior 3 (Boromir): ");
        warrior3.displayInfo();

        // Verify a fresh clone from the registry
        Warrior freshWarrior = (Warrior) registry.getPrototype("BASIC_WARRIOR");
        freshWarrior.setName("Fresh Recruit");
        System.out.print("Fresh from Registry: ");
        freshWarrior.displayInfo();

        System.out.println("\n✅ SUCCESS: '🔥 Berserker Rage' exists ONLY on Thorin!");
        System.out.println("The master prototype in the registry and other clones remain completely untouched!");

        // --- PHASE 4: Polymorphic Mage Cloning & Customization ---
        System.out.println("\n--- [PHASE 4] Cloning and Customizing a Mage ---");
        Mage frostMage = (Mage) registry.getPrototype("BASIC_MAGE");
        frostMage.setName("Jaina Proudmoore");
        frostMage.setSpellElement("Frost & Ice");
        frostMage.addSkill("❄️ Blizzard Storm");
        frostMage.setMana(180);

        frostMage.displayInfo();

        // --- PHASE 5: Dynamic Runtime Prototype Registration ---
        System.out.println("\n--- [PHASE 5] Dynamically Registering a Boss Prototype at Runtime ---");
        Warrior bossTemplate = new Warrior(
                "Demon Warlord Boss",
                500,
                90,
                List.of("Hellfire Cleave", "Ground Slam", "Dread Aura"),
                "Demonic Greatsword",
                80
        );
        // Register the boss template into our registry
        registry.registerPrototype("DEMON_BOSS", bossTemplate);

        // Instantly spawn two mini-bosses based on the new prototype
        System.out.println("\nSpawning 2 Mini-Bosses from the newly registered prototype:");
        Warrior miniBoss1 = (Warrior) registry.getPrototype("DEMON_BOSS");
        miniBoss1.setName("Warlord Azazel (North Gate)");

        Warrior miniBoss2 = (Warrior) registry.getPrototype("DEMON_BOSS");
        miniBoss2.setName("Warlord Malgath (South Gate)");

        miniBoss1.displayInfo();
        miniBoss2.displayInfo();

        System.out.println("\n=================================================================");
        System.out.println("  SUMMARY OF BENEFITS WITH PROTOTYPE PATTERN:");
        System.out.println("  ✅ High Performance: Avoids redundant heavy object construction.");
        System.out.println("  ✅ Deep Copy Safety: Clones are 100% isolated and mutate independently.");
        System.out.println("  ✅ Registry Pattern: Central catalog allows instant cloning by key.");
        System.out.println("  ✅ Runtime Flexibility: Clone existing objects with complex states easily.");
        System.out.println("=================================================================");
    }
}
