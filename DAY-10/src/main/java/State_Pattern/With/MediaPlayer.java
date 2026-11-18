package State_Pattern.With;

public class MediaPlayer {
    private State state;

    public MediaPlayer() {
        // Initial state is Stopped
        this.state = new StoppedState();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void pressPlay() {
        state.pressPlay(this);
    }

    public void pressPause() {
        state.pressPause(this);
    }

    public void pressStop() {
        state.pressStop(this);
    }
}
