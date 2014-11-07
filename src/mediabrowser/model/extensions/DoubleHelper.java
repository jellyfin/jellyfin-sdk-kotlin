package mediabrowser.model.Extensions;

/** 
 Isolating these helpers allow this entire project to be easily converted to Java
*/
public final class DoubleHelper
{
	/** 
	 Tries the parse culture invariant.
	 
	 @param s The s.
	 @param result The result.
	 @return <c>true</c> if XXXX, <c>false</c> otherwise.
	*/
	public static boolean TryParseCultureInvariant(String s, tangible.RefObject<Double> result)
	{
        try {
            result.argValue = Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
	}
}