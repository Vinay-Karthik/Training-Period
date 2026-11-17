package Strategy_Pattern.Without;

abstract class Robot {

    void walk() {
        System.out.println("Walking normally");
    }

    void talk() {
        System.out.println("Talking normally");
    }

    abstract void projection();
}
