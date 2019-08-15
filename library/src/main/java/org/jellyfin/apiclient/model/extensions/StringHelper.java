package org.jellyfin.apiclient.model.extensions;

/**
 Isolating these helpers allow this entire project to be easily converted to Java
 */
public final class StringHelper
{
    /**
     Null safe {@code equalsIgnoreCase}.

     @param str1 The first String.
     @param str2 The second String.
     @return <c>true</c> if both strings are null or equalsIgnoreCase, <c>false</c> otherwise.
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null && str2 == null) return true;

        if (str1 == null || str2 == null) return false;

        return str1.equalsIgnoreCase(str2);
    }
}
