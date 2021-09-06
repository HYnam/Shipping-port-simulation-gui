package portsim.movement;

import portsim.ship.Ship;

/** The movement of a ship coming into or out of the port.*/
public class ShipMovement extends Movement
{
    /** the ship which that is waiting to move */
    private Ship shipWaitToMove;

    /** Creates a new ship movement with the given action time and direction to be undertaken with the given ship.
     * @param time - the time the movement should occur, long type
     * @param direction - the direction of the movement, MovementDirection
     * @param ship - the ship which that is waiting to move, Ship type
     * @throws IllegalArgumentException - if time < 0
     * */
    public ShipMovement(long time, MovementDirection direction, Ship ship)
    {
        super(time, direction);
        this.shipWaitToMove = ship;

        if(time < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Returns the ship undertaking the movement.
     * @return movements ship
     * */
    public Ship getShip()
    {
        return this.shipWaitToMove;
    }

    /** Returns the human-readable string representation of this ShipMovement.
     * The format of the string to return is
     *
     *  DIRECTION ShipMovement to occur at time involving the ship name
     * Where:
     * DIRECTION is the direction of the movement
     * time is the time the movement is meant to occur
     * name is the name of the ship that is being moved
     * For example:
     *  OUTBOUND CargoMovement to occur at 135 involving the ship Voyager
     *  */
    @Override
    public String toString()
    {
        return super.toString() + getDirection() + this.getClass().getName() + "to occur at" + getTime() + "involving the ship " + getShip();
    }

}
