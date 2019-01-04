package id.jmlcode.sample.util;

/**
 * Created by Jamal on 9/27/2017.
 */


import org.greenrobot.eventbus.EventBus;

public class GlobalBus {
    private static EventBus sBus;

    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}
