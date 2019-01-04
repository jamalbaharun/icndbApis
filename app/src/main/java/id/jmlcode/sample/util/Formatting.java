package id.jmlcode.sample.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by HanusaCloud on 8/17/2016.
 */
public class Formatting {

    private static DecimalFormat decimalFormat;

    private static void init() {
        // set the currency format
        decimalFormat = new DecimalFormat("#,###;(#,###)");

        // swap comma and dot in rupiah currency
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');

        // set it to the formatter
        decimalFormat.setDecimalFormatSymbols(dfs);
    }

    public static String rupiahCurrency(double amount) {
        if (decimalFormat == null) {
            init();
        }
        // return the formatted number that already appended with "Rp" without extra space before
        // (this format is comply with EYD)
        return "Rp " + decimalFormat.format(amount);
    }

    public static String rupiahCurrency(long amount) {
        if (decimalFormat == null) {
            init();
        }
        // return the formatted number that already appended with "Rp" without extra space before
        // (this format is comply with EYD)
        return "Rp " + decimalFormat.format(amount);
    }

    public static String currency(double amount) {
        if (decimalFormat == null) {
            init();
        }
        return decimalFormat.format(amount);
    }

    public static String currency(long amount) {
        if (decimalFormat == null) {
            init();
        }
        return decimalFormat.format(amount);
    }

    public static double getCleanResult(String originalString) {
        originalString = cleanString(originalString);
        return (originalString.length() > 0) ? Double.valueOf(originalString) : 0;
    }

    public static long getCleanResultLong(String originalString) {
        originalString = cleanString(originalString);
        return (originalString.length() > 0) ? Long.parseLong(originalString) : 0;
    }

    public static String cleanString(String originalString) {
        if (originalString.contains(",")) {
            originalString = originalString.replaceAll(",", "");
        }
        if (originalString.contains(".")) {
            originalString = originalString.replaceAll("\\.", "");
        }
        if (originalString.contains("Rp ")) {
            originalString = originalString.replaceAll("Rp ", "");
        }
        return originalString;
    }

    public static String cleanString(double value) {
        return String.format("%.0f", value);
    }

    public static double getCleanPercentage(double value) {
        DecimalFormat df = new DecimalFormat("##.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return Double.valueOf(df.format(value)) * 100;
    }

    public static String TimeFormater(String value) {
        DecimalFormat format = new DecimalFormat("##:##");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(':');
        format.setDecimalFormatSymbols(symbols);
        return String.valueOf(format.format(value));
    }

    public static String getCleanTimeFormat(String value) {
        if (value.contains(":")) {
            value = value.replaceAll(":", "");
        }
        return value;
    }

    public static double getFormattedDecimal(double value) {
        DecimalFormat df = new DecimalFormat("##.00");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return Double.valueOf(df.format(value));
    }

    public static float decimalRoundedEven(float source) {
        BigDecimal decimal = new BigDecimal(source);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }

    public static String currencyFormat(String value) {
        try {
            // TODO: uncomment this if desired behaviour changed.
//            if (value.length() > 0 && !value.equals("0")) {
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
            dfs.setCurrencySymbol("Rp ");
                DecimalFormat df = new DecimalFormat("#,###,###", dfs);
            String formatted = "Rp " + df.format(Double.valueOf(value));
//                LogUtil.d(Constante.TAG, "Formatted " + formatted + ", clean " + removeCurrency(formatted));
                return formatted;
//            } else {
//                return "";
//            }
        } catch (Throwable t) {
//            LogUtil.e(Constante.TAG, t.getMessage(), t);
            return value;
        }
    }

    public static String removeCurrency(String formatted) {
        try {
            String result = formatted.replaceAll("([Rp. ])+", "");
            return result.length() == 0 ? "0" : result;
        } catch (Throwable t) {
//            LogUtil.e(Constante.TAG, t.getMessage(), t);
            return formatted;
        }
    }

    public static String sanitizeDoubleZero(String source) {
        if (StringUtil.isEmpty(source)) {
            return "";
        }
        if (source.length() > 2 && !source.equals("0")) {
            return source.substring(0, source.length() - 2);
        } else {
            return source;
        }
    }

    public static String cleanZeroTrails(String formated) {
        //837378.00
        return formated.toLowerCase().replaceAll("([.0)])+", "");
    }
}
