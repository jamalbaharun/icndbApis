package id.jmlcode.sample.model.prefs;


public interface PreferencesHelper {

    boolean getNightModeState();
    void setNightModeState(boolean state);

    String getToken();
    void setToken(String token);
}
