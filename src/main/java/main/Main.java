package main;

import exception.ConstructionException;
import functional.Game;
import gui.Window;
import server.NetworkingGame;
import util.Asset;
import util.Player;

public class Main {
    public static void main(String[] args) {
        /*
        System.out.println("Huff ist dumm!");
        System.out.println("Huff ist noch immer dumm!");
        System.out.println("Huff ist immer noch dumm!");
        System.out.println("Hello World");

        Game game = new Game(4, true);

        Asset.setUp();
        new Window(480 / 9 * 16, 480, game);
         */

        try {
            new NetworkingGame("localhost", 123, "test", 4);
        } catch (ConstructionException e) {
            throw new RuntimeException(e);
        }
    }
}
