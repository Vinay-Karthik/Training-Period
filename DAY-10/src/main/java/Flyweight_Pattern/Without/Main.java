package Flyweight_Pattern.Without;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Forest Simulation (Without Flyweight Pattern) ===");
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

        System.out.println("\nTotal Tree objects created in memory: " + forest.getTrees().size());
        System.out.println("Every Tree object duplicates the intrinsic state (name, color, texture).");
    }
}
