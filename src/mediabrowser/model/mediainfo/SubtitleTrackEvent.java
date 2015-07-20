package mediabrowser.model.mediainfo;

public class SubtitleTrackEvent
{
    private String Id;
    public final String getId()
    {
        return Id;
    }
    public final void setId(String value)
    {
        Id = value;
    }
    private String Text;
    public final String getText()
    {
        return Text;
    }
    public final void setText(String value)
    {
        Text = value;
    }
    private long StartPositionTicks;
    public final long getStartPositionTicks()
    {
        return StartPositionTicks;
    }
    public final void setStartPositionTicks(long value)
    {
        StartPositionTicks = value;
    }
    private long EndPositionTicks;
    public final long getEndPositionTicks()
    {
        return EndPositionTicks;
    }
    public final void setEndPositionTicks(long value)
    {
        EndPositionTicks = value;
    }
}
