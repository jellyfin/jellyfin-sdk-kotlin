package MediaBrowser.Model.IO;

/** 
 Interface IZipClient
*/
public interface IZipClient
{
	/** 
	 Extracts all.
	 
	 @param sourceFile The source file.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAll(String sourceFile, String targetPath, boolean overwriteExistingFiles);

	/** 
	 Extracts all.
	 
	 @param source The source.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAll(Stream source, String targetPath, boolean overwriteExistingFiles);

	/** 
	 Extracts all from7z.
	 
	 @param sourceFile The source file.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAllFrom7z(String sourceFile, String targetPath, boolean overwriteExistingFiles);

	/** 
	 Extracts all from7z.
	 
	 @param source The source.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAllFrom7z(Stream source, String targetPath, boolean overwriteExistingFiles);

	/** 
	 Extracts all from tar.
	 
	 @param sourceFile The source file.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAllFromTar(String sourceFile, String targetPath, boolean overwriteExistingFiles);

	/** 
	 Extracts all from tar.
	 
	 @param source The source.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAllFromTar(Stream source, String targetPath, boolean overwriteExistingFiles);

	/** 
	 Extracts all from rar.
	 
	 @param sourceFile The source file.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAllFromRar(String sourceFile, String targetPath, boolean overwriteExistingFiles);

	/** 
	 Extracts all from rar.
	 
	 @param source The source.
	 @param targetPath The target path.
	 @param overwriteExistingFiles if set to <c>true</c> [overwrite existing files].
	*/
	void ExtractAllFromRar(Stream source, String targetPath, boolean overwriteExistingFiles);
}