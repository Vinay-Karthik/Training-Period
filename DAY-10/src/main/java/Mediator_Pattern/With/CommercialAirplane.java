package Mediator_Pattern.With;

/**
 * Concrete Colleague 1: Passenger / Commercial Airplane.
 */
public class CommercialAirplane extends Airplane {
    private final int passengerCount;

    public CommercialAirplane(String flightNumber, String airline, int passengerCount) {
        super(flightNumber, airline);
        this.passengerCount = passengerCount;
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("    [Commercial Flight " + flightNumber + " (" + passengerCount + " passengers)] Radio Inbound: " + message);
    }

    public int getPassengerCount() {
        return passengerCount;
    }
}
