package portsim.port;

import portsim.ship.Ship;

public abstract class Quay extends Object
/** Quay is a platform lying alongside or projecting into the water where ships are moored for loading or unloading.*/
{
    int QuayId;

    public Quay(int id)
    /** Creates a new Quay with the given ID, with no ship docked at the quay.*/
    {
        this.QuayId = id;

        if (id < 0)
        {
            throw new IllegalArgumentException ();
        }
    }

    public int getId()
    /** Get the id of this quay*/
    {
        return this.QuayId;
    }

    public void shipArrives(Ship ship)
    /** Docks the given ship at the Quay so that the quay becomes occupied.*/
    {

    }


    public Ship shipDeparts()
     /**Removes the current ship docked at the quay. The current ship should be set to null.*/
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


    public boolean isEmpty()
     /**Returns whether a ship is currently docked at this quay.*/
    {
        if(getShip().getImoNumber() == this.QuayId){
            return
        }
    }



    public Ship getShip()
     /**Returns the ship currently docked at the quay.*/
    {
        return Ship;
    }



    @Override
    public String toString()
    /**
    Returns the human-readable string representation of this quay.
        The format of the string to return is

    QuayClass id [Ship: imoNumber]
     */
    {
        return super.toString() + this.getClass().getName() + this.QuayId + "[Ship: " + getShip().getImoNumber() +"]";
    }

}
