package portsim.movement;

public abstract class Movement extends Object
// The movement of ships or cargo coming into or out of the port from land or sea.
{
    long timeOfMoveOccur;
    MovementDirection directionToMove;

    public Movement(long time, MovementDirection direction)
    // Creates a new movement with the given action time and direction
    {
        this.timeOfMoveOccur = time;
        this.directionToMove = direction;

        if (time < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }

    public long getTime()
    // Returns the time the movement should be actioned
    {
        return this.timeOfMoveOccur;
    }

    public MovementDirection getDirection()
    // Returns the direction of the movement.
    {
        return this.directionToMove;
    }

    @Override
    public String toString()
    /*
        Returns the human-readable string representation of this Movement.
    The format of the string to return is
        DIRECTION MovementClass to occur at time
     */
    {
        return super.toString() + this.directionToMove + Movement.class + "to occur at " + this.timeOfMoveOccur;
    }
}
