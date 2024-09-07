package no.org.ServerConnection;

import no.org.ItemsPackage.Shells;
import no.org.ItemsPackage.Weapons.Shotgun;
import no.org.ServerCommands.ServerManager;
import no.org.World.Position;
import no.org.Rooms.RoomGrid;
import no.org.World.World;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiServer {
    private static RoomGrid roomGrid; // Make RoomGrid static
    private final ServerSocket serverSocket;
    private final World world;
    private final ExecutorService threadPool;

    public MultiServer(ServerSocket serverSocket, World world, int poolSize) {
        this.serverSocket = serverSocket;
        this.world = world;
        this.threadPool = Executors.newFixedThreadPool(poolSize); // Thread pool
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void startServers() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");

                Runnable robot = new SimpleServer(socket, world);
                threadPool.execute(robot);
            }
        } catch (IOException e) {
            System.err.println("I/O error occurred while accepting connection: " + e.getMessage());
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            threadPool.shutdown(); // Properly shut down the thread pool
        } catch (IOException e) {
            System.err.println("Failed to close server socket: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> flags = parseArguments(args);

        int port = Integer.parseInt(flags.getOrDefault("-p", String.valueOf(SimpleServer.PORT)));
        int size = Integer.parseInt(flags.getOrDefault("-s", "5"));

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running & waiting for robots to connect...");

        // Create a 30x30 room grid and assign it to the static variable
        roomGrid = new RoomGrid(30, 30);

        // Initialize the world with the static room grid and proper bounds
        World world = new World(roomGrid, new Position(0, 0), new Position(size, size));
        Shotgun shotgun = new Shotgun(0, 0, "12345");
        world.setShotgunInWorld(shotgun);
        MultiServer servers = new MultiServer(serverSocket, world, 10); // Assuming a thread pool size of 10
        //TODO add ServerManager here
        ServerManager serverManager = new ServerManager(serverSocket, world);

        serverManager.start();
        servers.startServers();
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> flags = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    flags.put(arg, args[i + 1]);
                    i++;
                } else {
                    flags.put(arg, null);
                }
            } else {
                System.out.println("Ignoring invalid argument: " + arg);
            }
        }
        return flags;
    }
}
