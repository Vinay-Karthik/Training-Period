package Strategy_Pattern.with_abstract.talk;

public class NoTalk extends Talkable {

    @Override
    public void talk() {
        System.out.println("Cannot talk");
    }
}
