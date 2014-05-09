package MediaBrowser.Model.LiveTv;

public class TimerInfoDto extends BaseTimerInfoDto
{
	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private RecordingStatus privateStatus = RecordingStatus.values()[0];
	public final RecordingStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(RecordingStatus value)
	{
		privateStatus = value;
	}

	/** 
	 Gets or sets the series timer identifier.
	 
	 <value>The series timer identifier.</value>
	*/
	private String privateSeriesTimerId;
	public final String getSeriesTimerId()
	{
		return privateSeriesTimerId;
	}
	public final void setSeriesTimerId(String value)
	{
		privateSeriesTimerId = value;
	}

	/** 
	 Gets or sets the external series timer identifier.
	 
	 <value>The external series timer identifier.</value>
	*/
	private String privateExternalSeriesTimerId;
	public final String getExternalSeriesTimerId()
	{
		return privateExternalSeriesTimerId;
	}
	public final void setExternalSeriesTimerId(String value)
	{
		privateExternalSeriesTimerId = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long privateRunTimeTicks;
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	/** 
	 Gets or sets the program information.
	 
	 <value>The program information.</value>
	*/
	private ProgramInfoDto privateProgramInfo;
	public final ProgramInfoDto getProgramInfo()
	{
		return privateProgramInfo;
	}
	public final void setProgramInfo(ProgramInfoDto value)
	{
		privateProgramInfo = value;
	}

}