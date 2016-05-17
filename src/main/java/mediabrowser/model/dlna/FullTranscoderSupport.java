package mediabrowser.model.dlna;

public class FullTranscoderSupport implements ITranscoderSupport
{
	public final boolean CanEncodeToAudioCodec(String codec)
	{
		return true;
	}
}