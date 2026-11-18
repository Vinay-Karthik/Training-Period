package Singleton_Pattern.Without;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Normal Instance (Without Singleton Pattern) ===");
        NormalInstance instance1 = NormalInstance.getInstance();
        NormalInstance instance2 = NormalInstance.getInstance();

        System.out.println("Instance 1: " + instance1 + " (Hash: " + instance1.hashCode() + ")");
        System.out.println("Instance 2: " + instance2 + " (Hash: " + instance2.hashCode() + ")");
        System.out.println("Are both instances the same? " + (instance1 == instance2));
    }
}
