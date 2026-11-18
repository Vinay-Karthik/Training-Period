package State_Pattern.With;

public class PlayingState implements State {
    @Override
    public void pressPlay(MediaPlayer player) {
        System.out.println("Already playing track.");
    }

    @Override
    public void pressPause(MediaPlayer player) {
        System.out.println("Pausing playback.");
        player.setState(new PausedState());
    }

    @Override
    public void pressStop(MediaPlayer player) {
        System.out.println("Stopping playback.");
        player.setState(new StoppedState());
    }

    @Override
    public String toString() {
        return "PLAYING";
    }
}
