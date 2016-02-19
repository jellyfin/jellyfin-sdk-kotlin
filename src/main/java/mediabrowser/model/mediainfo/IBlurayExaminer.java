package mediabrowser.model.mediainfo;

/** 
 Interface IBlurayExaminer
*/
public interface IBlurayExaminer
{
	/** 
	 Gets the disc info.
	 
	 @param path The path.
	 @return BlurayDiscInfo.
	*/
	BlurayDiscInfo GetDiscInfo(String path);
}