package Strategy_Pattern.With.talk;


public class NormalTalk implements Talkable {

    @Override
    public void talk() {
        System.out.println("Talking normally");
    }
}
