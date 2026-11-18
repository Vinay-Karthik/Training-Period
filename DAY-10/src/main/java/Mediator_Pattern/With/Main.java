package Mediator_Pattern.With;

/**
 * Demonstrates the air traffic simulation WITH the Mediator Design Pattern.
 * <p>
 * Notice the key advantages:
 * 1. Star Topology: Colleagues (Airplanes) only know the Mediator (ControlTower).
 * 2. Loose Coupling: Zero direct dependencies between airplanes (100% decoupled).
 * 3. Centralized Traffic Logic: Runway occupancy, holding queue, and clearance are
 * encapsulated cleanly inside the ControlTower mediator.
 * 4. Effortless Extensibility: Adding a new flight only requires registering it with the tower (O(1)).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   AIR TRAFFIC SYSTEM - WITH MEDIATOR PATTERN (LOOSE COUPLING)   ");
        System.out.println("=================================================================");

        // Step 1: Create the Mediator (Central Control Tower)
        System.out.println("\n--- [PHASE 1] Initializing Control Tower ---");
        ControlTower jfkTower = new ControlTower("JFK Control Tower");

        // Step 2: Create Colleague Airplanes (Commercial and Cargo)
        System.out.println("\n--- [PHASE 2] Registering Flights with the Tower ---");
        Airplane flight1 = new CommercialAirplane("AI-101", "Air India", 250);
        Airplane flight2 = new CommercialAirplane("EK-202", "Emirates", 380);
        Airplane flight3 = new CargoAirplane("FDX-707", "FedEx Cargo", 45.5);

        // Register each flight with the tower (O(1) operation per plane, zero peer-to-peer linking!)
        jfkTower.registerAirplane(flight1);
        jfkTower.registerAirplane(flight2);
        jfkTower.registerAirplane(flight3);

        System.out.println("All flights registered with JFK Tower. No flight knows about other flights!");

        // Step 3: All flights request landing CONCURRENTLY, each on its own thread.
        // The ControlTower (synchronized mediator) serializes access to the single runway
        // and queues whoever arrives while it is occupied — no plane coordinates with another.
        System.out.println("\n--- [PHASE 3] Concurrent Landing Requests (Multithreaded) ---");
        Thread t1 = flightThread(flight1, 0);
        Thread t2 = flightThread(flight2, 300);
        Thread t3 = flightThread(flight3, 600);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 4: Effortless Extensibility - Adding a new flight to the airspace
        System.out.println("\n--- [PHASE 4] Adding a New Flight 'DL-303' to Airspace ---");
        Airplane flight4 = new CommercialAirplane("DL-303", "Delta Airlines", 180);
        // Only 1 line needed: register with the mediator! No need to touch flight1, flight2, or flight3!
        jfkTower.registerAirplane(flight4);

        // Step 5: New flight operates on its own thread too
        System.out.println("\n--- [PHASE 5] New Flight DL-303 Requests Landing ---");
        Thread t4 = flightThread(flight4, 0);
        t4.start();
        try {
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 6: Broadcast message demonstration
        System.out.println("\n--- [PHASE 6] Flight Communication via Mediator ---");
        flight4.sendMessage("Reporting light turbulence at 15,000 feet near sector 4.");

        System.out.println("\n=================================================================");
        System.out.println("  SUMMARY OF BENEFITS WITH MEDIATOR PATTERN:");
        System.out.println("  ✅ Decoupled Colleagues: Airplanes have NO knowledge of each other.");
        System.out.println("  ✅ Star Topology: Replaced N*(N-1) mesh connections with N connections.");
        System.out.println("  ✅ Centralized Control: Runway queue & clearance logic lives in one place.");
        System.out.println("  ✅ Thread-Safe by Design: One synchronized mediator guards the shared runway.");
        System.out.println("  ✅ Single Responsibility: Airplanes only fly; Mediator manages traffic.");
        System.out.println("  ✅ Easy Maintenance: Adding or removing flights requires zero changes to peers.");
        System.out.println("=================================================================");
    }

    /**
     * Simulates one flight's lifecycle on its own thread:
     * request landing -> wait in queue until cleared -> turnaround -> take off.
     *
     * @param flight     the airplane to operate
     * @param startDelay ms to wait before requesting landing (staggers concurrent requests)
     */
    private static Thread flightThread(Airplane flight, long startDelay) {
        return new Thread(() -> {
            try {
                Thread.sleep(startDelay);
                flight.requestLanding();

                // The tower may have placed us in the holding queue.
                // Wait until the mediator actually clears us onto the runway.
                while (!flight.isOnRunway()) {
                    Thread.sleep(100);
                }

                Thread.sleep(1500); // turnaround time on the runway
                flight.takeOff();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Flight-" + flight.getFlightNumber());
    }
}
