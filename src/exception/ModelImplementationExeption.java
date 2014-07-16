package exception;

public class ModelImplementationExeption extends Exception
{
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	public ModelImplementationExeption()
	{
		super();
	}

	public ModelImplementationExeption(String message)
	{
		super(message);
	}

	public ModelImplementationExeption(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ModelImplementationExeption(Throwable cause)
	{
		super(cause);
	}
}
