package portsim.movement;

import portsim.cargo.Cargo;

import java.util.LinkedList;
import java.util.List;

/** The movement of cargo coming into or out of the port.*/
public class CargoMovement extends Movement
{
    /** the cargo to be moved */
    private List<Cargo> cargoToMove;

    /** Creates a new cargo movement with the given action time and direction to be undertaken with the given cargo.
     * @param time - the time the movement should occur, long type
     * @param direction - the direction of the movement, MovementDirection
     * @param cargo - the cargo to be moved, List type
     * @throws IllegalArgumentException - if time < 0
     * */
    public CargoMovement(long time, MovementDirection direction, List<Cargo> cargo)
    {
        super(time, direction);
        this.cargoToMove = cargo;

        if(time < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Returns the cargo that will be moved.
     * Adding or removing elements from the returned list should not affect the original list.
     * @return all cargo in the movement, List type
     * */
    public List<Cargo> getCargo()
    {
        List<Cargo> result = new LinkedList<>();
        result.addAll(this.cargoToMove);
        return result;
    }

    /** Returns the human-readable string representation of this CargoMovement.
     * The format of the string to return is
     *
     *  DIRECTION CargoMovement to occur at time involving num piece(s) of cargo
     * Where:
     * DIRECTION is the direction of the movement
     * time is the time the movement is meant to occur
     * num is the number of cargo pieces that are being moved
     * For example:
     *
     *  OUTBOUND CargoMovement to occur at 135 involving 5 piece(s) of cargo
     *  */
    @Override
    public String toString()
    {
        return super.toString() + this.getDirection() + "CargoMovement to occur at " + this.getTime() + "involving" + getCargo().size() + "piece(s) of cargo ";
    }
}
