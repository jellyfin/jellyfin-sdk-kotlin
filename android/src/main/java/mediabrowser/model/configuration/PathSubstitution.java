package mediabrowser.model.configuration;

import mediabrowser.model.dto.*;
import mediabrowser.model.entities.*;

public class PathSubstitution
{
	private String From;
	public final String getFrom()
	{
		return From;
	}
	public final void setFrom(String value)
	{
		From = value;
	}
	private String To;
	public final String getTo()
	{
		return To;
	}
	public final void setTo(String value)
	{
		To = value;
	}
}