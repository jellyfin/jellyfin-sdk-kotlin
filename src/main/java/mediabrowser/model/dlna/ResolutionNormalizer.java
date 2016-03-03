package mediabrowser.model.dlna;

public class ResolutionNormalizer
{
	private static final java.util.ArrayList<ResolutionConfiguration> Configurations = new java.util.ArrayList<ResolutionConfiguration>(java.util.Arrays.asList(new ResolutionConfiguration[]
	{
		new ResolutionConfiguration(426, 320000),
		new ResolutionConfiguration(640, 400000),
		new ResolutionConfiguration(720, 950000),
		new ResolutionConfiguration(1280, 2500000)
	}));

	public static ResolutionOptions Normalize(Integer inputBitrate, int outputBitrate, String inputCodec, String outputCodec, Integer maxWidth, Integer maxHeight)
	{
		// If the bitrate isn't changing, then don't downlscale the resolution
		if (inputBitrate != null && outputBitrate >= inputBitrate)
		{
			if (maxWidth != null || maxHeight != null)
			{
				ResolutionOptions tempVar = new ResolutionOptions();
				tempVar.setMaxWidth(maxWidth);
				tempVar.setMaxHeight(maxHeight);
				return tempVar;
			}
		}

		for (ResolutionConfiguration config : Configurations)
		{
			if (outputBitrate <= config.getMaxBitrate())
			{
				Integer originvalValue = maxWidth;

				maxWidth = Math.min(config.getMaxWidth(), (maxWidth != null) ? maxWidth : config.getMaxWidth());
				if (originvalValue == null || originvalValue != maxWidth)
				{
					maxHeight = null;
				}

				break;
			}
		}

		ResolutionOptions tempVar2 = new ResolutionOptions();
		tempVar2.setMaxWidth(maxWidth);
		tempVar2.setMaxHeight(maxHeight);
		return tempVar2;
	}
}