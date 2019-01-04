package id.jmlcode.sample.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import id.jmlcode.sample.app.App;
import id.jmlcode.sample.util.Constants;


public class ImplPreferencesHelper implements PreferencesHelper {

    private static final boolean DEFAULT_NIGHT_MODE = false;
    public static final String DEFAULT_JWT_TOKEN = "";


    private static final String SHAREDPREFERENCES_NAME = "wpli_pref";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean getNightModeState() {
        return mSPrefs.getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    @Override
    public void setNightModeState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    @Override
    public String getToken() {
        return mSPrefs.getString(Constants.SP_JWT_TOKEN, DEFAULT_JWT_TOKEN);
    }

    @Override
    public void setToken(String token) {
        mSPrefs.edit().putString(Constants.SP_JWT_TOKEN, token).apply();
    }
}
