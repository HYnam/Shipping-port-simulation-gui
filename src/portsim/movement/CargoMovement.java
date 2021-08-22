package portsim.movement;

import portsim.cargo.Cargo;
import java.util.List;

public class CargoMovement extends Movement
/** The movement of cargo coming into or out of the port.*/
{
    List<Cargo> cargoToMove;

    public CargoMovement(long time, MovementDirection direction, List<Cargo> cargo)
    /** Creates a new cargo movement with the given action time and direction to be undertaken with the given cargo.*/
    {
        super(time, direction);
        this.cargoToMove = cargo;

        if(time < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    public List<Cargo> getCargo()
    /**
        Returns the cargo that will be moved.
        Adding or removing elements from the returned list should not affect the original list
     */
    {
        //return this.cargoToMove.add(c);
        return this.cargoToMove;
    }

    @Override
    public String toString()
    /**
        Returns the human-readable string representation of this CargoMovement.
    The format of the string to return is
        DIRECTION CargoMovement to occur at time involving num piece(s) of cargo
     */
    {
        return super.toString() + this.getDirection() + "CargoMovement to occur at " + this.getTime() + "involving" + getCargo() + "piece(s) of cargo ";
    }
}
