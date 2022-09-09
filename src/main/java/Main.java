import functional.Engine;
import functional.figure.figures.Rook;
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

        Engine engine = new Engine(4);
        engine.board.set(new Position(2, 3), new Rook(Player.PLAYER1));
        engine.board.set(new Position(3, 2), new Rook(Player.PLAYER1));
        engine.board.set(new Position(10, 11), new Rook(Player.PLAYER1));
        engine.board.set(new Position(11, 10), new Rook(Player.PLAYER1));

        engine.getAllValidMoves(new Position(10, 12));

        Asset.setUp();
        new Window(480 / 9 * 16, 480, engine);
    }
}
