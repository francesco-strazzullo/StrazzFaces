package it.strazz.faces.util;

import java.util.Collection;

/**
 * Utility Class for Strings
 * 
 * @author f1l0
 */
public class Strings {

	public static boolean isNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}
	
	public static String join(final Collection<String> strings, String separator) {
        if (strings == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }

        final int size = strings.size();
        if (size <= 0) {
            return "";
        }

        final StringBuilder buf = new StringBuilder(size * 16);

        int i = 0;
        for (String string : strings) {
			if(i > 0){
				buf.append(separator);
			}
			buf.append(string);
			i++;
		}
        
        return buf.toString();
    }
}
