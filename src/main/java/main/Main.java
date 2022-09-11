package main;

import functional.Game;
import functional.figure.figures.Queen;
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

        Game game = new Game(4, false);

        game.engine.board.set(new Position(7, 1), null);

        game.engine.board.set(new Position(7, 2), new Queen(Player.PLAYER4));

        game.move(new Position(8,1), new Position(8,2));

        Asset.setUp();
        new Window(480 / 9 * 16, 480, game);
    }
}
