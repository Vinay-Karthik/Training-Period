package State_Pattern.With;

public class PausedState implements State {
    @Override
    public void pressPlay(MediaPlayer player) {
        System.out.println("Resuming playback.");
        player.setState(new PlayingState());
    }

    @Override
    public void pressPause(MediaPlayer player) {
        System.out.println("Already paused.");
    }

    @Override
    public void pressStop(MediaPlayer player) {
        System.out.println("Stopping playback.");
        player.setState(new StoppedState());
    }

    @Override
    public String toString() {
        return "PAUSED";
    }
}
