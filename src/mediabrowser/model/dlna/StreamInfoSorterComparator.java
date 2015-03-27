package mediabrowser.model.dlna;

import mediabrowser.model.mediainfo.MediaProtocol;
import mediabrowser.model.session.PlayMethod;

public class StreamInfoSorterComparator extends BaseStreamInfoSorter {

	private int level;

	public StreamInfoSorterComparator(int level) {
		this.level = level;
	}

	@Override
	protected int getValue(StreamInfo info) {

		switch (level){

			case 0:
			{
				if (info.getPlayMethod() == PlayMethod.DirectPlay && info.getMediaSource().getProtocol() == MediaProtocol.File)
				{
					return 0;
				}

				return 1;
			}
			case 1:
			{
				if (info.getPlayMethod() == PlayMethod.DirectPlay && info.getMediaSource().getProtocol() == MediaProtocol.File)
				{
					return 0;
				}

				return 1;
			}
			case 2:
			{
				if (info.getPlayMethod() == PlayMethod.DirectPlay && info.getMediaSource().getProtocol() == MediaProtocol.File)
				{
					return 0;
				}

				return 1;
			}
		}

		throw new IllegalArgumentException("Unrecognized level");
	}
}
