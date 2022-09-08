package tests;

import functional.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(4);
    }

    @Test
    void move() {
        assertNull(board.getFigure(new Position(2, 3)));
        board.move(new Position(0,4), new Position(2, 3));
        assertNotNull(board.getFigure(new Position(2, 3)));
    }

    @Test
    void getPlayer() {
        assertNull(board.getFigure(new Position(10,10)));

        assertNotNull(board.getFigure(new Position(0,3)));
        assertNotNull(board.getFigure(new Position(3,0)));
        assertNotNull(board.getFigure(new Position(13,10)));
        assertNotNull(board.getFigure(new Position(10,13)));
    }
}