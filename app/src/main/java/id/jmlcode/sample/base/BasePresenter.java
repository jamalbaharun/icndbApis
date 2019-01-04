package id.jmlcode.sample.base;

/**
 * Created by Jamal on 1/23/2018.
 */

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
