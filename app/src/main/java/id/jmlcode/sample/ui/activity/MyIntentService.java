package id.jmlcode.sample.ui.activity;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import javax.inject.Inject;

import id.jmlcode.sample.app.App;
import id.jmlcode.sample.di.component.DaggerIntentServiceComponent;
import id.jmlcode.sample.di.component.IntentServiceComponent;
import id.jmlcode.sample.di.module.IntentServiceModule;
import id.jmlcode.sample.model.DataManager;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.util.GenerateBasicAuth;
import id.jmlcode.sample.util.RxUtil;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Response;

public class MyIntentService extends IntentService {

    //from MyIntentService to MainActivity
    final static String KEY_INT_FROM_SERVICE = "KEY_INT_FROM_SERVICE";
    final static String KEY_STRING_FROM_SERVICE = "KEY_STRING_FROM_SERVICE";
    final static String ACTION_UPDATE_CNT = "UPDATE_CNT";
    final static String ACTION_UPDATE_MSG = "UPDATE_MSG";

    //from MainActivity to MyIntentService
    final static String KEY_MSG_TO_SERVICE = "KEY_MSG_TO_SERVICE";
    final static String ACTION_MSG_TO_SERVICE = "MSG_TO_SERVICE";
    @Inject DataManager dataManager;

    MyServiceReceiver myServiceReceiver;
    int cnt;

    public MyIntentService() {
        super("MyIntentService");
    }

    protected IntentServiceComponent getServiceComponent(){
        return DaggerIntentServiceComponent.builder()
                .appComponent(App.getAppComponent())
                .intentServiceModule(new IntentServiceModule(this))
                .build();
    }

    @Override
    public void onCreate() {
        getServiceComponent().inject(this);
        Toast.makeText(getApplicationContext(),
                "onCreate", Toast.LENGTH_LONG).show();
        myServiceReceiver = new MyServiceReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(),
                "onStartCommand", Toast.LENGTH_LONG).show();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_MSG_TO_SERVICE);
        registerReceiver(myServiceReceiver, intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(),
                "onDestroy", Toast.LENGTH_LONG).show();
        unregisterReceiver(myServiceReceiver);
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String prompt;
        //check if current thread is Main Thread (UI)
        if(Looper.myLooper() == Looper.getMainLooper()){
            prompt = "onHandleIntent run in UI Thread";
        }else{
            prompt = "onHandleIntent run in NOT UI Thread";
        }

        Intent iPrompt = new Intent();
        iPrompt.setAction(ACTION_UPDATE_MSG);
        iPrompt.putExtra(KEY_STRING_FROM_SERVICE, prompt);
        sendBroadcast(iPrompt);

        cnt = 0;
        boolean b = false;
        while (!b){//while (cnt <= 1000){
            try {
                Thread.sleep(800);

                Intent i = new Intent();
                i.setAction(ACTION_UPDATE_CNT);
                i.putExtra(KEY_INT_FROM_SERVICE, cnt);
                sendBroadcast(i);

                String basicAuth = new GenerateBasicAuth().getAuthToken();
                dataManager.getTokenBCA(basicAuth,
                        "application/x-www-form-urlencoded","client_credentials")
                        .compose(RxUtil.<Response<ResultGetToken>>rxSchedulerHelper())
                        .subscribe(new ResourceSubscriber<Response<ResultGetToken>>() {
                            @Override
                            public void onNext(Response<ResultGetToken> resultGetToken) {
                                Log.e("KAMFRET "+cnt,new Gson().toJson(resultGetToken));
                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                cnt++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(action.equals(ACTION_MSG_TO_SERVICE)){
                String msg = intent.getStringExtra(KEY_MSG_TO_SERVICE);

                msg = new StringBuilder(msg).reverse().toString();

                //send back to MainActivity
                Intent i = new Intent();
                i.setAction(ACTION_UPDATE_MSG);
                i.putExtra(KEY_STRING_FROM_SERVICE, msg);
                sendBroadcast(i);
            }
        }
    }

}
