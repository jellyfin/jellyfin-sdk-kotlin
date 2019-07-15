package tangible;

//----------------------------------------------------------------------------------------
//	Copyright © 2007 - 2014 Tangible Software Solutions Inc.
//	This class can be used by anyone provided that the copyright notice remains intact.
//
//	This class is used to simulate some .NET string methods in Java.
//----------------------------------------------------------------------------------------
public final class DotNetToJavaStringHelper
{
	//------------------------------------------------------------------------------------
	//	This method replaces the .NET static string method 'IsNullOrEmpty'.
	//------------------------------------------------------------------------------------
	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.equals("");
	}

	//------------------------------------------------------------------------------------
	//	This method replaces the .NET static string method 'Join' (2 parameter version).
	//------------------------------------------------------------------------------------
	public static String join(String separator, String[] stringArray)
	{
		if (stringArray == null)
			return null;
		else
			return join(separator, stringArray, 0, stringArray.length);
	}

	//------------------------------------------------------------------------------------
	//	This method replaces the .NET static string method 'Join' (4 parameter version).
	//------------------------------------------------------------------------------------
	public static String join(String separator, String[] stringArray, int startIndex, int count)
	{
		String result = "";

		if (stringArray == null)
			return null;

		for (int index = startIndex; index < stringArray.length && index - startIndex < count; index++)
		{
			if (separator != null && index > startIndex)
				result += separator;

			if (stringArray[index] != null)
				result += stringArray[index];
		}

		return result;
	}
}