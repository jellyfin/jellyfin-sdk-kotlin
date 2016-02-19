package mediabrowser.model.extensions;

/** 
 Isolating these helpers allow this entire project to be easily converted to Java
*/
public final class IntHelper
{
	/** 
	 Tries the parse culture invariant.
	 
	 @param s The s.
	 @param result The result.
	 @return <c>true</c> if XXXX, <c>false</c> otherwise.
	*/
	public static boolean TryParseCultureInvariant(String s, tangible.RefObject<Integer> result)
	{
        try {
            result.argValue = Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
	}
}