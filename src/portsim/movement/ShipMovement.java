package portsim.movement;

import portsim.ship.Ship;

public class ShipMovement extends Movement
// The movement of a ship coming into or out of the port.
{
    long TimeMovementOccur;
    Ship ShipWaitToMove;

    public ShipMovement(long time, MovementDirection direction, Ship ship)
    // Creates a new ship movement with the given action time and direction to be undertaken with the given ship.
    {
        super(MovementDirection.INBOUND, MovementDirection.OUTBOUND);
        this.timeOfMoveOccur = time;
        this.ShipWaitToMove = ship;

        if(time < 0)
        {
            throw new IllegalArgumentException ("Must have time");
        }
    }

}
