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
	private T privateArgument;
	public final T getArgument()
	{
		return privateArgument;
	}
	public final void setArgument(T value)
	{
		privateArgument = value;
	}
}