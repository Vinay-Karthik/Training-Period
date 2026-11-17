package Strategy_Pattern.With;

class NoTalk implements Talkable {

    @Override
    public void talk() {
        System.out.println("Cannot talk");
    }
}
