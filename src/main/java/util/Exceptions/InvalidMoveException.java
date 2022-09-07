package util.Exceptions;

public class InvalidMoveException extends Exception{
    public InvalidMoveException() {
        super();
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}
