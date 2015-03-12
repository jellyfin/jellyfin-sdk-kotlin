package mediabrowser.model.dlna;

public interface ILocalPlayer
{
	/** 
	 Determines whether this instance [can access file] the specified path.
	 
	 @param path The path.
	 @return <c>true</c> if this instance [can access file] the specified path; otherwise, <c>false</c>.
	*/
	boolean CanAccessFile(String path);
	/** 
	 Determines whether this instance [can access directory] the specified path.
	 
	 @param path The path.
	 @return <c>true</c> if this instance [can access directory] the specified path; otherwise, <c>false</c>.
	*/
	boolean CanAccessDirectory(String path);
	/** 
	 Determines whether this instance [can access URL] the specified URL.
	 
	 @param url The URL.
	 @param requiresCustomRequestHeaders if set to <c>true</c> [requires custom request headers].
	 @return <c>true</c> if this instance [can access URL] the specified URL; otherwise, <c>false</c>.
	*/
	boolean CanAccessUrl(String url, boolean requiresCustomRequestHeaders);
}