package id.jmlcode.sample.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.jmlcode.sample.util.CheckAvailableNetwork;
import id.jmlcode.sample.util.Constants;
import id.jmlcode.sample.util.SnackbarUtil;
import me.yokeyword.fragmentation.SupportFragment;

import static id.jmlcode.sample.util.Constants.networkAvailable;
import static id.jmlcode.sample.util.Constants.networkUnAvailable;

public abstract class SimpleFragment extends SupportFragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected boolean isInited = false;
    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        initEventAndData();
        checkConnection();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        //initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();

    private void checkConnection(){
        boolean isConnected = CheckAvailableNetwork.isConnected(mContext);
        showMsgNetwork(isConnected);
    }

    private void showMsgNetwork(boolean isConnected){
        String msg = isConnected ? networkAvailable:networkUnAvailable;
        if(!isConnected) {
            SnackbarUtil.show(((ViewGroup) _mActivity.findViewById(android.R.id.content)).getChildAt(0), msg);
        }
        Log.d(Constants.TAG, "Network Available : "+msg);
    }
}
