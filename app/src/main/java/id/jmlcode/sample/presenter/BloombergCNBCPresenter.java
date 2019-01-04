package id.jmlcode.sample.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import id.jmlcode.sample.base.RxPresenter;
import id.jmlcode.sample.component.RxBus;
import id.jmlcode.sample.contract.BloombergCNBCContract;
import id.jmlcode.sample.model.DataManager;
import id.jmlcode.sample.model.bean.BloombergCNBCBean;
import id.jmlcode.sample.model.bean.ResultGetToken;
import id.jmlcode.sample.model.bean.ResultStatement;
import id.jmlcode.sample.model.bean.realm.TestOneMillion;
import id.jmlcode.sample.model.events.NightModeEvent;
import id.jmlcode.sample.util.RxUtil;
import id.jmlcode.sample.widget.CommonSubscriber;
import io.reactivex.functions.Function;
import retrofit2.Response;

/**
 * Created by Jamal on 8/8/2017.
 */
public class BloombergCNBCPresenter extends RxPresenter<BloombergCNBCContract.View> implements BloombergCNBCContract.Presenter  {

    private DataManager mDataManager;
    private boolean isHotList = true;
    private List<BloombergCNBCBean> totalList = new ArrayList<>();

    @Inject
    public BloombergCNBCPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(BloombergCNBCContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(NightModeEvent.class)
                .compose(RxUtil.<NightModeEvent>rxSchedulerHelper())
                .map(new Function<NightModeEvent, Boolean>() {
                    @Override
                    public Boolean apply(NightModeEvent nightModeEvent) {
                        return nightModeEvent.getNightMode();
                    }
                })
                .subscribeWith(new CommonSubscriber<Boolean>(mView, "Ada sesuatu yang salahヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        registerEvent();
                    }
                })
        );
    }

    /*@Override
    public void getBloombeergCNBCData(String apiKey) {
        mView.onLoading();
        totalList.clear();
        Flowable<List<BloombergCNBCBean>> listBloomberg = mDataManager.fetchBloombergList(apiKey)
                .compose(RxUtil.<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<BloombergCNBCBean>>handleBloombergCNBCResult());

        Flowable<List<BloombergCNBCBean>> listCNBC = mDataManager.fetchCNBCList(apiKey)
                .compose(RxUtil.<BloombergCNBCHttpResponse<List<BloombergCNBCBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<BloombergCNBCBean>>handleBloombergCNBCResult());

        addSubscribe(Flowable.concat(listCNBC,listBloomberg)
                .subscribeWith(new CommonSubscriber<List<BloombergCNBCBean>>(mView) {
                    @Override
                    public void onNext(List<BloombergCNBCBean> bloombergCNBCBeen) {
                        if (isHotList) {
                            isHotList = false;
                            totalList.addAll(bloombergCNBCBeen);
                        } else {
                            isHotList = true;
                            totalList.addAll(bloombergCNBCBeen);
                            mView.showContent(totalList);
                       }
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideLoading();
                    }
                })
        );
    }*/

    @Override
    public void getToken(String authorization, String contentType, String grantType) {
        mView.onLoadingGetToken();
        mDataManager.getTokenBCA(authorization, contentType, grantType)
                .compose(RxUtil.<Response<ResultGetToken>>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<Response<ResultGetToken>>(mView,false) {
                    @Override
                    public void onNext(Response<ResultGetToken> a) {
                        mView.showContentGetToken(a);
                        mView.hideLoadingGetToken();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideLoadingGetToken();
                    }
                });
    }

    @Override
    public void getSignature(String timestamp, String uri, String accessToken, String aPISecret, String hTTPMethod) {
        mView.onLoadingGetSignature();
        mDataManager.getSignature(timestamp, uri, accessToken, aPISecret, hTTPMethod)
                .compose(RxUtil.<String>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<String>(mView,false) {
                    @Override
                    public void onNext(String a) {
                        mView.showContentGetSignature(a);
                        mView.hideLoadingGetSignature();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideLoadingGetSignature();
                    }
                });
    }

    @Override
    public void getStatement(String xBCASignature, String authorization, String XbCAtimestamp, String xBCAKey, String accountNumber, String CorporateID, String startDate, String endDate) {
        mView.onLoadingGetStatemene();
        mDataManager.getStatement(xBCASignature, authorization, XbCAtimestamp, xBCAKey, accountNumber, CorporateID, startDate, endDate)
                .compose(RxUtil.<ResultStatement>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<ResultStatement>(mView,false) {
                    @Override
                    public void onNext(ResultStatement a) {
                        mView.showContentGetStatement(a);
                        mView.hideLoadingGetStatement();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideLoadingGetStatement();
                    }
                });
    }


    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mDataManager.setNightModeState(state);
    }

    @Override
    public void insertOneMillionData(TestOneMillion testOneMillion) {
        mDataManager.insertOneMillionData(testOneMillion);
    }

    @Override
    public List<TestOneMillion> selectOneMillion() {
        return mDataManager.selectOneMillion();
    }
}
