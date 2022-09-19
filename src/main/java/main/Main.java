package main;

import functional.Game;
import gui.Window;
import util.Asset;

public class Main {

    public static Window window;

    public static void main(String[] args) {
        System.out.println("Huff ist dumm!");
        System.out.println("Huff ist noch immer dumm!");
        System.out.println("Huff ist immer noch dumm!!!");
        System.out.println("Hello World");

        Game game = new Game(4, false);

        Asset.setUp();
        window = new Window(480 / 9 * 16, 480, game);
    }
}
