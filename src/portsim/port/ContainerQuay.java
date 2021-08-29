package portsim.port;

/** A Container Quay is a type of quay specifically designed for the unloading of Container Ship vessels. */
public class ContainerQuay extends Quay
{
    /** maximum number of containers the quay can handle */
    private int MaxContainerQuayHandle;

    /** Creates a new Container Quay with the given ID and maximum number of containers.
     * Parameters:
     * id - quay ID
     * maxContainers - maximum number of containers the quay can handle
     * Throws:
     * IllegalArgumentException - if ID or maxContainers < 0
     * */
    public ContainerQuay(int id, int maxContainers)
    {
        super(id); // Get the value id in Quay class
        this.MaxContainerQuayHandle = maxContainers;

        if(id < 0 || maxContainers < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Returns the maximum number of containers of this quay can process at once.
     * Return: maxContainers
     * */
    public int getMaxContainers()
    {
        return this.MaxContainerQuayHandle;
    }

    /** Returns the human-readable string representation of this ContainerQuay.
     * The format of the string to return is
     *
     * ContainerQuay id [Ship: imoNumber] - maxContainers
     * Where:
     * id is the ID of this quay
     * imoNumber is the IMO number of the ship docked at this quay, or None if the quay is unoccupied.
     * maxContainers is the number of containers this quay can take.
     * For example:
     *
     * ContainerQuay 3 [Ship: 22] - 32
     * */
    @Override
    public String toString()
    {
        return super.toString() + "ContainerQuay " + getId() + "[Ship: " + getShip().getImoNumber() + "] - " + this.getMaxContainers();
    }
}
