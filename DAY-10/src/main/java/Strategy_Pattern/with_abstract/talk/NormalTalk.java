package Strategy_Pattern.with_abstract.talk;

public class NormalTalk extends Talkable {

    @Override
    public void talk() {
        System.out.println("Talking normally");
    }
}
