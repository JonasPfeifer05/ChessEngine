import functional.Engine;
import functional.Game;
import functional.figure.figures.Pawn;
import functional.figure.figures.Rook;
import functional.figure.special.KillLinked;
import gui.Window;
import util.Asset;
import util.Player;
import util.Position;

public class Main {
    public static void main(String[] args) {
        System.out.println("Huff ist dumm!");
        System.out.println("Huff ist noch immer dumm!");
        System.out.println("Huff ist immer noch dumm!");
        System.out.println("Hello World");

        Game game = new Game(4);

        game.engine.move(new Position(7,12), new Position(7,10));
        game.engine.move(new Position(1,6), new Position(3,6));

        game.move(new Position(5, 1), new Position(5, 2));

        game.getAllValidMoves(new Position(7, 13));

        Asset.setUp();
        new Window(480 / 9 * 16, 480, game);
    }
}
