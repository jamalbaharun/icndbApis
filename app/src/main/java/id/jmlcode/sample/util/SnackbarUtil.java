package id.jmlcode.sample.util;

import android.support.design.widget.Snackbar;
import android.view.View;
/**
 * Created by Jamal on 1/4/2018.
 */

public class SnackbarUtil {
    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
