package mediabrowser.model.dlna;

import java.util.Collections;

public class StreamInfoSorter
{
	public static java.util.ArrayList<StreamInfo> SortMediaSources(java.util.ArrayList<StreamInfo> streams)
	{
		ChainedComparator<StreamInfo> comparator = new ChainedComparator<>(
				new StreamInfoSorterComparator(0),
				new StreamInfoSorterComparator(1),
				new StreamInfoSorterComparator(2)
		);

		Collections.sort(streams, comparator);
		return streams;
	}
}