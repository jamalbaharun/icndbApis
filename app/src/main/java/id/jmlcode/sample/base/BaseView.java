package id.jmlcode.sample.base;

/**
 * Created by Jamal on 1/23/2018.
 */

public interface BaseView {
    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    void stateError();

    /*void stateEmpty();

    void stateLoading();

    void stateMain();*/
}
