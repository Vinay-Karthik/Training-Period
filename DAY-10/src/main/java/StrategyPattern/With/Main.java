package StrategyPattern.With;

public class Main {

    public static void main(String[] args) {

        Robot r1 = new CompanionRobot(new NormalWalk(), new NormalTalk(),
                new NoFly()
        );

        Robot r2 = new WorkerRobot(new NoWalk(), new NoTalk(),
                new NormalFly()
        );

        // Sparrow Robot
        Robot r3 = new CompanionRobot(new NormalWalk(), new NormalTalk(),
                new NormalFly()
        );

        // Jet Robot
        Robot r4 = new WorkerRobot(new NormalWalk(), new NormalTalk(),
                new JetFly()
        );

        System.out.println("Companion Robot");
        r1.walk();
        r1.talk();
        r1.fly();
        r1.projection();

        System.out.println("\nWorker Robot");
        r2.walk();
        r2.talk();
        r2.fly();
        r2.projection();

        System.out.println("\nSparrow Robot");
        r3.walk();
        r3.talk();
        r3.fly();

        System.out.println("\nJet Robot");
        r4.walk();
        r4.talk();
        r4.fly();
    }
}
