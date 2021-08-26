package portsim.port;

import portsim.ship.Ship;

/** Quay is a platform lying alongside or projecting into the water where ships are moored for loading or unloading.*/
public abstract class Quay extends Object
{
    /** quay ID */
    private int QuayId;

    /** Creates a new Quay with the given ID, with no ship docked at the quay.
     * Parameters:
     * id - quay ID
     * Throws:
     * IllegalArgumentException - if ID < 0
     * */
    public Quay(int id)
    {
        this.QuayId = id;

        if (id < 0)
        {
            throw new IllegalArgumentException ();
        }
    }

    /** Get the id of this quay
     * Returns:
     * quay id
     * */
    public int getId()
    {
        return this.QuayId;
    }

    /** Docks the given ship at the Quay so that the quay becomes occupied.
     * Parameters:
     * ship - ship to dock to the quay
     * */
    public void shipArrives(Ship ship)
    {

    }


    /**Removes the current ship docked at the quay. The current ship should be set to null.
     * Returns:
     * the current ship or null if quay is empty.
     * */
    public Ship shipDeparts()
    {
        if(this.QuayId == 0)
        {
            return null;
        }
        else
        {
            return Ship;
        }
    }


    /**Returns whether a ship is currently docked at this quay.
     * Returns:
     * true if there is no ship docked else false
     * */
    public boolean isEmpty()
    {
        if(getShip().getImoNumber() == this.QuayId){
            return
        }
    }



    /**Returns the ship currently docked at the quay.
     * Returns:
     * ship at quay or null if no ship is docked
     * */
    public Ship getShip()
    {
        return Ship;
    }


    /** Returns the human-readable string representation of this quay.
     * The format of the string to return is
     *
     * QuayClass id [Ship: imoNumber]
     * Where:
     * id is the ID of this quay
     * imoNumber is the IMO number of the ship docked at this quay, or None if the quay is unoccupied.
     * For example:
     *
     * BulkQuay 1 [Ship: 23]
     * or
     * ContainerQuay 3 [Ship: None]
     * */
    @Override
    public String toString()
    {
        return super.toString() + this.getClass().getName() + getId() + "[Ship: " + getShip().getImoNumber() +"]";
    }

}
