package portsim.port;

import portsim.ship.Ship;

/** Quay is a platform lying alongside or projecting into the
 * water where ships are moored for loading or unloading.*/
public abstract class Quay extends Object {
    /** quay ID */
    private int quayId;

    /** the ship that docked to the quay */
    private Ship dockedShip;

    /** Creates a new Quay with the given ID, with no ship docked at the quay.
     * @param id - quay ID, int type
     * @throws IllegalArgumentException - if ID < 0
     * */
    public Quay(int id) {
        this.quayId = id;
        this.dockedShip = null;

        if (id < 0) {
            throw new IllegalArgumentException();
        }
    }

    /** Get the id of this quay
     * @return quay id, int type
     * */
    public int getId() {
        return this.quayId;
    }

    /** Docks the given ship at the Quay so that the quay becomes occupied.
     * @param ship - ship to dock to the quay
     * */
    public void shipArrives(Ship ship) {
        if (this.dockedShip == null) {
            this.dockedShip = ship;
        }
    }

    /**Removes the current ship docked at the quay. The current ship should be set to null.
     * @return the current ship or null if quay is empty.
     * */
    public Ship shipDeparts() {
        if (this.dockedShip == null) {
            return null;
        } else {
            Ship shipToReturn = this.dockedShip;
            this.dockedShip = null;
            return shipToReturn;
        }
    }

    /**Returns whether a ship is currently docked at this quay.
     * @return true if there is no ship docked else false
     * */
    public boolean isEmpty() {
        if (this.dockedShip == null) {
            return true;
        } else {
            return false;
        }
    }

    /**Returns the ship currently docked at the quay.
     * @return ship at quay or null if no ship is docked
     * */
    public Ship getShip() {
        return this.dockedShip;
    }

    /** Returns the human-readable string representation of this quay.
     * The format of the string to return is
     *
     * QuayClass id [Ship: imoNumber]
     * Where:
     * id is the ID of this quay
     * imoNumber is the IMO number of the ship docked at this
     * quay, or None if the quay is unoccupied.
     * For example:
     *
     * BulkQuay 1 [Ship: 23]
     * or
     * ContainerQuay 3 [Ship: None]
     * */
    @Override
    public String toString() {
        if (isEmpty()) {
            return this.getClass().getName() + " " + getId()
                   + "[Ship: None]";
        } else {
            return this.getClass().getName() + " " + getId()
                   + "[Ship: " + getShip().getImoNumber() + "]";
        }
    }

}
