package no.org.ClientPackage;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

import static no.org.ClientPackage.LaunchCommand.launch;
import static no.org.Protocols.Request.getJsonObject;

public class ClientConnection {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintStream out = null;

        try {
            socket = new Socket("localhost", 5000);
            out = new PrintStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            JSONObject launch = launch();
            String name = launch.getString("name");

            out.println(launch.toString());
            out.flush();

            String messageFromServer = in.readLine();
            System.out.println(messageFromServer);

            while (true) {
                JSONObject requestObject = getJsonObject(name);
                out.println(requestObject.toString());

                messageFromServer = in.readLine();
                JSONObject message = new JSONObject(messageFromServer);
                System.out.println("Received from server: " + message.toString(4));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
