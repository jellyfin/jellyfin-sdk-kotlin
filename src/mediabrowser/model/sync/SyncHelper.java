package mediabrowser.model.sync;

import mediabrowser.model.dto.*;

public final class SyncHelper
{
	public static java.util.ArrayList<SyncOptions> GetSyncOptions(java.util.ArrayList<BaseItemDto> items)
	{
		java.util.ArrayList<SyncOptions> options = new java.util.ArrayList<SyncOptions>();

		if (items.size() > 1)
		{
			options.add(SyncOptions.Name);
		}

		for (BaseItemDto item : items)
		{
			Boolean tempVar = item.getSupportsSync();
			if ((tempVar != null) ? tempVar : false)
			{
				if (item.getIsVideo())
				{
					options.add(SyncOptions.Quality);
					if (items.size() > 1)
					{
						options.add(SyncOptions.UnwatchedOnly);
					}
					break;
				}
				if (item.getIsFolder() && !item.getIsMusicGenre() && !item.getIsArtist() && !item.IsType("musicalbum") && !item.getIsGameGenre())
				{
					options.add(SyncOptions.Quality);
					options.add(SyncOptions.UnwatchedOnly);
					break;
				}
				if (item.getIsGenre())
				{
					options.add(SyncOptions.SyncNewContent);
					options.add(SyncOptions.ItemLimit);
					break;
				}
			}
		}

		for (BaseItemDto item : items)
		{
			Boolean tempVar2 = item.getSupportsSync();
			if ((tempVar2 != null) ? tempVar2 : false)
			{
				if (item.getIsFolder() || item.getIsGameGenre() || item.getIsMusicGenre() || item.getIsGenre() || item.getIsArtist() || item.getIsStudio() || item.getIsPerson())
				{
					options.add(SyncOptions.SyncNewContent);
					options.add(SyncOptions.ItemLimit);
					break;
				}
			}
		}

		return options;
	}

	public static java.util.ArrayList<SyncOptions> GetSyncOptions(SyncCategory category)
	{
		java.util.ArrayList<SyncOptions> options = new java.util.ArrayList<SyncOptions>();

		options.add(SyncOptions.Name);
		options.add(SyncOptions.Quality);
		options.add(SyncOptions.UnwatchedOnly);
		options.add(SyncOptions.SyncNewContent);
		options.add(SyncOptions.ItemLimit);

		return options;
	}
}