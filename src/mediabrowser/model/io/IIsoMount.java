package mediabrowser.model.io;

/** 
 Interface IIsoMount
*/
public interface IIsoMount extends IDisposable
{
	/** 
	 Gets or sets the iso path.
	 
	 <value>The iso path.</value>
	*/
	String getIsoPath();

	/** 
	 Gets the mounted path.
	 
	 <value>The mounted path.</value>
	*/
	String getMountedPath();
}