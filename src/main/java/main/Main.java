package main;

import functional.Engine;
import functional.figure.figures.Pawn;
import functional.figure.figures.Rook;
import functional.figure.special.KillLinked;
import gui.Window;
import util.Asset;
import util.Player;
import util.Position;

public class Main {

    public static Window window;

    public static void main(String[] args) {
        System.out.println("Huff ist dumm!");
        System.out.println("Huff ist noch immer dumm!");
        System.out.println("Huff ist immer noch dumm!");
        System.out.println("Hello World");

        Engine engine = new Engine(4);

        Asset.setUp();
        window = new Window(480 / 9 * 16, 480, engine);
    }
}
