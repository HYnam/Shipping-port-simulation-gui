package portsim.cargo;

public class Container
{
    public int cargo_id;
    public String dest_port;
    public ContainerType type;

    public Container(int id, String destination, ContainerType type)
    {
        cargo_id = id;
        dest_port = destination;
        this.type = type;

        if (id < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }

    public ContainerType getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return super.toString() + "Container" + cargo_id + "to" + dest_port + "[" + type + "]";
    }
}
