package mediabrowser.model.dto;

public final class ItemLayout
{
	public static Double GetDisplayAspectRatio(BaseItemDto item)
	{
		java.util.ArrayList<BaseItemDto> items = new java.util.ArrayList<BaseItemDto>();
		items.add(item);
		return GetDisplayAspectRatio(items);
	}

	public static Double GetDisplayAspectRatio(java.util.ArrayList<BaseItemDto> items)
	{
		java.util.ArrayList<Double> values = new java.util.ArrayList<Double>();

		for (BaseItemDto item : items)
		{
			if (item.getPrimaryImageAspectRatio() != null)
			{
				values.add(item.getPrimaryImageAspectRatio());
			}
		}

		if (values.isEmpty())
		{
			return null;
		}

		java.util.Collections.sort(values);

		double halfDouble = values.size();
		halfDouble /= 2;
		int half = (int)Math.floor(halfDouble);

		double result;

		if (values.size() % 2 > 0)
		{
			result = values.get(half);
		}
		else
		{
			result = (values.get(half - 1) + values.get(half)) / 2.0;
		}

		// If really close to 2:3 (poster image), just return 2:3
		if (Math.abs(0.66666666667 - result) <= .15)
		{
			return 0.66666666667;
		}

		// If really close to 16:9 (episode image), just return 16:9
		if (Math.abs(1.777777778 - result) <= .2)
		{
			return 1.777777778;
		}

		// If really close to 1 (square image), just return 1
		if (Math.abs(1 - result) <= .15)
		{
			return 1.0;
		}

		// If really close to 4:3 (poster image), just return 2:3
		if (Math.abs(1.33333333333 - result) <= .15)
		{
			return 1.33333333333;
		}

		return result;
	}
}