package portsim.movement;

import portsim.cargo.Cargo;
import java.util.List;

public class CargoMovement extends Movement
// The movement of cargo coming into or out of the port.
{
    long timeMovement;
    // MovementDirection directionOfMovement;
    List<Cargo> cargoToMove;

    public CargoMovement(long time, MovementDirection direction, List<Cargo> cargo)
    // Creates a new cargo movement with the given action time and direction to be undertaken with the given cargo.
    {
        super(MovementDirection.INBOUND, MovementDirection.OUTBOUND);
        this.timeMovement = time;
        // this.directionOfMovement = direction;
        this.cargoToMove = cargo;

        if(time < 0)
        {
            throw new IllegalArgumentException ("Must have time");
        }
    }

    public List<Cargo> getCargo()
    /*
        Returns the cargo that will be moved.
        Adding or removing elements from the returned list should not affect the original list
     */
    {
        return this.cargoToMove;
    }

    @Override
    public String toString()
    /*
        Returns the human-readable string representation of this CargoMovement.
    The format of the string to return is
        DIRECTION CargoMovement to occur at time involving num piece(s) of cargo
     */
    {
        return super.toString() + super.(MovementDirection) + "CargoMovement to occur at " + this.timeMovement + "involving" + this.cargoToMove + "piece(s) of cargo ";
    }
}
