package Mediator_Pattern.With;

/**
 * Colleague Abstract Class.
 * <p>
 * Each Airplane holds a reference to the Mediator (AirTrafficControlMediator)
 * instead of holding references to dozens of other Airplane objects.
 * <p>
 * Benefits:
 * - Decoupled: Zero direct dependencies on sibling Airplane classes.
 * - Reusable: Can easily be swapped, tested, or reused with different mediators.
 */
public abstract class Airplane {
    protected final String flightNumber;
    protected final String airline;
    protected AirTrafficControlMediator mediator;
    protected volatile boolean onRunway;

    public Airplane(String flightNumber, String airline) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.onRunway = false;
    }

    /**
     * Injects the mediator into this colleague.
     */
    public void setMediator(AirTrafficControlMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Request clearance to land by communicating ONLY with the mediator.
     */
    public void requestLanding() {
        System.out.println("\n[" + flightNumber + " (" + airline + ")] Contacting Control Tower for landing clearance...");
        if (mediator != null) {
            mediator.requestLanding(this);
        } else {
            System.out.println("  ❌ ERROR: No Control Tower assigned!");
        }
    }

    /**
     * Executes the landing when clearance is granted by the mediator.
     */
    public void land() {
        this.onRunway = true;
        System.out.println("  🛬 [" + flightNumber + "] Touchdown! Occupying runway.");
    }

    /**
     * Takes off and notifies the mediator that the runway is now free.
     */
    public void takeOff() {
        if (!onRunway) {
            System.out.println("\n[" + flightNumber + "] Cannot take off: Flight is not on the runway.");
            return;
        }

        System.out.println("\n[" + flightNumber + " (" + airline + ")] Taking off... 🛫");
        this.onRunway = false;

        if (mediator != null) {
            mediator.notifyTakeOff(this);
        }
    }

    /**
     * Sends a general broadcast message to all other planes through the mediator.
     */
    public void sendMessage(String message) {
        System.out.println("\n[" + flightNumber + "] Sending message to tower: \"" + message + "\"");
        if (mediator != null) {
            mediator.broadcastMessage(message, this);
        }
    }

    /**
     * Hook method to receive notifications from the mediator.
     */
    public abstract void receiveMessage(String message);

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public boolean isOnRunway() {
        return onRunway;
    }
}
