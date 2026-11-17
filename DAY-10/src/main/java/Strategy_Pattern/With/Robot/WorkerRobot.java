package Strategy_Pattern.With.Robot;

import Strategy_Pattern.With.Fly.Flyable;
import Strategy_Pattern.With.talk.Talkable;
import Strategy_Pattern.With.walk.Walkable;

public class WorkerRobot extends Robot {

    public WorkerRobot(Walkable w, Talkable t, Flyable f) {
        super(w, t, f);
    }

    @Override
    public void projection() {
        System.out.println(
                "I look like a Worker Robot");
    }
}
