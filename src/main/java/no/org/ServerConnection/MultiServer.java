//package no.org.ServerConnection;
//
//import no.org.World.World;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Component
//public class MultiServer {
//
//    private final ServerSocket serverSocket;
//    private final World world;
//    private final ExecutorService threadPool;
//
//    public MultiServer(ServerSocket serverSocket, World world) {
//        this.serverSocket = serverSocket;
//        this.world = world;
//        this.threadPool = Executors.newFixedThreadPool(10);
//    }
//
//    public void startServers() {
//        try {
//            while (!serverSocket.isClosed()) {
//                Socket socket = serverSocket.accept();
//                System.out.println("A new client has connected!");
//
//                Runnable robot = new SimpleServer(socket, world);
//                threadPool.execute(robot);
//            }
//        } catch (IOException e) {
//            System.err.println("I/O error occurred while accepting connection: " + e.getMessage());
//            closeServerSocket();
//        }
//    }
//
//    public void closeServerSocket() {
//        try {
//            if (serverSocket != null && !serverSocket.isClosed()) {
//                serverSocket.close();
//            }
//            threadPool.shutdown();
//        } catch (IOException e) {
//            System.err.println("Failed to close server socket: " + e.getMessage());
//        }
//    }
//
//}
