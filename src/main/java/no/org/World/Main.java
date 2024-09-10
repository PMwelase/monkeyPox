package no.org.World;

import no.org.Rooms.RoomGrid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class AppConfig {

    @Bean
    public ServerSocket serverSocket() throws IOException {
        int port = 8080; // or read from properties
        return new ServerSocket(port);
    }

    @Bean
    public World world() {
        RoomGrid roomGrid = new RoomGrid(31, 31);
        // Initialize your roomGrid and world
        return new World(roomGrid, new Position(0, 0), new Position(30, 30));
    }
}