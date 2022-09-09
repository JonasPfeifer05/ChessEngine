import functional.Engine;
import gui.Window;
import util.Asset;

public class Main {
    public static void main(String[] args) {
        System.out.println("Huff ist dumm!");
        System.out.println("Huff ist noch immer dumm!");
        System.out.println("Huff ist immer noch dumm!");
        System.out.println("Hello World");

        Engine engine = new Engine(4);

        //engine.board.load("src/main/resources/start_save.txt");
        //engine.board.save("src/main/resources/test_save.txt");

        Asset.setUp();
        new Window(480 / 9 * 16, 480, engine);
    }
}
