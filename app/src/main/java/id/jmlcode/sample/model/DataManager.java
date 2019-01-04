package id.jmlcode.sample.model;

import java.util.List;

import id.jmlcode.sample.model.bean.BloombergCNBCBean;
import id.jmlcode.sample.model.bean.BloombergCNBCHttpResponse;
import id.jmlcode.sample.model.bean.IcndbBean;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.model.bean.ResultStatement;
import id.jmlcode.sample.model.bean.realm.TestOneMillion;
import id.jmlcode.sample.model.db.DBHelper;
import id.jmlcode.sample.model.http.HttpHelper;
import id.jmlcode.sample.model.prefs.PreferencesHelper;
import io.reactivex.Flowable;
import retrofit2.Response;


public class DataManager implements HttpHelper, DBHelper, PreferencesHelper {

    private final HttpHelper mHttpHelper;
    private final DBHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public String getToken() {
        return mPreferencesHelper.getToken();
    }

    @Override
    public void setToken(String token) {
        mPreferencesHelper.setToken(token);
    }


    @Override
    public Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> fetchBloombergList(String apiKey) {
        return mHttpHelper.fetchBloombergList(apiKey);
    }

    @Override
    public Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> fetchCNBCList(String apiKey) {
        return mHttpHelper.fetchCNBCList(apiKey);
    }

    @Override
    public Flowable<Response<ResultGetToken>> getTokenBCA(String authorization, String contentType, String grantType) {
        return mHttpHelper.getTokenBCA(authorization, contentType, grantType);
    }

    @Override
    public Flowable<String> getSignature(String authorization, String uri, String accessToken, String aPISecret, String hTTPMethod) {
        return mHttpHelper.getSignature(authorization, uri, accessToken, aPISecret, hTTPMethod);
    }

    @Override
    public Flowable<ResultStatement> getStatement(String xBCASignature, String authorization, String XbCAtimestamp, String xBCAKey, String accountNumber, String CorporateID, String startDate, String endDate) {
        return mHttpHelper.getStatement(xBCASignature, authorization, XbCAtimestamp, xBCAKey, accountNumber, CorporateID, startDate, endDate);
    }

    @Override
    public Flowable<IcndbBean> getJokes(int count) {
        return mHttpHelper.getJokes(count);
    }

    @Override
    public void insertOneMillionData(TestOneMillion testOneMillion) {
        mDbHelper.insertOneMillionData(testOneMillion);
    }

    @Override
    public List<TestOneMillion> selectOneMillion() {
        return mDbHelper.selectOneMillion();
    }
}
