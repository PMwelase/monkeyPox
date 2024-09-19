package no.org.WebApi;

import no.org.Rooms.RoomGrid;
import no.org.ServerCommands.ServerManager;
//import no.org.ServerConnection.MultiServer;
import no.org.World.Position;
import no.org.World.World;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class ServerConfig {

    @Bean
    public ServerSocket serverSocket() throws IOException {
        return new ServerSocket(8080);
    }

    @Bean
    public RoomGrid roomGrid() {
        return new RoomGrid(30, 30);
    }

    @Bean
    public World world(RoomGrid roomGrid) {
        return new World(roomGrid, new Position(0, 0), new Position(30, 30));
    }
//
//    @Bean
//    public MultiServer multiServer(ServerSocket serverSocket, World world) {
//        return new MultiServer(serverSocket, world);
//    }

    @Bean
    public ServerManager serverManager(ServerSocket serverSocket, World world) {
        return new ServerManager(serverSocket, world);
    }
}

