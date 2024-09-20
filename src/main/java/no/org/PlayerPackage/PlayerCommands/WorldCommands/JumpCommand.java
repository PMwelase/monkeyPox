//package no.org.PlayerPackage.PlayerCommands.WorldCommands;
//
//import no.org.PlayerPackage.PlayerCommands.Command;
//import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
//import no.org.PlayerPackage.Player;
//import no.org.Protocols.Response;
//import no.org.World.Position;
//import no.org.World.World;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class JumpCommand extends Command {
//
//    private final int moveValue;
//    private final int staminaCost = 3;
//
//    public JumpCommand(int moveValue) {
//        super("jump");
//        this.moveValue = moveValue;
//    }
//
//    @Override
//    public JSONObject execute(Player player, World world) {
//        String message = "";
//        JSONObject movementResponse = MovementHelper.movePlayer(player, world, moveValue);
//
//
//        if (!StaminaCheck.canPerformAction(player, this)) {
//            message = "Not enough stamina to perform the action.";
//        } else {
//            if (player.isInRoom()) {
//                message = " jumped " + moveValue + " steps " + movementResponse.getString("direction");
//                player.setStamina(player.getStamina() - staminaCost);
//
//                JSONArray newPositionArray = movementResponse.getJSONArray("newPosition");
//
//                int x = newPositionArray.getInt(0);
//                int y = newPositionArray.getInt(1);
//
//                Position position = new Position(x, y);
//                player.setPosition(position);
//
//                System.out.println("Player moved to position: " + x + ", " + y);
//            } else {
//                message = "You are not in a room. You cannot jump.";
//            }
//        }
//
//        Response response = new Response();
//        JSONObject responseObjectJson = response.buildResponse(player, world);
//        responseObjectJson.put("message", message);
//        return responseObjectJson;
//    }
//}
//
//
