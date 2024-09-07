package no.org.ServerCommands;

import no.org.World.World;

import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerManager extends Thread {

    private final Scanner scanner = new Scanner(System.in);
    private final ServerSocket serverSocket;
    private World world;

    public ServerManager(ServerSocket serverSocket, World world){
        this.serverSocket = serverSocket;
        this.world=world;
        CommandManager.setWorld(this.world);
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()){
            try{
                String command = getCommand();

                CommandManager.create(command,world);

            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getCommand(){
        return scanner.nextLine();
    }
}

