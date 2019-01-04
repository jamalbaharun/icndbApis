package id.jmlcode.sample.ui.login;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import butterknife.OnClick;
import id.jmlcode.sample.R;
import id.jmlcode.sample.base.BaseActivity;
import id.jmlcode.sample.contract.LoginContract;
import id.jmlcode.sample.presenter.LoginPresenter;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.sign_up)
    public void clickSignUp(View view){
        Snackbar.make(view, "Sign Up", Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
