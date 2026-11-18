package Mediator_Pattern.Without;

/**
 * Demonstrates the air traffic simulation WITHOUT the Mediator Design Pattern.
 * <p>
 * Notice the pain points:
 * 1. Mesh Topology: Every plane must hold a list of every other plane.
 * 2. High Coupling: Planes are directly dependent on each other.
 * 3. Scalability Nightmare: When a new flight arrives, all existing flights must be manually updated.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("  AIR TRAFFIC SYSTEM - WITHOUT MEDIATOR PATTERN (TIGHT COUPLING) ");
        System.out.println("=================================================================");

        // Step 1: Create 3 flights
        Airplane flight1 = new Airplane("AI-101", "Air India");
        Airplane flight2 = new Airplane("EK-202", "Emirates");
        Airplane flight3 = new Airplane("DL-303", "Delta Airlines");

        flight1.registerNeighbor(flight2);
        flight1.registerNeighbor(flight3);

        flight2.registerNeighbor(flight1);
        flight2.registerNeighbor(flight3);

        flight3.registerNeighbor(flight1);
        flight3.registerNeighbor(flight2);

        Thread user1 = new Thread(() -> {
            try {
                flight1.requestLanding();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flight1.takeOff();
        });


        Thread user2 = new Thread(() -> {
            try {
                flight2.requestLanding();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flight2.takeOff();
        });

        Thread user3 = new Thread(() -> {
            try {
                flight3.requestLanding();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flight3.takeOff();
        });

        user1.start();
        user2.start();
        user3.start();

        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 7: The Problem of Adding a New Flight!
//        System.out.println("\n-----------------------------------------------------------------");
//        Airplane flight4 = new Airplane("BA-404", "British Airways");
//
//        flight4.registerNeighbor(flight1);
//        flight4.registerNeighbor(flight2);
//        flight4.registerNeighbor(flight3);
//
//        flight1.registerNeighbor(flight4);
//        flight2.registerNeighbor(flight4);
//        flight3.registerNeighbor(flight4);
//
//        System.out.println("Updated every single flight. Now 4 flights have 12 direct connections!");
//        System.out.println("Imagine doing this with 100 flights (9,900 connections)! 💥");
//
//        flight4.requestLanding();
    }
}
