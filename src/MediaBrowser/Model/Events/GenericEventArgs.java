package MediaBrowser.Model.Events;

/** 
 Provides a generic EventArgs subclass that can hold any kind of object
 
 <typeparam name="T"></typeparam>
*/
public class GenericEventArgs<T> extends EventArgs
{
	/** 
	 Gets or sets the argument.
	 
	 <value>The argument.</value>
	*/
	private T Argument;
	public final T getArgument()
	{
		return Argument;
	}
	public final void setArgument(T value)
	{
		Argument = value;
	}

	/** 
	 Initializes a new instance of the <see cref="GenericEventArgs{T}"/> class.
	 
	 @param arg The argument.
	*/
	public GenericEventArgs(T arg)
	{
		setArgument(arg);
	}

	/** 
	 Initializes a new instance of the <see cref="GenericEventArgs{T}"/> class.
	*/
	public GenericEventArgs()
	{
	}
}