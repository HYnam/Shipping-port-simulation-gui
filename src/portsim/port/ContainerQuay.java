package portsim.port;

/** A Container Quay is a type of quay specifically designed for the unloading of Container Ship vessels. */
public class ContainerQuay extends Quay
{
    /** maximum number of containers the quay can handle */
    public int MaxContainerQuayHandle;

    /** Creates a new Container Quay with the given ID and maximum number of containers.
     * Parameters:
     * id - quay ID
     * maxContainers - maximum number of containers the quay can handle
     * Throws:
     * IllegalArgumentException - if ID or maxContainers < 0
     * */
    public ContainerQuay(int id, int maxContainers)
    {
        super(id);
        this.MaxContainerQuayHandle = maxContainers;

        if(id < 0 || maxContainers < 0)
        {
            throw new IllegalArgumentException ("ID or maxContainers must be greater than zero");
        }
    }

    /** Returns the maximum number of containers of this quay can process at once.
     * Return: maxContainers
     * */
    public int getMaxContainers()
    {
        return this.MaxContainerQuayHandle;
    }

    @Override
    public String toString()
    /*
    Returns the human-readable string representation of this ContainerQuay.
    The format of the string to return is

        ContainerQuay id [Ship: imoNumber] - maxContainers
     */
    {
        return super.toString() + "ContainerQuay " + this.QuayId + "[Ship: " + this.getShip() + "] - " + this.getMaxContainers();
    }
}
