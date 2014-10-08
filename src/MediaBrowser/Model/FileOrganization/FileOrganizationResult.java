package MediaBrowser.Model.FileOrganization;

public class FileOrganizationResult
{
	/** 
	 Gets or sets the result identifier.
	 
	 <value>The result identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	/** 
	 Gets or sets the original path.
	 
	 <value>The original path.</value>
	*/
	private String OriginalPath;
	public final String getOriginalPath()
	{
		return OriginalPath;
	}
	public final void setOriginalPath(String value)
	{
		OriginalPath = value;
	}

	/** 
	 Gets or sets the name of the original file.
	 
	 <value>The name of the original file.</value>
	*/
	private String OriginalFileName;
	public final String getOriginalFileName()
	{
		return OriginalFileName;
	}
	public final void setOriginalFileName(String value)
	{
		OriginalFileName = value;
	}

	/** 
	 Gets or sets the name of the extracted.
	 
	 <value>The name of the extracted.</value>
	*/
	private String ExtractedName;
	public final String getExtractedName()
	{
		return ExtractedName;
	}
	public final void setExtractedName(String value)
	{
		ExtractedName = value;
	}

	/** 
	 Gets or sets the extracted year.
	 
	 <value>The extracted year.</value>
	*/
	private Integer ExtractedYear = null;
	public final Integer getExtractedYear()
	{
		return ExtractedYear;
	}
	public final void setExtractedYear(Integer value)
	{
		ExtractedYear = value;
	}

	/** 
	 Gets or sets the extracted season number.
	 
	 <value>The extracted season number.</value>
	*/
	private Integer ExtractedSeasonNumber = null;
	public final Integer getExtractedSeasonNumber()
	{
		return ExtractedSeasonNumber;
	}
	public final void setExtractedSeasonNumber(Integer value)
	{
		ExtractedSeasonNumber = value;
	}

	/** 
	 Gets or sets the extracted episode number.
	 
	 <value>The extracted episode number.</value>
	*/
	private Integer ExtractedEpisodeNumber = null;
	public final Integer getExtractedEpisodeNumber()
	{
		return ExtractedEpisodeNumber;
	}
	public final void setExtractedEpisodeNumber(Integer value)
	{
		ExtractedEpisodeNumber = value;
	}

	/** 
	 Gets or sets the extracted ending episode number.
	 
	 <value>The extracted ending episode number.</value>
	*/
	private Integer ExtractedEndingEpisodeNumber = null;
	public final Integer getExtractedEndingEpisodeNumber()
	{
		return ExtractedEndingEpisodeNumber;
	}
	public final void setExtractedEndingEpisodeNumber(Integer value)
	{
		ExtractedEndingEpisodeNumber = value;
	}

	/** 
	 Gets or sets the target path.
	 
	 <value>The target path.</value>
	*/
	private String TargetPath;
	public final String getTargetPath()
	{
		return TargetPath;
	}
	public final void setTargetPath(String value)
	{
		TargetPath = value;
	}

	/** 
	 Gets or sets the date.
	 
	 <value>The date.</value>
	*/
	private java.util.Date Date = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return Date;
	}
	public final void setDate(java.util.Date value)
	{
		Date = value;
	}

	/** 
	 Gets or sets the error message.
	 
	 <value>The error message.</value>
	*/
	private String StatusMessage;
	public final String getStatusMessage()
	{
		return StatusMessage;
	}
	public final void setStatusMessage(String value)
	{
		StatusMessage = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private FileSortingStatus Status = FileSortingStatus.values()[0];
	public final FileSortingStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(FileSortingStatus value)
	{
		Status = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private FileOrganizerType Type = FileOrganizerType.values()[0];
	public final FileOrganizerType getType()
	{
		return Type;
	}
	public final void setType(FileOrganizerType value)
	{
		Type = value;
	}

	/** 
	 Gets or sets the duplicate paths.
	 
	 <value>The duplicate paths.</value>
	*/
	private java.util.ArrayList<String> DuplicatePaths;
	public final java.util.ArrayList<String> getDuplicatePaths()
	{
		return DuplicatePaths;
	}
	public final void setDuplicatePaths(java.util.ArrayList<String> value)
	{
		DuplicatePaths = value;
	}

	/** 
	 Gets or sets the size of the file.
	 
	 <value>The size of the file.</value>
	*/
	private long FileSize;
	public final long getFileSize()
	{
		return FileSize;
	}
	public final void setFileSize(long value)
	{
		FileSize = value;
	}

	public FileOrganizationResult()
	{
		setDuplicatePaths(new java.util.ArrayList<String>());
	}
}