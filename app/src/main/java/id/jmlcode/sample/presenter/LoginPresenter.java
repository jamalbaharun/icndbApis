package id.jmlcode.sample.presenter;

import javax.inject.Inject;

import id.jmlcode.sample.base.RxPresenter;
import id.jmlcode.sample.contract.LoginContract;
import id.jmlcode.sample.model.DataManager;

/**
 * Created by Jamal on 8/8/2017.
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter  {

    private DataManager mDataManager;


    @Inject
    public LoginPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


}
