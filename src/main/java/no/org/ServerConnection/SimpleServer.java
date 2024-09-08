package no.org.ServerConnection;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.CommandFactory;
import no.org.World.Position;
import no.org.World.World;
import no.org.PlayerPackage.Player;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class SimpleServer implements Runnable {
    public static List<SimpleServer> players = new CopyOnWriteArrayList<>();
    public static final int PORT = 5000;
    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final PrintStream out;
    public final String clientID;
    private String name; // Correctly declare the name field
    private final World world;
    private Player player;

    public SimpleServer(Socket socket, World world) {
        this.socket = socket;
        this.world = world;
        this.player = player;
        this.clientID = UUID.randomUUID().toString();

        PrintStream outTemp = null;
        BufferedWriter bufferedWriterTemp = null;
        BufferedReader bufferedReaderTemp = null;

        try {
            outTemp = new PrintStream(socket.getOutputStream());
            bufferedWriterTemp = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReaderTemp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            players.add(this);
            bufferedWriterTemp.flush();
        } catch (IOException e) {
            closeProgram(socket, bufferedReaderTemp, bufferedWriterTemp);
            throw new RuntimeException("Error initializing server", e);
        } finally {
            this.out = outTemp;
            this.bufferedWriter = bufferedWriterTemp;
            this.bufferedReader = bufferedReaderTemp;
        }
    }

    public void closeProgram(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getPlayerName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public Player getPlayer() {
        return player;
    }


    public void run() {
        String messageFromClient;
        this.player = new Player("defaultName", "defaultType", this.world, new Position(0, 0));

        while (!socket.isClosed()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) break;
                JSONObject jsonObject = new JSONObject(messageFromClient);
                String commandName = jsonObject.getString("command");
                String playerName = jsonObject.getString("name");
                JSONArray arguments = jsonObject.getJSONArray("arguments");


                // Print out the received data for debugging
                System.out.println("Command: " + commandName);
                System.out.println("Player Name: " + playerName);
                System.out.println("Arguments: " + arguments);


                if (Objects.equals(player.getName(), "defaultName")) {
                    player.initializePlayer(playerName, arguments.getString(0));  // Initialize the player
                }

                // Create and execute the command using the command name and arguments
                Command command = CommandFactory.createCommand(commandName, arguments);
                JSONObject response = command.execute(this.player, world);

                // Send the response back to the client
                out.println(response.toString());
                out.flush();
            } catch (IOException e) {
                System.out.println("Couldn't get message: " + e.getMessage());
                closeProgram(socket, bufferedReader, bufferedWriter);
                break;
            } catch (org.json.JSONException e) {
                System.out.println("Error parsing JSON: " + e.getMessage());
                // Optionally, send an error response back to the client
            }
        }

    }
}
