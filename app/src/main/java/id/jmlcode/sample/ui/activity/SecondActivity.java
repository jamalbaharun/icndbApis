package id.jmlcode.sample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import id.jmlcode.sample.R;
import id.jmlcode.sample.base.BaseActivity;
import id.jmlcode.sample.contract.BloombergCNBCContract;
import id.jmlcode.sample.model.bean.Header;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.model.bean.ResultSignature;
import id.jmlcode.sample.model.bean.ResultStatement;
import id.jmlcode.sample.model.bean.realm.TestOneMillion;
import id.jmlcode.sample.model.events.NightModeEvent;
import id.jmlcode.sample.presenter.BloombergCNBCPresenter;
import id.jmlcode.sample.util.GenerateBasicAuth;
import id.jmlcode.sample.util.gson.SignatureConverter;
import retrofit2.Response;

public class SecondActivity extends BaseActivity<BloombergCNBCPresenter> implements BloombergCNBCContract.View {
    @BindView(R.id.ck_theme) CheckBox ckTheme;
    @BindView(R.id.result_token) TextView tvResultToken;
    @BindView(R.id.result_signature) TextView tvResultSignature;
    @BindView(R.id.result_statement) TextView getTvResultStatement;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    private static Intent intent;
    private static Header header = new Header();
    private static NightModeEvent event = new NightModeEvent();

    private ResultGetToken resultGetToken;
    private String resultSignature;
    private ResultSignature oResultSignature;
    private ResultStatement resultStatement;
    private String timeStamp ;// "2018-03-13T19:12:00.000+07:00"
    private Intent myIntent = null;
    private int jmlData = 2000000;


    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initEventAndData() {
       /* intent = getIntent();
        if(intent!=null) {
            header = intent.getExtras().getParcelable("datas");
            String a = header.getName();
            Toast.makeText(mContext, header.getName(), Toast.LENGTH_LONG).show();
            //mPresenter.getBloombeergCNBCData("140e15f66fb04c46a497da20ac379c77");*/
            if(mPresenter.getNightModeState()){
                ckTheme.setChecked(true);
                ckTheme.setText(getString(R.string.theme_night));
            }else{
                ckTheme.setChecked(false);
                ckTheme.setText(getString(R.string.theme_noon));
            }

        //}

        /*myIntent = new Intent(SecondActivity.this, MyIntentService.class);
        startService(myIntent);*/

        String basicAuth = new GenerateBasicAuth().getAuthToken();
        mPresenter.getToken(basicAuth,
                "application/x-www-form-urlencoded","client_credentials");
        progressBar.setMax(jmlData);
    }

    @OnCheckedChanged(R.id.ck_theme)
    void onThemeCheck(){
        for(int i = 0 ; i < jmlData ; i ++) {
            TestOneMillion testOneMillion = new TestOneMillion();
            testOneMillion.setString1("Wakanda forever");
            testOneMillion.setString2("Wakanda forever");
            testOneMillion.setString3("Wakanda forever");
            testOneMillion.setString4("Wakanda forever");
            testOneMillion.setString5("Wakanda forever");
            testOneMillion.setString6("Wakanda forever");
            testOneMillion.setString7("Wakanda forever");
            testOneMillion.setString8("Wakanda forever");
            testOneMillion.setString9("Wakanda forever");
            testOneMillion.setString10("Wakanda forever");
            testOneMillion.setString11("Wakanda forever");
            testOneMillion.setString12("Wakanda forever");
            testOneMillion.setString13("Wakanda forever");
            testOneMillion.setString14("Wakanda forever");
            testOneMillion.setString15("Wakanda forever");
            testOneMillion.setString16("Wakanda forever");
            mPresenter.insertOneMillionData(testOneMillion);
            progressBar.setProgress(i);
            Log.i("INDEX",i + testOneMillion.toString());
        }
        /*if(!mPresenter.getNightModeState()){
            mPresenter.setNightModeState(true);
            ckTheme.setChecked(true);
            ckTheme.setText(getString(R.string.theme_night));
            event.setNightMode(true);
        }else{
            mPresenter.setNightModeState(false);
            ckTheme.setChecked(false);
            event.setNightMode(false);
            ckTheme.setText(getString(R.string.theme_noon));
        }
        RxBus.getDefault().post(event);*/
    }

    /*@Override
    public void showContent(List<BloombergCNBCBean> bloombergCNBCBean) {
        List<BloombergCNBCBean> bloombergCNBCBeanList = bloombergCNBCBean;
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void hideLoading() {

    }*/

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showContentGetToken(Response<ResultGetToken> a) {
        this.resultGetToken = a.body();
        //tvResultToken.setText(new Gson().toJson(resultGetToken));
        tvResultToken.setText(String.valueOf(a.code()));
    }
    @Override
    public void onLoadingGetToken() {

    }
    @Override
    public void hideLoadingGetToken() {
        timeStamp= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZD",Locale.getDefault()).format(new Date());
        /*mPresenter.getSignature(timeStamp,
                "/banking/v3/corporates/BCAAPI2016/accounts/0201245680/statements?StartDate=2016-09-01&EndDate=2016-09-01",
                resultGetToken.getAccessToken(),
                "02ed5a50-f390-4283-bd1b-f43378a00d46",
                "GET");*/
    }

    @Override
    public void showContentGetSignature(String a) {
        this.resultSignature = a;
        this.oResultSignature = new SignatureConverter().signatureConverter(a);
        tvResultSignature.setText(new Gson().toJson(oResultSignature));
    }
    @Override
    public void onLoadingGetSignature() {

    }
    @Override
    public void hideLoadingGetSignature() {
        /*mPresenter.getStatement(oResultSignature.getCalculatedHMAC(),
                "Bearer "+oResultSignature.getAccessToken(),
                timeStamp,
                "104d363e-46ea-45a8-8ad6-6c674247efb6",
                "0201245680",
                "BCAAPI2016",
                "2016-09-01",
                "2016-09-01");*/
    }

    @Override
    public void showContentGetStatement(ResultStatement a) {
        this.resultStatement = a;
        getTvResultStatement.setText(new Gson().toJson(this.resultStatement));
    }

    @Override
    public void onLoadingGetStatemene() {

    }

    @Override
    public void hideLoadingGetStatement() {

    }


}
