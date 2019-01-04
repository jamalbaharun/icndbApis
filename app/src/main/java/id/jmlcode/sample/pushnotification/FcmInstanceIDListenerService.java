package id.jmlcode.sample.pushnotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by sunil on 9/4/16.
 */
public class FcmInstanceIDListenerService extends FirebaseInstanceIdService {

    final private static String TAG = "FcmInstanceIDListener";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String registrationId = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Found Registration Id:" + registrationId);

    }
}
