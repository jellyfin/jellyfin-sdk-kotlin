package mediabrowser.model.extensions;

public class LongHelper
{
    /**
     Tries the parse culture invariant.

     @param s The s.
     @param result The result.
     @return <c>true</c> if XXXX, <c>false</c> otherwise.
     */
    public static boolean TryParseCultureInvariant(String s, tangible.RefObject<Long> result)
    {
        try {
            result.argValue = Long.parseLong(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}