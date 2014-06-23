package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Extensions.*;

/** 
 Class MetadataOptions.
*/
public class MetadataOptions
{
	private String privateItemType;
	public final String getItemType()
	{
		return privateItemType;
	}
	public final void setItemType(String value)
	{
		privateItemType = value;
	}

	private ImageOption[] privateImageOptions;
	public final ImageOption[] getImageOptions()
	{
		return privateImageOptions;
	}
	public final void setImageOptions(ImageOption[] value)
	{
		privateImageOptions = value;
	}

	private String[] privateDisabledMetadataSavers;
	public final String[] getDisabledMetadataSavers()
	{
		return privateDisabledMetadataSavers;
	}
	public final void setDisabledMetadataSavers(String[] value)
	{
		privateDisabledMetadataSavers = value;
	}
	private String[] privateLocalMetadataReaderOrder;
	public final String[] getLocalMetadataReaderOrder()
	{
		return privateLocalMetadataReaderOrder;
	}
	public final void setLocalMetadataReaderOrder(String[] value)
	{
		privateLocalMetadataReaderOrder = value;
	}

	private String[] privateDisabledMetadataFetchers;
	public final String[] getDisabledMetadataFetchers()
	{
		return privateDisabledMetadataFetchers;
	}
	public final void setDisabledMetadataFetchers(String[] value)
	{
		privateDisabledMetadataFetchers = value;
	}
	private String[] privateMetadataFetcherOrder;
	public final String[] getMetadataFetcherOrder()
	{
		return privateMetadataFetcherOrder;
	}
	public final void setMetadataFetcherOrder(String[] value)
	{
		privateMetadataFetcherOrder = value;
	}

	private String[] privateDisabledImageFetchers;
	public final String[] getDisabledImageFetchers()
	{
		return privateDisabledImageFetchers;
	}
	public final void setDisabledImageFetchers(String[] value)
	{
		privateDisabledImageFetchers = value;
	}
	private String[] privateImageFetcherOrder;
	public final String[] getImageFetcherOrder()
	{
		return privateImageFetcherOrder;
	}
	public final void setImageFetcherOrder(String[] value)
	{
		privateImageFetcherOrder = value;
	}

	public MetadataOptions()
	{
		this(3, 1280);
	}

	public MetadataOptions(int backdropLimit, int minBackdropWidth)
	{
		ImageOption tempVar = new ImageOption();
		tempVar.setLimit(backdropLimit);
		tempVar.setMinWidth(minBackdropWidth);
		tempVar.setType(ImageType.Backdrop);
		java.util.ArrayList<ImageOption> imageOptions = new java.util.ArrayList<ImageOption>(java.util.Arrays.asList(new ImageOption[] {tempVar}));

		setImageOptions(imageOptions.toArray(new ImageOption[0]));
		setDisabledMetadataSavers(new String[] { });
		setLocalMetadataReaderOrder(new String[] { });

		setDisabledMetadataFetchers(new String[] { });
		setMetadataFetcherOrder(new String[] { });
		setDisabledImageFetchers(new String[] { });
		setImageFetcherOrder(new String[] { });
	}

	public final int GetLimit(ImageType type)
	{
		ImageOption option = null;
		for (ImageOption i : getImageOptions())
		{
			if (i.getType() == type)
			{
				option = i;
				break;
			}
		}

		return option == null ? 1 : option.getLimit();
	}

	public final int GetMinWidth(ImageType type)
	{
		ImageOption option = null;
		for (ImageOption i : getImageOptions())
		{
			if (i.getType() == type)
			{
				option = i;
				break;
			}
		}

		return option == null ? 0 : option.getMinWidth();
	}

	public final boolean IsEnabled(ImageType type)
	{
		return GetLimit(type) > 0;
	}

	public final boolean IsMetadataSaverEnabled(String name)
	{
		return !ListHelper.ContainsIgnoreCase(getDisabledMetadataSavers(), name);
	}
}