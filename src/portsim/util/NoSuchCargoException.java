package portsim.util;

public class NoSuchCargoException extends Exception
// Exception thrown when a ship that is already unloaded is attempted to be unloaded
{
    public NoSuchCargoException() throws NoSuchCargoException
    // Constructs a new NoSuchCargoException with no detail message or cause
    {
        throw new NoSuchCargoException();
    }

    public NoSuchCargoException(String message)
    // Constructs a NoSuchCargoException that contains a helpful detail message explaining why the exception occurred
    {
        try {
            throw new NoSuchCargoException(message);
        } catch (NoSuchCargoException e) {
            e.printStackTrace();
        }
    }
}
