package Strategy_Pattern.With;

class WorkerRobot extends Robot {

    public WorkerRobot(Walkable w, Talkable t, Flyable f) {
        super(w, t, f);
    }

    @Override
    void projection() {
        System.out.println(
                "I look like a Worker Robot");
    }
}
