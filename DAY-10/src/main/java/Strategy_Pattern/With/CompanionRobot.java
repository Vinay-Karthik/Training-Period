package Strategy_Pattern.With;

class CompanionRobot extends Robot {

    public CompanionRobot(Walkable w, Talkable t, Flyable f) {
        super(w, t, f);
    }

    @Override
    void projection() {
        System.out.println(
                "I look like a Companion Robot"
        );
    }
}
