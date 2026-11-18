package Mediator_Pattern.Without;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Airplane in a system WITHOUT the Mediator Pattern.
 * <p>
 * Problem:
 * - Direct Tight Coupling: Every Airplane must hold a direct reference to all other Airplanes.
 * - Mesh Topology: Communication is N-to-N (Many-to-Many).
 * - Scalability Issue: When a new Airplane joins, every existing Airplane must be updated.
 * - Single Responsibility Violation: Airplanes are responsible for flying AND for coordinating
 * traffic, checking runway availability, and broadcasting status to peers.
 * - Concurrency Nightmare: There is only ONE runway, but each Airplane synchronized on itself,
 * so two planes could "land" at the same time. Fixing this requires every plane to agree on
 * a shared lock — which is exactly the coordination job a Mediator should do.
 */
public class Airplane {
    // There is only ONE runway shared by ALL airplanes, so the lock must be shared too.
    private static final Object RUNWAY_LOCK = new Object();

    private final String flightNumber;
    private final String airline;
    // Direct references to all other airplanes (Tight Coupling!)
    private final List<Airplane> otherAirplanes;
    private volatile boolean onRunway;

    public Airplane(String flightNumber, String airline) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.onRunway = false;
        this.otherAirplanes = new ArrayList<>();
    }

    /**
     * Manually link this airplane to another airplane.
     * In a mesh network of N planes, this must be called N * (N - 1) times!
     */
    public void registerNeighbor(Airplane airplane) {
        if (airplane != this && !otherAirplanes.contains(airplane)) {
            otherAirplanes.add(airplane);
        }
    }

    /**
     * Request landing by directly querying all known airplanes.
     * Waits (blocks) until the runway becomes free instead of giving up.
     */
    public void requestLanding() {
        System.out.println("\n[" + flightNumber + " (" + airline + ")] Requesting landing...");

        synchronized (RUNWAY_LOCK) {
            // Check-and-set must be atomic across ALL airplanes, not just this one.
            while (true) {
                Airplane occupant = null;
                for (Airplane neighbor : otherAirplanes) {
                    if (neighbor.isOnRunway()) {
                        occupant = neighbor;
                        break;
                    }
                }

                if (occupant == null) {
                    // Runway is free — occupy it while still holding the shared lock.
                    this.onRunway = true;
                    System.out.println(flightNumber + " landed successfully!");
                    break;
                }

                System.out.println("Runway occupied by " + occupant.getFlightNumber() + "! " +
                        flightNumber + " holding pattern...");
                try {
                    RUNWAY_LOCK.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(flightNumber + " landing request interrupted.");
                    return;
                }
            }

            // Broadcast to every individual peer manually
            for (Airplane neighbor : otherAirplanes) {
                neighbor.receiveNotification(flightNumber + ": I have landed and runway is now OCCUPIED!");
            }
        }
    }

    /**
     * Take off and notify all other airplanes directly.
     */
    public void takeOff() {
        synchronized (RUNWAY_LOCK) {
            if (!onRunway) {
                System.out.println("\n[" + flightNumber + "] Cannot take off: Airplane is not on runway.");
                return;
            }

            this.onRunway = false;
            System.out.println("\n[" + flightNumber + " (" + airline + ")] Taking off from runway... 🛫");

            // Wake up every plane waiting for the runway
            RUNWAY_LOCK.notifyAll();

            // Manually alert every peer that the runway is free
            for (Airplane neighbor : otherAirplanes) {
                neighbor.receiveNotification("ℹ️ UPDATE from " + flightNumber + ": Departed! Runway is now FREE.");
            }
        }
    }

    /**
     * Receive direct peer-to-peer notification.
     */
    public void receiveNotification(String message) {
        System.out.println("    [" + flightNumber + " Radio] Received: " + message);
    }

    public boolean isOnRunway() {
        return onRunway;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public int getNeighborCount() {
        return otherAirplanes.size();
    }
}
