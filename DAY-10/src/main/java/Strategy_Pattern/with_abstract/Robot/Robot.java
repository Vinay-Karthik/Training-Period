package Strategy_Pattern.with_abstract.Robot;

import Strategy_Pattern.with_abstract.Fly.Flyable;
import Strategy_Pattern.with_abstract.talk.Talkable;
import Strategy_Pattern.with_abstract.walk.Walkable;

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
