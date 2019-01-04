package id.jmlcode.sample.util;

public class StringUtil {

    /**
     * This isEmpty method are copied from TextUtils.isEmpty
     * to remove dependency from Android framework
     *
     * @param str string to check
     * @return boolean that indicate the string is empty or not
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * This method should return empty string if the source is null,
     * otherwise return the source string
     *
     * @param source String source
     * @return empty string if source is null or source if not null
     */
    public static String stringHandlingNull(String source) {
        return isEmpty(source) ? "" : source;
    }
}