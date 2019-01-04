package id.jmlcode.sample.util;

import android.os.Environment;

import java.io.File;

import id.jmlcode.sample.app.App;


public class Constants {
    public static final String TAG = "GREAT_APP";
    static final String METODE_ENCRYPTION = "AES";
    public static final String networkAvailable = "Network Available";
    public static final String networkUnAvailable = "Network Unvailable";
    private static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/internetcache";
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "id" + File.separator + "widy";
    public static final String DATE_TIME_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_TIME_ZONE_1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_TIME_ZONE_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_ZONE_3 = "MMM dd, yyyy HH:mm:ss aa";
    public static final String SP_NIGHT_MODE = "night_mode";
    public static final String SP_JWT_TOKEN = "token";


    static final String PATH_BLOCK_CANARY = "/blockcanary/performance";
    static final String KEY = "oWdYnt0Wh1t3Op3n"; // 16 bit key
    public static final String KEY64 = "Jdids37hsKsudhd79sjdjdya7a99ekdkdhfhss7shaakziaus"; // 64 bytes


}
