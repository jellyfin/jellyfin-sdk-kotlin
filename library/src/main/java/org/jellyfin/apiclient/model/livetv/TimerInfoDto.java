package org.jellyfin.apiclient.model.livetv;

import org.jellyfin.apiclient.model.dto.*;

public class TimerInfoDto extends BaseTimerInfoDto
{
	public TimerInfoDto()
	{
		setType("Timer");
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private RecordingStatus Status = RecordingStatus.values()[0];
	public final RecordingStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(RecordingStatus value)
	{
		Status = value;
	}

	/** 
	 Gets or sets the series timer identifier.
	 
	 <value>The series timer identifier.</value>
	*/
	private String SeriesTimerId;
	public final String getSeriesTimerId()
	{
		return SeriesTimerId;
	}
	public final void setSeriesTimerId(String value)
	{
		SeriesTimerId = value;
	}

	/** 
	 Gets or sets the external series timer identifier.
	 
	 <value>The external series timer identifier.</value>
	*/
	private String ExternalSeriesTimerId;
	public final String getExternalSeriesTimerId()
	{
		return ExternalSeriesTimerId;
	}
	public final void setExternalSeriesTimerId(String value)
	{
		ExternalSeriesTimerId = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long RunTimeTicks;
	public final Long getRunTimeTicks()
	{
		return RunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		RunTimeTicks = value;
	}

	/** 
	 Gets or sets the program information.
	 
	 <value>The program information.</value>
	*/
	private BaseItemDto ProgramInfo;
	public final BaseItemDto getProgramInfo()
	{
		return ProgramInfo;
	}
	public final void setProgramInfo(BaseItemDto value)
	{
		ProgramInfo = value;
	}

}