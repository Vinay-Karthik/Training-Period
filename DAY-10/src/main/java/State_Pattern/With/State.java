package State_Pattern.With;

public interface State {
    void pressPlay(MediaPlayer player);

    void pressPause(MediaPlayer player);

    void pressStop(MediaPlayer player);
}
