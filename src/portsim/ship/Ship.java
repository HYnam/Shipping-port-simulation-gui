package portsim.ship;

import portsim.cargo.Cargo;
import portsim.port.Quay;

/** Represents a ship whose movement is managed by the system
 * Ships store various types of cargo which can be loaded and unloaded at a port*/
public abstract class Ship extends Object {
    /** unique identifier */
    private long identifier;

    /** name of the ship */
    private String nameOfShip;

    /** port of origin */
    private String portOfOrigin;

    /** The nautical flag this ship is flying */
    private NauticalFlag flagShipFlying;

    /**Creates a new ship with the given IMO number, name, origin port flag and nautical flag
     * @param imoNumber - unique identifier, long type
     * @param name - name of the ship, String type
     * @param originFlag - port of origin, String type
     * @param flag - the nautical flag this ship is flying, NauticalFlag
     * @throws IllegalArgumentException - if imoNumber < 0 or
     * imoNumber is not 7 digits long (no leading zero's [0])
     * */
    public Ship(long imoNumber, String name, String originFlag, NauticalFlag flag) {
        this.identifier = imoNumber;
        this.nameOfShip = name;
        this.portOfOrigin = originFlag;
        this.flagShipFlying = flag;

        // Change long type to String type
        String longAsString = Long.toString(imoNumber);

        if (imoNumber < 0 || longAsString.length() != 7)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Check if this ship can dock with the specified quay
     * according to the conditions determined by the ships type
     * @param quay - quay to be checked
     * @return true if the Quay satisfies the conditions else false
     * */
    public abstract boolean canDock(Quay quay);

    /** Checks if the specified cargo can be loaded onto the ship
     * according to the conditions determined by the ships type
     * @param cargo - cargo to be loaded
     * @return true if the Cargo satisfies the conditions else false
     * */
    public abstract boolean canLoad(Cargo cargo);

    /** Loads the specified cargo onto the ship
     * @param cargo - cargo to be loaded
     * Requires:
     * Cargo given is able to be loaded onto this ship according to the
     *              implementation of canLoad(Cargo)
     * */
    public abstract void loadCargo(Cargo cargo);

    /** Return this ship's name.
     * @return name, String type
     * */
    public String getName() {
        return this.nameOfShip;
    }

    /** Return this ship's IMO number
     * @return imoNumber, long type
     * */
    public long getImoNumber() {
        return this.identifier;
    }

    /** Returns this ship's flag denoting its origin.
     * @return originFlag, String type
     * */
    public String getOriginFlag() {
        return this.portOfOrigin;
    }

    /** Returns the nautical flag the ship is flying
     * @return flag, NauticalFlag
     * */
    public NauticalFlag getFlag() {
        return this.flagShipFlying;
    }

    /**Returns the human-readable string representation of this Ship.
     * The format of the string to return is
     *
     * ShipClass name from origin [flag]
     * Where:
     * ShipClass is the Ship class
     * name is the name of this ship
     * origin is the country of origin of this ship
     * flag is the nautical flag of this ship
     * For example:
     * Ship Evergreen from Australia [BRAVO]
     * */
    @Override
    public String toString() {
        return super.toString() + this.getClass().getName() + this.getName() +
                "from " + this.getOriginFlag() + "[" + this.getFlag() + "]";
    }
}
