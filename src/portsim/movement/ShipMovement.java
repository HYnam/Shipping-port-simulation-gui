package portsim.movement;

import portsim.ship.Ship;

public class ShipMovement extends Movement
// The movement of a ship coming into or out of the port.
{
    Ship ShipWaitToMove;

    public ShipMovement(long time, MovementDirection direction, Ship ship)
    // Creates a new ship movement with the given action time and direction to be undertaken with the given ship.
    {
        super(time, direction);
        this.ShipWaitToMove = ship;

        if(time < 0)
        {
            throw new IllegalArgumentException ("Must have time");
        }
    }

    public Ship getShip()
    // Returns the ship undertaking the movement.
    {
        return this.ShipWaitToMove;
    }

    @Override
    public String toString()
    /*
        Returns the human-readable string representation of this ShipMovement.
    The format of the string to return is
        DIRECTION ShipMovement to occur at time involving the ship name
     */
    {
        return super.toString() + this.getDirection() + this.getClass().getName() + "to occur at" + this.getTime() + "involving the ship " + this.getShip();
    }

}
