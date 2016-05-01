package by.kotsikav.utils;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.regexp.internal.RESyntaxException;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by yura5 on 03.04.2016.
 */
public class RegexMap extends HashMap {
    public Object put(Object key, Object value) {
        if (key instanceof String)
            return super.put(key, value);
        else
            throw new RuntimeException("RegexpKeyedMap - only accepts Strings as keys.");
    }

    public Object get(Object key) {
        Iterator regexps = keySet().iterator();
        String keyString;
        Object result = null;

        String stringToMatch = cleanKey(key);

        while (regexps.hasNext()) {
            keyString = regexps.next().toString();
            try {
                RE regexp = new RE(keyString);
                if (regexp.match(stringToMatch)) {
                    result = super.get(keyString);
                    break;
                }
            } catch (RESyntaxException e) {
            }
        }
        return result;
    }

    private String cleanKey(Object obj) {
        String retVal = obj.toString();
        return (retVal.charAt(0) == '^') ? retVal.substring(1) : retVal;
    }
}
