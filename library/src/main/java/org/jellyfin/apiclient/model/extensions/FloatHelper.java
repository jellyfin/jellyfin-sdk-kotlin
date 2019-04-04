package org.jellyfin.apiclient.model.extensions;

public final class FloatHelper
{
	/** 
	 Tries the parse culture invariant.
	 
	 @param s The s.
	 @param result The result.
	 @return <c>true</c> if XXXX, <c>false</c> otherwise.
	*/
	public static boolean TryParseCultureInvariant(String s, tangible.RefObject<Float> result)
	{
        try {
            result.argValue = Float.parseFloat(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
	}
}