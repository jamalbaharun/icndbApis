package id.jmlcode.sample.contract;

import java.util.List;

import id.jmlcode.sample.base.BasePresenter;
import id.jmlcode.sample.base.BaseView;
import id.jmlcode.sample.model.bean.IcndbBean;


/**
 * Created by Jamal on 8/8/2017.
 */
public interface IcndbContract {

    interface View extends BaseView {
        void showResult(IcndbBean response);

        void showLoading();

        void hideLoading();


    }

    interface Presenter extends BasePresenter<View> {

        void getJokes(int count);
    }
}
