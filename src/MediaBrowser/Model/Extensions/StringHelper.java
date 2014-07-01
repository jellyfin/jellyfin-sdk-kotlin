package MediaBrowser.Model.Extensions;

/** 
 Isolating these helpers allow this entire project to be easily converted to Java
*/
public final class StringHelper
{
	/** 
	 Equalses the ignore case.
	 
	 @param str1 The STR1.
	 @param str2 The STR2.
	 @return <c>true</c> if XXXX, <c>false</c> otherwise.
	*/
	public static boolean EqualsIgnoreCase(String str1, String str2)
	{
		if (str1 == null && str2 == null) return true;

        return str1.equalsIgnoreCase(str2);
	}

	/** 
	 Indexes the of ignore case.
	 
	 @param str The string.
	 @param value The value.
	 @return System.Int32.
	*/
	public static int IndexOfIgnoreCase(String str, String value)
	{
		return str.toLowerCase().indexOf(value.toLowerCase());
	}

	/** 
	 To the string culture invariant.
	 
	 @param val The value.
	 @return System.String.
	*/
	public static String ToStringCultureInvariant(int val)
	{
        return String.valueOf(val);
	}

	/** 
	 To the string culture invariant.
	 
	 @param val The value.
	 @return System.String.
	*/
	public static String ToStringCultureInvariant(long val)
	{
		return String.valueOf(val);
	}

	/** 
	 To the string culture invariant.
	 
	 @param val The value.
	 @return System.String.
	*/
	public static String ToStringCultureInvariant(double val)
	{
		return String.valueOf(val);
	}

	/** 
	 Trims the start.
	 
	 @param str The string.
	 @param c The c.
	 @return System.String.
	*/
	public static String TrimStart(String str, char c)
	{
		return tangible.DotNetToJavaStringHelper.trimStart(str, c);
	}
}