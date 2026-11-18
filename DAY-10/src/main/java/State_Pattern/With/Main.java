package State_Pattern.With;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Media Player (With State Pattern) ===");
        MediaPlayer player = new MediaPlayer();

        System.out.println("\nInitial State: " + player.getState());

        System.out.println("\nAction: pressPause()");
        player.pressPause();

        System.out.println("\nAction: pressPlay()");
        player.pressPlay();

        System.out.println("\nAction: pressPlay()");
        player.pressPlay();

        System.out.println("\nAction: pressPause()");
        player.pressPause();

        System.out.println("\nAction: pressPlay()");
        player.pressPlay();

        System.out.println("\nAction: pressStop()");
        player.pressStop();

        System.out.println("\nAction: pressStop()");
        player.pressStop();
    }
}
