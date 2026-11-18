package Mediator_Pattern.With;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Concrete Mediator: Airport Control Tower.
 * <p>
 * Centralizes all coordination logic:
 * 1. Tracks runway availability.
 * 2. Manages the landing queue (holding pattern) when the runway is occupied.
 * 3. Dispatches notifications between airplanes without them knowing about each other.
 * 4. Automatically clears queued flights when the runway becomes free.
 */
public class ControlTower implements AirTrafficControlMediator {
    private final String towerName;
    private final List<Airplane> registeredAirplanes;
    private final Queue<Airplane> landingQueue;
    private Airplane currentRunwayOccupant;

    public ControlTower(String towerName) {
        this.towerName = towerName;
        this.registeredAirplanes = new ArrayList<>();
        this.landingQueue = new LinkedList<>();
        this.currentRunwayOccupant = null;
    }

    @Override
    public synchronized void registerAirplane(Airplane airplane) {
        if (!registeredAirplanes.contains(airplane)) {
            registeredAirplanes.add(airplane);
            airplane.setMediator(this);
            System.out.println("[" + towerName + "] Registered flight: " + airplane.getFlightNumber() + " (" + airplane.getAirline() + ")");
        }
    }

    @Override
    public synchronized void requestLanding(Airplane airplane) {
        System.out.println("[" + towerName + "] Processing landing request for " + airplane.getFlightNumber() + "...");

        // Case 1: Runway is free
        if (currentRunwayOccupant == null) {
            currentRunwayOccupant = airplane;
            System.out.println("  ✅ [" + towerName + "] Clearance GRANTED for " + airplane.getFlightNumber() + ".");
            airplane.land();

            // Broadcast status to all other planes
            broadcastMessage("⚠️ Runway is now OCCUPIED by flight " + airplane.getFlightNumber(), airplane);
            return;
        }

        // Case 2: Runway is already occupied -> place flight into holding queue
        landingQueue.offer(airplane);
        System.out.println("  ⛔ [" + towerName + "] HOLDING PATTERN: Runway is currently occupied by "
                + currentRunwayOccupant.getFlightNumber() + "!");
        System.out.println("  📋 [" + towerName + "] " + airplane.getFlightNumber()
                + " queued in holding pattern at position #" + landingQueue.size());
    }

    @Override
    public synchronized void notifyTakeOff(Airplane airplane) {
        if (currentRunwayOccupant == airplane) {
            System.out.println("[" + towerName + "] " + airplane.getFlightNumber()
                    + " departed. Runway is now FREE.");
            currentRunwayOccupant = null;

            // Broadcast departure to other planes
            broadcastMessage("ℹ️ Runway is now FREE after departure of " + airplane.getFlightNumber(), airplane);

            // Automatically check and serve the next queued airplane
            if (!landingQueue.isEmpty()) {
                Airplane nextFlight = landingQueue.poll();
                System.out.println("\n[" + towerName + "] 🎯 Automatically clearing next flight from queue: "
                        + nextFlight.getFlightNumber() + "...");
                requestLanding(nextFlight);
            }
        } else {
            System.out.println("[" + towerName + "] ⚠️ Warning: " + airplane.getFlightNumber()
                    + " was not the recorded occupant of the runway.");
        }
    }

    @Override
    public synchronized void broadcastMessage(String message, Airplane sender) {
        for (Airplane plane : registeredAirplanes) {
            if (plane != sender) {
                plane.receiveMessage(message);
            }
        }
    }

    public int getQueueSize() {
        return landingQueue.size();
    }

    public Airplane getCurrentRunwayOccupant() {
        return currentRunwayOccupant;
    }

    public String getTowerName() {
        return towerName;
    }
}
