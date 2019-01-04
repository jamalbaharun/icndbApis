package id.jmlcode.sample.pushnotification;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FcmListenerService extends FirebaseMessagingService {
    private static final String TAG = FcmListenerService.class.getSimpleName();


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "Message data:" + remoteMessage.getData());
        // Map<String,String> value = remoteMessage.getData();

    }

}