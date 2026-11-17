package Strategy_Pattern.With.Robot;

import Strategy_Pattern.With.Fly.Flyable;
import Strategy_Pattern.With.talk.Talkable;
import Strategy_Pattern.With.walk.Walkable;

public abstract class Robot {

    protected Walkable walkable;
    protected Talkable talkable;
    protected Flyable flyable;

    public Robot(Walkable walkable, Talkable talkable, Flyable flyable) {
        this.walkable = walkable;
        this.talkable = talkable;
        this.flyable = flyable;
    }

    public void walk() {
        walkable.walk();
    }

    public void talk() {
        talkable.talk();
    }

    public void fly() {
        flyable.fly();
    }

    public abstract void projection();
}
