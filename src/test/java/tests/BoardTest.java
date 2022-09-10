package tests;

import util.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Player;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(4);
    }

    @Test
    void inBound() {
        assertFalse(Board.inBound(new Position(-1,0)));
        assertFalse(Board.inBound(new Position(14,0)));
        assertFalse(Board.inBound(new Position(0,-1)));
        assertFalse(Board.inBound(new Position(0,14)));

        assertTrue(Board.inBound(new Position(3,3)));
        assertTrue(Board.inBound(new Position(3,10)));
        assertTrue(Board.inBound(new Position(10,3)));
        assertTrue(Board.inBound(new Position(10,10)));
    }

    @Test
    void testSetUp() {
        assertNotNull(board.getFigure(new Position(3,0)));
        assertNotNull(board.getFigure(new Position(3,1)));

        assertNotNull(board.getFigure(new Position(0,3)));
        assertNotNull(board.getFigure(new Position(1,3)));

        assertNotNull(board.getFigure(new Position(3,12)));
        assertNotNull(board.getFigure(new Position(3,13)));

        assertNotNull(board.getFigure(new Position(12,3)));
        assertNotNull(board.getFigure(new Position(13,3)));
    }

    @Test
    void move() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.move(new Position(-1,0), new Position(3,3)));
        assertThrows(IndexOutOfBoundsException.class, () -> board.move(new Position(3,3), new Position(-3,3)));

        assertNull(board.getFigure(new Position(2,3)));
        board.move(new Position(0, 4), new Position(2, 3));
        assertNotNull(board.getFigure(new Position(2, 3)));
    }

    @Test
    void getFigure() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.getFigure(new Position(-1,0)));

        assertNull(board.getFigure(new Position(3,3)));
        assertNotNull(board.getFigure(new Position(0,3)));
    }

    @Test
    void getPlayer() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.getPlayer(new Position(-1,0)));

        assertEquals(Player.PLAYER2, board.getPlayer(new Position(3, 13)));
        assertEquals(Player.PLAYER1, board.getPlayer(new Position(3, 0)));
        assertEquals(Player.PLAYER3, board.getPlayer(new Position(13, 3)));
        assertEquals(Player.PLAYER4, board.getPlayer(new Position(0, 3)));
    }
}