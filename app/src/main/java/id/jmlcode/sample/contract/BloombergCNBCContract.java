package id.jmlcode.sample.contract;

import java.util.List;

import id.jmlcode.sample.base.BasePresenter;
import id.jmlcode.sample.base.BaseView;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.model.bean.ResultStatement;
import id.jmlcode.sample.model.bean.realm.TestOneMillion;
import retrofit2.Response;


/**
 * Created by Jamal on 8/8/2017.
 */
public interface BloombergCNBCContract {

    interface View extends BaseView {
        /*void showContent(List<BloombergCNBCBean> bloombergCNBCBean);
        void onLoading();
        void hideLoading();*/

        void showContentGetToken(Response<ResultGetToken> a);
        void onLoadingGetToken();
        void hideLoadingGetToken();

        void showContentGetSignature(String a);
        void onLoadingGetSignature();
        void hideLoadingGetSignature();

        void showContentGetStatement(ResultStatement a);
        void onLoadingGetStatemene();
        void hideLoadingGetStatement();
    }

    interface Presenter extends BasePresenter<View> {
        //void getBloombeergCNBCData(String apiKey);


        void getToken(String authorization,
                      String contentType,
                      String grantType);

        void getSignature(
                String timestamp,
                String uri,
                String accessToken,
                String aPISecret,
                String hTTPMethod
        );

        void getStatement(
                String xBCASignature,
                String authorization,
                String XbCAtimestamp,
                String xBCAKey,
                String accountNumber,
                String CorporateID,
                String startDate,
                String endDate
        );

        boolean getNightModeState();
        void setNightModeState(boolean state);

        void insertOneMillionData(TestOneMillion testOneMillion);
        List<TestOneMillion> selectOneMillion();
    }
}
