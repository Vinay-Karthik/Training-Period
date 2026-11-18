package Mediator_Pattern.With;

/**
 * Concrete Colleague 2: Cargo / Freight Airplane.
 */
public class CargoAirplane extends Airplane {
    private final double cargoWeightInTons;

    public CargoAirplane(String flightNumber, String airline, double cargoWeightInTons) {
        super(flightNumber, airline);
        this.cargoWeightInTons = cargoWeightInTons;
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("    [Cargo Flight " + flightNumber + " (" + cargoWeightInTons + " tons cargo)] Radio Inbound: " + message);
    }

    public double getCargoWeightInTons() {
        return cargoWeightInTons;
    }
}
