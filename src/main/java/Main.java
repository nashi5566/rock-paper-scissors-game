
public class Main {
    public static void main(String[] args) {
        game = new Game();
        game.play();
        if (game.who == -1) {
            System.out.println("Draw!");
        } else {
            System.out.format("Player %d win!\n", game.who);

        }
    }
    private static Game game;
}
