package id.jmlcode.sample.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.jmlcode.sample.util.CheckAvailableNetwork;
import id.jmlcode.sample.util.Constants;
import id.jmlcode.sample.util.SnackbarUtil;
import id.jmlcode.sample.util.service.ICheckAvailableNetwork;
import me.yokeyword.fragmentation.SupportActivity;

import static id.jmlcode.sample.util.Constants.networkAvailable;
import static id.jmlcode.sample.util.Constants.networkUnAvailable;


/**
 * Created by Jamal on 1/4/2018.
 */

public abstract class SimpleActivity extends SupportActivity implements ICheckAvailableNetwork{
    private Unbinder mUnBinder;
    protected abstract int getLayoutResourceId();
    protected void onViewCreated() {}
    protected abstract void initEventAndData();
    protected Activity mContext;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        mContext = this;
        setContentView(getLayoutResourceId());
        mUnBinder = ButterKnife.bind(this);
        onViewCreated();
        initEventAndData();
        checkConnection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckAvailableNetwork.setConnectivityListener(this);
    }

    protected void setToolBar(Toolbar toolbar,
                              String title, boolean displayHomeAsUpEnabled,
                              boolean displayShowHomeEnabled) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
        getSupportActionBar().setDisplayShowHomeEnabled(displayShowHomeEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showMsgNetwork(isConnected);
    }

    private void checkConnection(){
        boolean isConnected = CheckAvailableNetwork.isConnected(getApplicationContext());
        showMsgNetwork(isConnected);
    }

    private void showMsgNetwork(boolean isConnected){
        String msg = isConnected ? networkAvailable:networkUnAvailable;
        if(!isConnected) {
            SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
        }
        Log.d(Constants.TAG, "Network Available : "+msg);
    }

    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }
}
