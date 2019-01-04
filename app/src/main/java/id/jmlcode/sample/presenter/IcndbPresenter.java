package id.jmlcode.sample.presenter;

import java.util.List;

import javax.inject.Inject;

import id.jmlcode.sample.base.RxPresenter;
import id.jmlcode.sample.contract.IcndbContract;
import id.jmlcode.sample.contract.LoginContract;
import id.jmlcode.sample.model.DataManager;
import id.jmlcode.sample.model.bean.IcndbBean;
import id.jmlcode.sample.util.RxUtil;
import id.jmlcode.sample.widget.CommonSubscriber;

/**
 * Created by Jamal on 8/8/2017.
 */
public class IcndbPresenter extends RxPresenter<IcndbContract.View> implements IcndbContract.Presenter  {

    private DataManager mDataManager;


    @Inject
    public IcndbPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getJokes(int count) {
        mView.showLoading();
        addSubscribe(mDataManager.getJokes(count)
                .compose(RxUtil.<IcndbBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<IcndbBean>(mView, false) {
                    @Override
                    public void onNext(IcndbBean response) {
                        if (response != null) {
                            mView.showResult(response);
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideLoading();
                    }
                }));
    }
}
