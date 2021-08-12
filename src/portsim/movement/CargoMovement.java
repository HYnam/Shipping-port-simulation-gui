package portsim.movement;

import portsim.cargo.Cargo;
import java.util.List;

public class CargoMovement extends Movement
// The movement of cargo coming into or out of the port.
{
    long timeMovement;
    MovementDirection directionOfMovement;
    List<Cargo> cargoToMove;

    public CargoMovement(long time, MovementDirection direction, List<Cargo> cargo)
    // Creates a new cargo movement with the given action time and direction to be undertaken with the given cargo.
    {
        this.timeMovement = time;
        this.directionOfMovement = direction;
        this.cargoToMove = cargo;

        if(time < 0)
        {
            throw new IllegalArgumentException ("Must have time");
        }
    }
}
