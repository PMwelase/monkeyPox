//package no.org.ServerCommands;
//
//import no.org.ServerConnection.SimpleServer;
//
//public class PurgeCommand {
//    public void kick(String playerName) {
//        for (SimpleServer client : SimpleServer.players) {
//            if (client.getPlayerName() != null && client.getPlayerName().equals(playerName)) {
//                System.out.println("Player: " + playerName + " has been kicked out.");
//                client.closeProgram(client.getSocket(), client.getBufferedReader(), client.getBufferedWriter());
//                break;
//            }
//        }
//    }
//}