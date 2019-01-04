package id.jmlcode.sample.util;

import android.content.Context;
import android.net.ConnectivityManager;

import id.jmlcode.sample.app.App;

/**
 * Created by Jamal on 1/23/2018.
 */

public class SystemUtil {
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
