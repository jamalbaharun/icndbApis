package id.jmlcode.sample.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import id.jmlcode.sample.util.service.ICheckAvailableNetwork;

/**
 * Created by Jamal on 1/4/2018.
 */

public class CheckAvailableNetwork extends BroadcastReceiver {

    public static ICheckAvailableNetwork iCheckAvailableNetwork;

    public static void setConnectivityListener(ICheckAvailableNetwork listener) {
        iCheckAvailableNetwork = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            boolean isConnected = isConnected(context);
            if (iCheckAvailableNetwork != null) {
                iCheckAvailableNetwork.onNetworkConnectionChanged(isConnected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager
                cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null)
                && (activeNetwork.getState() == activeNetwork.getState().CONNECTED);
    }


}
