package StrategyPattern.Without;

public class SparrowRobot extends Robot {

    @Override
    void projection() {
        System.out.println("I look like a Sparrow Robot");
    }

    void fly() {
        System.out.println("Flying with wings");
    }
}
