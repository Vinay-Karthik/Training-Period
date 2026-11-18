package State_Pattern.Without;

public class MediaPlayer {
    private PlayerState state;

    public MediaPlayer() {
        this.state = PlayerState.STOPPED;
    }

    public void pressPlay() {
        switch (state) {
            case STOPPED:
                System.out.println("Starting playback from the beginning.");
                state = PlayerState.PLAYING;
                break;
            case PLAYING:
                System.out.println("Already playing track.");
                break;
            case PAUSED:
                System.out.println("Resuming playback.");
                state = PlayerState.PLAYING;
                break;
        }
    }

    public void pressPause() {
        switch (state) {
            case PLAYING:
                System.out.println("Pausing playback.");
                state = PlayerState.PAUSED;
                break;
            case PAUSED:
                System.out.println("Already paused.");
                break;
            case STOPPED:
                System.out.println("Cannot pause: Media player is currently stopped.");
                break;
        }
    }

    public void pressStop() {
        switch (state) {
            case PLAYING:
            case PAUSED:
                System.out.println("Stopping playback.");
                state = PlayerState.STOPPED;
                break;
            case STOPPED:
                System.out.println("Already stopped.");
                break;
        }
    }

    public PlayerState getState() {
        return state;
    }
}
