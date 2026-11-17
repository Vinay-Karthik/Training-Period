package Strategy_Pattern.Without;

public class Main {

    public static void main(String[] args) {

        Robot r1 = new CompanionRobot();
        Robot r2 = new WorkerRobot();

        r1.walk();
        r1.talk();
        r1.projection();

        r2.walk();
        r2.talk();
        r2.projection();

        SparrowRobot r3 = new SparrowRobot();

        r3.walk();
        r3.talk();
        r3.fly();
        r3.projection();

        Robot r4 = new WalkingRobot();
        r4.projection();
        r4.walk();
        r4.talk();
    }
}
