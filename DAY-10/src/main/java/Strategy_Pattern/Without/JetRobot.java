package Strategy_Pattern.Without;

public class JetRobot extends Robot {

    @Override
    void projection() {
        System.out.println("I look like a Jet Robot");
    }

    void fly() {
        System.out.println("Flying using a jet");
    }
}
