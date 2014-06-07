package MediaBrowser.Model.Drawing;

import MediaBrowser.Model.Extensions.*;

/** 
 Struct ImageSize
*/
//C# TO JAVA CONVERTER WARNING: Java does not allow user-defined value types. The behavior of this class will differ from the original:
//ORIGINAL LINE: public struct ImageSize
public final class ImageSize
{
	private double _height;
	private double _width;

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	public double getHeight()
	{
		return _height;
	}
	public void setHeight(double value)
	{
		_height = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	public double getWidth()
	{
		return _width;
	}
	public void setWidth(double value)
	{
		_width = value;
	}

	public boolean equals(ImageSize size)
	{
		return (new Double(getWidth())).equals(size.getWidth()) && (new Double(getHeight())).equals(size.getHeight());
	}

	@Override
	public String toString()
	{
		return String.format("%1$s-%2$s", getWidth(), getHeight());
	}

	public ImageSize()
	{
	}

	public ImageSize(String value)
	{
		_width = 0;

		_height = 0;

		ParseValue(value);
	}

	private void ParseValue(String value)
	{
		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(value))
		{
			String[] parts = value.split("[-]", -1);

			if (parts.length == 2)
			{
				double val = 0;

				tangible.RefObject<Double> tempRef_val = new tangible.RefObject<Double>(val);
				boolean tempVar = DoubleHelper.TryParseCultureInvariant(parts[0], tempRef_val);
					val = tempRef_val.argValue;
				if (tempVar)
				{
					_width = val;
				}

				tangible.RefObject<Double> tempRef_val2 = new tangible.RefObject<Double>(val);
				boolean tempVar2 = DoubleHelper.TryParseCultureInvariant(parts[1], tempRef_val2);
					val = tempRef_val2.argValue;
				if (tempVar2)
				{
					_height = val;
				}
			}
		}
	}

	public ImageSize clone()
	{
		ImageSize varCopy = new ImageSize();

		varCopy._height = this._height;
		varCopy._width = this._width;

		return varCopy;
	}
}