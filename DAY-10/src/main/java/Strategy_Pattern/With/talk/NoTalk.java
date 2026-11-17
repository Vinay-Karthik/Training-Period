package Strategy_Pattern.With.talk;

public class NoTalk implements Talkable {

    @Override
    public void talk() {
        System.out.println("Cannot talk");
    }
}
