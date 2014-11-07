package mediabrowser.model.configuration;

import mediabrowser.model.entities.*;
import mediabrowser.model.extensions.*;

/** 
 Class MetadataOptions.
*/
public class MetadataOptions
{
	private String ItemType;
	public final String getItemType()
	{
		return ItemType;
	}
	public final void setItemType(String value)
	{
		ItemType = value;
	}

	private ImageOption[] ImageOptions;
	public final ImageOption[] getImageOptions()
	{
		return ImageOptions;
	}
	public final void setImageOptions(ImageOption[] value)
	{
		ImageOptions = value;
	}

	private String[] DisabledMetadataSavers;
	public final String[] getDisabledMetadataSavers()
	{
		return DisabledMetadataSavers;
	}
	public final void setDisabledMetadataSavers(String[] value)
	{
		DisabledMetadataSavers = value;
	}
	private String[] LocalMetadataReaderOrder;
	public final String[] getLocalMetadataReaderOrder()
	{
		return LocalMetadataReaderOrder;
	}
	public final void setLocalMetadataReaderOrder(String[] value)
	{
		LocalMetadataReaderOrder = value;
	}

	private String[] DisabledMetadataFetchers;
	public final String[] getDisabledMetadataFetchers()
	{
		return DisabledMetadataFetchers;
	}
	public final void setDisabledMetadataFetchers(String[] value)
	{
		DisabledMetadataFetchers = value;
	}
	private String[] MetadataFetcherOrder;
	public final String[] getMetadataFetcherOrder()
	{
		return MetadataFetcherOrder;
	}
	public final void setMetadataFetcherOrder(String[] value)
	{
		MetadataFetcherOrder = value;
	}

	private String[] DisabledImageFetchers;
	public final String[] getDisabledImageFetchers()
	{
		return DisabledImageFetchers;
	}
	public final void setDisabledImageFetchers(String[] value)
	{
		DisabledImageFetchers = value;
	}
	private String[] ImageFetcherOrder;
	public final String[] getImageFetcherOrder()
	{
		return ImageFetcherOrder;
	}
	public final void setImageFetcherOrder(String[] value)
	{
		ImageFetcherOrder = value;
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