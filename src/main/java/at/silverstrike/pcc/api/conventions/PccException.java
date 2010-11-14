package at.silverstrike.pcc.api.conventions;

public class PccException extends Exception {
	private static final long serialVersionUID = 1L;

	public PccException(final String aMessage)
	{
		super(aMessage);
	}
	public PccException(final Throwable aThrowable)
	{
		super(aThrowable);
	}
}
