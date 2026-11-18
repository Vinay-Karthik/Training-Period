package State_Pattern.With;

public class StoppedState implements State {
    @Override
    public void pressPlay(MediaPlayer player) {
        System.out.println("Starting playback from the beginning.");
        player.setState(new PlayingState());
    }

    @Override
    public void pressPause(MediaPlayer player) {
        System.out.println("Cannot pause: Media player is currently stopped.");
    }

    @Override
    public void pressStop(MediaPlayer player) {
        System.out.println("Already stopped.");
    }

    @Override
    public String toString() {
        return "STOPPED";
    }
}
