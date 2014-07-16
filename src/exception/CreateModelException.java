package exception;

public class CreateModelException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CreateModelException()
    {
	super();
    }

    public CreateModelException(String message)
    {
	super(message);
    }

    public CreateModelException(String message, Throwable cause)
    {
	super(message, cause);
    }

    public CreateModelException(Throwable cause)
    {
	super(cause);
    }
}
