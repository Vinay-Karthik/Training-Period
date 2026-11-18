package Flyweight_Pattern.With;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Forest Simulation (With Flyweight Pattern) ===");
        Forest forest = new Forest();

        int oakCount = 100000;
        int pineCount = 50000;

        System.out.println("Planting " + oakCount + " Oak trees and " + pineCount + " Pine trees...");
        for (int i = 0; i < oakCount; i++) {
            forest.plantTree(i, i, "Oak", "Green", "Rough Bark Texture Data");
        }
        for (int i = 0; i < pineCount; i++) {
            forest.plantTree(i, i + 1000, "Pine", "DarkGreen", "Needle Texture Data");
        }

        // Draw sample trees
        System.out.println("\nSample tree renderings:");
        forest.getTrees().get(0).draw();
        forest.getTrees().get(oakCount).draw();

        System.out.println("\nTotal Tree context instances: " + forest.getTrees().size());
        System.out.println("Total Flyweight TreeType instances in memory: " + TreeFactory.getUniqueTreeTypesCount());
        System.out.println("Memory saved: Rather than 150,000 sets of intrinsic state, only 2 shared TreeType objects exist!");
    }
}
