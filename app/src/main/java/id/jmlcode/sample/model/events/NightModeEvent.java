package id.jmlcode.sample.model.events;

/**
 * Created by Jamal on 1/24/2018.
 */

public class NightModeEvent {
    private boolean isNightMode;

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }

    public boolean getNightMode() {
        return isNightMode;
    }
}
