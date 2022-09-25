package server;


import additional.ChessLobby;
import client.Client;
import exception.ConstructionException;
import functional.Game;
import gui.Window;
import networking.protocols.echo.EchoResponse;
import networking.protocols.lobby.LobbyCreationResponse;
import util.Asset;

public class NetworkingGame {
    private Game game;

    private Client client;


    private NetworkingGame(int playerCount, boolean inOrder) {
        game = new Game(playerCount, inOrder);
        setUpWindow();
    }

    public NetworkingGame(int playerCount) {
        this(playerCount, true);
    }

    public NetworkingGame(String host, int port, String lobbyName, int playerCount) throws ConstructionException {

        client = new Client(host, port);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        client.send(new LobbyCreationResponse(ChessLobby.class, lobbyName, playerCount));

    }

    public NetworkingGame(String host, int port, String lobbyName) throws ConstructionException {
        client = new Client(host, port);
        client.send(new EchoResponse(lobbyName, null));
    }

    private void setUpWindow() {
        Asset.setUp();
        new Window(750, 750, this.game);
    }



    public static void main(String[] args) {
        //NetworkingGame networkingGame = new NetworkingGame(4, false);
        try {
            NetworkingGame networkingGame1 = new NetworkingGame("localhost", 123, "test", 4);
        } catch (ConstructionException e) {
            e.printStackTrace();
        }
    }
}
