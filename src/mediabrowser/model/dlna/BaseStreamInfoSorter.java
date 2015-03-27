package mediabrowser.model.dlna;

import java.util.Comparator;

public abstract class BaseStreamInfoSorter implements Comparator<StreamInfo> {
	protected abstract int getValue(StreamInfo info);

	@Override
	public int compare(StreamInfo lhs, StreamInfo rhs) {

		return Integer.compare(getValue(lhs), getValue(rhs));
	}
}
