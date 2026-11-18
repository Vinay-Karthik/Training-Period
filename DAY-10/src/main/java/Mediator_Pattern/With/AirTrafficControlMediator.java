package Mediator_Pattern.With;

/**
 * Mediator Interface.
 * <p>
 * Defines the contract for communication between Colleagues (Airplanes).
 * Instead of airplanes communicating directly with each other, they
 * communicate exclusively through this interface.
 */
public interface AirTrafficControlMediator {

    void registerAirplane(Airplane airplane);

    void requestLanding(Airplane airplane);

    void notifyTakeOff(Airplane airplane);

    void broadcastMessage(String message, Airplane sender);
}
