package id.jmlcode.sample.model.http;


import java.util.List;

import id.jmlcode.sample.model.bean.BloombergCNBCBean;
import id.jmlcode.sample.model.bean.BloombergCNBCHttpResponse;
import id.jmlcode.sample.model.bean.IcndbBean;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.model.bean.ResultStatement;
import io.reactivex.Flowable;
import retrofit2.Response;

public interface HttpHelper {
    Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> fetchBloombergList(String apiKey);
    Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> fetchCNBCList(String apiKey);
    Flowable<Response<ResultGetToken>> getTokenBCA(String authorization,
                                         String contentType,
                                         String grantType);
    Flowable<String> getSignature(
            String timestamp,
            String uri,
            String accessToken,
            String aPISecret,
            String hTTPMethod
    );

    Flowable<ResultStatement> getStatement(
            String xBCASignature,
            String authorization,
            String XbCAtimestamp,
            String xBCAKey,
            String accountNumber,
            String CorporateID,
            String startDate,
            String endDate
    );

    Flowable<IcndbBean> getJokes(int count);
}
