package exceptions;

public class EmptyQueueException extends Exception
{
	/**
	 *  Custom exception class for QueueADT implementations
	 */
	private static final long serialVersionUID = 682267963980463371L;

	public EmptyQueueException()
	{
		super();
	}

	/**
	 * @param message error message specific to cause of error.
	 */
	public EmptyQueueException( String message )
	{
		super( message );
	}
}
