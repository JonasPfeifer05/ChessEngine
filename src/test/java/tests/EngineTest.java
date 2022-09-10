package tests;

import functional.Engine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    Engine engine;

    @BeforeEach
    void setUp() {
        engine = new Engine(4);
    }

    @Test
    void getAllValidMoves() {
        assertEquals(0, engine.getAllValidMoves(new Position(3,3)).size());
        assertEquals(0, engine.getAllValidMoves(new Position(-1,0)).size());
        assertEquals(0, engine.getAllValidMoves(new Position(0,3)).size());

        assertEquals(2, engine.getAllValidMoves(new Position(3,1)).size());
        assertEquals(2, engine.getAllValidMoves(new Position(1,3)).size());
    }

    @Test
    void move() {
        assertThrows(IndexOutOfBoundsException.class, () -> engine.move(new Position(3,0), new Position(-1,0)));
        assertThrows(IndexOutOfBoundsException.class, () -> engine.move(new Position(0,0), new Position(3,0)));

        assertDoesNotThrow(() -> engine.move(new Position(1,3), new Position(3,3)));
        assertDoesNotThrow(() -> engine.move(new Position(3,3), new Position(4,3)));
    }

    //TODO Test moving figure around and special moves
}