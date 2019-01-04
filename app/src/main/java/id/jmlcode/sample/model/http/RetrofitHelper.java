package id.jmlcode.sample.model.http;

import java.util.List;

import javax.inject.Inject;

import id.jmlcode.sample.model.bean.BloombergCNBCBean;
import id.jmlcode.sample.model.bean.BloombergCNBCHttpResponse;
import id.jmlcode.sample.model.bean.IcndbBean;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.model.bean.ResultStatement;
import id.jmlcode.sample.model.http.api.IcndbApi;
import id.jmlcode.sample.model.http.api.OwnApis;
import io.reactivex.Flowable;
import retrofit2.Response;

public class RetrofitHelper implements HttpHelper {

    private final OwnApis mOwnApis;
    private final IcndbApi mIcndbApi;

    @Inject
    public RetrofitHelper(OwnApis ownApis, IcndbApi icndbApi) {
        this.mOwnApis = ownApis;
        this.mIcndbApi = icndbApi;
    }



    @Override
    public Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> fetchBloombergList(String apiKey) {
        return mOwnApis.getBloombergApis(apiKey);
    }

    @Override
    public Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> fetchCNBCList(String apiKey) {
        return mOwnApis.getCNBCApis(apiKey);
    }

    @Override
    public Flowable<Response<ResultGetToken>> getTokenBCA(String authorization, String contentType, String grantType) {
        return mOwnApis.getTokenBCA(authorization, contentType, grantType);
    }

    @Override
    public Flowable<String> getSignature(String timestamp, String uri, String accessToken, String aPISecret, String hTTPMethod) {
        return mOwnApis.getSignature(timestamp, uri, accessToken, aPISecret, hTTPMethod);
    }

    @Override
    public Flowable<ResultStatement> getStatement(String xBCASignature, String authorization, String XbCAtimestamp, String xBCAKey, String accountNumber, String CorporateID, String startDate, String endDate) {
        return mOwnApis.getStatement(xBCASignature, authorization, XbCAtimestamp, xBCAKey, accountNumber, CorporateID, startDate, endDate);
    }

    @Override
    public Flowable<IcndbBean> getJokes(int total) {
        return mIcndbApi.getJoke(total);
    }


}
