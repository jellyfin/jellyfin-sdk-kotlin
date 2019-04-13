package org.jellyfin.apiclient.model.extensions;

public final class BoolHelper
{
	/** 
	 Tries the parse culture invariant.
	 
	 @param s The s.
	 @param result The result.
	 @return <c>true</c> if XXXX, <c>false</c> otherwise.
	*/
	public static boolean TryParseCultureInvariant(String s, tangible.RefObject<Boolean> result)
	{
        result.argValue = Boolean.parseBoolean(s);
        return true;
	}
}