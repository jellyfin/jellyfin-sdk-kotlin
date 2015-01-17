package mediabrowser.model.sync;

import mediabrowser.model.dto.*;

public final class SyncHelper
{
	public static java.util.ArrayList<SyncJobOption> GetSyncOptions(java.util.ArrayList<BaseItemDto> items)
	{
		java.util.ArrayList<SyncJobOption> options = new java.util.ArrayList<SyncJobOption>();

		if (items.size() > 1)
		{
			options.add(SyncJobOption.Name);
		}

		for (BaseItemDto item : items)
		{
			Boolean tempVar = item.getSupportsSync();
			if ((tempVar != null) ? tempVar : false)
			{
				if (item.getIsVideo())
				{
					options.add(SyncJobOption.Quality);
					if (items.size() > 1)
					{
						options.add(SyncJobOption.UnwatchedOnly);
					}
					break;
				}
				if (item.getIsFolder() && !item.getIsMusicGenre() && !item.getIsArtist() && !item.IsType("musicalbum") && !item.getIsGameGenre())
				{
					options.add(SyncJobOption.Quality);
					options.add(SyncJobOption.UnwatchedOnly);
					break;
				}
				if (item.getIsGenre())
				{
					options.add(SyncJobOption.SyncNewContent);
					options.add(SyncJobOption.ItemLimit);
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
					options.add(SyncJobOption.SyncNewContent);
					options.add(SyncJobOption.ItemLimit);
					break;
				}
			}
		}

		return options;
	}

	public static java.util.ArrayList<SyncJobOption> GetSyncOptions(SyncCategory category)
	{
		java.util.ArrayList<SyncJobOption> options = new java.util.ArrayList<SyncJobOption>();

		options.add(SyncJobOption.Name);
		options.add(SyncJobOption.Quality);
		options.add(SyncJobOption.UnwatchedOnly);
		options.add(SyncJobOption.SyncNewContent);
		options.add(SyncJobOption.ItemLimit);

		return options;
	}
}