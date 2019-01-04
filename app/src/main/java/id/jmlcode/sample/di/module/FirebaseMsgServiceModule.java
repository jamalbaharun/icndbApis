package id.jmlcode.sample.di.module;

import com.google.firebase.messaging.FirebaseMessagingService;

import dagger.Module;

/**
 * Created by Jamal2 on 9/14/2017.
 */
@Module
public class FirebaseMsgServiceModule {
    private FirebaseMessagingService service;

    public FirebaseMsgServiceModule(FirebaseMessagingService service) {
        this.service = service;
    }
}
