package id.jmlcode.sample.model.http.api;

import java.util.List;

import id.jmlcode.sample.model.bean.BloombergCNBCBean;
import id.jmlcode.sample.model.bean.BloombergCNBCHttpResponse;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.model.bean.ResultStatement;
import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jamal on 9/6/2017.
 */

public interface OwnApis {
    //String HOST = "https://newsapi.org/v1/";
    String HOST = "https://sandbox.bca.co.id:443/";

    @GET("articles?source=bloomberg&sortBy=top")
    Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> getBloombergApis(@Query("apiKey") String apiKey);

    @GET("articles?source=cnbc&sortBy=top")
    Flowable<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>> getCNBCApis(@Query("apiKey") String apiKey);

    @FormUrlEncoded
    @POST("api/oauth/token")
    Flowable<Response<ResultGetToken>> getTokenBCA(
            @Header("Authorization") String authorization,
            @Header("Content-Type") String contentType,
            @Field("grant_Type") String grantType
    );


    @Headers({"Content-type: text/plain"})
    @POST("/utilities/signature")
    Flowable<String> getSignature(
            @Header("Timestamp") String timestamp,
            @Header("URI") String uri,
            @Header("AccessToken") String accessToken,
            @Header("APISecret") String aPISecret,
            @Header("HTTPMethod") String hTTPMethod
    );


    //banking/v3/corporates/BCAAPI2016/accounts/0201245680/statements?StartDate=2016-09-01&EndDate=2016-09-01
    @Headers({"Content-Type: application/json"})
    @GET("banking/v3/corporates/{CorporateID}/accounts/{AccountNumber}/statements")
    Flowable<ResultStatement> getStatement(
            @Header("X-BCA-Signature")String xBCASignature,
            @Header("Authorization")String authorization,
            @Header("X-BCA-Timestamp")String XbCAtimestamp,
            @Header("X-BCA-Key")String xBCAKey,
            @Path("AccountNumber")String accountNumber,
            @Path("CorporateID")String CorporateID,
            @Query("StartDate")String startDate,
            @Query("EndDate")String endDate
    );
}
