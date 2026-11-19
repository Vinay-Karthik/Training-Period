package Strategy_Pattern.with_abstract.Robot;

import Strategy_Pattern.with_abstract.Fly.Flyable;
import Strategy_Pattern.with_abstract.talk.Talkable;
import Strategy_Pattern.with_abstract.walk.Walkable;

public class CompanionRobot extends Robot {

    public CompanionRobot(Walkable w, Talkable t, Flyable f) {
        super(w, t, f);
    }

    @Override
    public void projection() {
        System.out.println(
                "I look like a Companion Robot"
        );
    }
}
