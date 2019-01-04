package id.jmlcode.sample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import id.jmlcode.sample.app.App;
import id.jmlcode.sample.di.component.DaggerFragmentComponent;
import id.jmlcode.sample.di.component.FragmentComponent;
import id.jmlcode.sample.di.module.FragmentModule;
import id.jmlcode.sample.util.SnackbarUtil;


public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {
    private static final String TAG = BaseFragment.class.getSimpleName();

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) _mActivity.findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }


    protected abstract void initInject();


}