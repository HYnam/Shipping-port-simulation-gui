package portsim.util;

/** Exception thrown when a ship that is already unloaded is attempted to be unloaded.*/
public class NoSuchCargoException extends Exception
{
    /** Constructs a new NoSuchCargoException with no detail message or cause
     * See Also:
     * Exception()
     * */
    public NoSuchCargoException() throws NoSuchCargoException
    {
        throw new NoSuchCargoException();
    }

    /** Constructs a NoSuchCargoException that contains a helpful detail message explaining why the exception occurred
     * Parameters:
     * message - detail message
     * See Also:
     * Exception(String)
     * */
    public NoSuchCargoException(String message)
    {
        try {
            throw new NoSuchCargoException(message);
        } catch (NoSuchCargoException e) {
            e.printStackTrace();
        }
    }
}
