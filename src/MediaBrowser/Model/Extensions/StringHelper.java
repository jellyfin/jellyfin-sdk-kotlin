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
		return String.equals(str1, str2, StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 Indexes the of ignore case.
	 
	 @param str The string.
	 @param value The value.
	 @return System.Int32.
	*/
	public static int IndexOfIgnoreCase(String str, String value)
	{
		return str.indexOf(value, StringComparison.OrdinalIgnoreCase);
	}

	/** 
	 To the string culture invariant.
	 
	 @param val The value.
	 @return System.String.
	*/
	public static String ToStringCultureInvariant(int val)
	{
		return (new Integer(val)).toString(CultureInfo.InvariantCulture);
	}

	/** 
	 To the string culture invariant.
	 
	 @param val The value.
	 @return System.String.
	*/
	public static String ToStringCultureInvariant(long val)
	{
		return (new Long(val)).toString(CultureInfo.InvariantCulture);
	}

	/** 
	 To the string culture invariant.
	 
	 @param val The value.
	 @return System.String.
	*/
	public static String ToStringCultureInvariant(double val)
	{
		return (new Double(val)).toString(CultureInfo.InvariantCulture);
	}
}