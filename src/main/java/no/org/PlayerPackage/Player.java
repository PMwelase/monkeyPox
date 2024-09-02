package no.org.PlayerPackage;

import no.org.World.Direction;
import no.org.World.Position;
import no.org.World.World;

public class Player {
    private String name;
    private String type;
    private World world;
    private Position position;
    private Direction currentDirection;

    public Player(String name, String type, World world, Position position, Direction currentDirection) {
        this.name = name;
        this.type = type;
        this.world = world;
        this.position = position;
        this.currentDirection = currentDirection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public World getWorld() {
        return world;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void move(Direction direction) {
        Position newPosition = new Position(position.getX(), position.getY());
        switch (direction) {
            case NORTH:
                newPosition.setY(position.getY() + 1);
                break;
            case SOUTH:
                newPosition.setY(position.getY() - 1);
                break;
            case EAST:
                newPosition.setX(position.getX() + 1);
                break;
            case WEST:
                newPosition.setX(position.getX() - 1);
                break;
        }
        if (newPosition.isIn(world.getTopLeft(), world.getBottomRight())) {
            position = newPosition;
        } else {
            System.out.println("You can't go that way");
        }
    }
}
