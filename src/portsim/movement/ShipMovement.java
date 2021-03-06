package portsim.movement;

import portsim.cargo.Cargo;
import portsim.port.Quay;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;

import java.util.Arrays;

/**
 * The movement of a ship coming into or out of the port.
 *
 * @ass1_partial
 */
public class ShipMovement extends Movement {

    /**
     * The ship entering of leaving the Port
     */
    private Ship ship;

    /**
     * Creates a new ship movement with the given action time and direction
     * to be undertaken with the given ship.
     *
     * @param time      the time the movement should occur
     * @param direction the direction of the movement
     * @param ship      the ship which that is waiting to move
     * @throws IllegalArgumentException if time &lt; 0
     * @ass1
     */
    public ShipMovement(long time, MovementDirection direction, Ship ship) throws IllegalArgumentException {
        super(time, direction);
        if (time < 0){
            throw new IllegalArgumentException();
        }
        this.ship = ship;
    }

    /**
     * Returns the ship undertaking the movement.
     *
     * @return movements ship
     * @ass1
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Returns the human-readable string representation of this ShipMovement.
     * <p>
     * The format of the string to return is
     * <pre>
     * DIRECTION ShipMovement to occur at time involving the ship name </pre>
     * Where:
     * <ul>
     *   <li>{@code DIRECTION} is the direction of the movement </li>
     *   <li>{@code time} is the time the movement is meant to occur </li>
     *   <li>{@code name} is the name of the ship that is being moved</li>
     * </ul>
     * For example:
     * <pre>
     * OUTBOUND ShipMovement to occur at 135 involving the ship Voyager </pre>
     *
     * @return string representation of this ShipMovement
     * @ass1
     */
    @Override
    public String toString() {
        return String.format("%s involving the ship %s",
            super.toString(),
            this.ship.getName());
    }

    /** Returns the machine-readable string representation of this ship movement
     * The format of the string to return is
     * <pre>ShipMovement:time:direction:imoNumber</pre>
     * Where:
     * <ul>
     *     <li>{@code time} is the time that the movement will be actioned</li>
     *     <li>{@code direction} is the direction of the movement</li>
     *     <li>{@code imoNumber} is the imoNumber of the ship that is moving</li>
     * </ul>
     * For example:
     * <pre>ShipMovement:120:INBOUND:1258691</pre>
     *
     * @return encoded string representation of this movement
     * */
    public String encode(){
        return String.format("%s:%d",
                super.encode(),
                this.ship.getImoNumber());
    }

    /** Creates a ship movement from a string encoding
     *
     * The format of the string should match the encoded representation of a ship
     * movement, as described in encode().
     *
     * The encoded string is invalid if any of the following conditions are ture:
     * <ul>
     *     <li>The number of colon(:) detected was more/fewer than expected</li>
     *     <li>The time is not a long (i.e. cannot be parsed by {@code Long.parseLong(String)}</li>
     *     <li>The time is less than zero</li>
     *     <li>The movementDirection is not one of the valid direction</li>
     *     <li>The imoNumber is not long (i.e. cannot be parsed by {@code Long.parseLong(String)}</li>
     *     <li>The imoNumber is less than zero</li>
     *     <li>There is no ship that exists with the specific imoNumber</li>
     * </ul>
     *
     * @param string string containing the encoded ShipMovement
     * @return decoded ShipMovement instance
     * @throws BadEncodingException if the format of the given string is invalid according to the
     * rules above
     * */
    public static ShipMovement fromString(String string) throws BadEncodingException{
        String[] listOfStrings = string.split(":"); // Split wherever we see ":"
        if (listOfStrings.length != 4) {
            throw new BadEncodingException();
        }
        try{    // Try convert String to long for the time
            Long.parseLong(listOfStrings[1]);
            Long.parseLong(listOfStrings[3]);
        } catch (Exception e){
            throw new BadEncodingException();
        }
        if (!Ship.shipExists(Long.parseLong(listOfStrings[3]))){
            throw new BadEncodingException();
        }
        if (Long.parseLong(listOfStrings[1]) < 0 || Long.parseLong(listOfStrings[3]) < 0){
            throw new BadEncodingException();
        } else if (!Arrays.toString(MovementDirection.values()).equals(listOfStrings[2])) {
            throw new BadEncodingException();
        }

        return new ShipMovement(130, MovementDirection.OUTBOUND,
                new Ship(1234567, "Peter", "Hong Kong", NauticalFlag.HOTEL) {
            @Override
            public boolean canDock(Quay quay) {
                return false;
            }
            @Override
            public boolean canLoad(Cargo cargo) {
                return false;
            }
            @Override
            public void loadCargo(Cargo cargo) {
            }
        });
    }
}
