package no.org.WebApi;

import no.org.ServerCommands.ServerManager;
//import no.org.ServerConnection.MultiServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"no.org.ServerConnection", "no.org.WebApi"})
public class MonkeyPoxApplication {

    @Autowired
    private ServerManager serverManager;

    public static void main(String[] args) {
        SpringApplication.run(MonkeyPoxApplication.class, args);
    }

    @Bean
    public CommandLineRunner startServers() {
        return args -> {
            try {
                serverManager.start();
                System.out.println("ServerManager started...");
            } catch (Exception e) {
                System.err.println("Error starting servers: " + e.getMessage());
            }
        };
    }
}

