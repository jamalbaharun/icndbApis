package id.jmlcode.sample.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Jamal on 3/13/2018.
 */

public class GenerateBasicAuth {
    public static String getAuthToken() {
        byte[] data = new byte[0];
        try {
            data = ("5d262d3d-b9f1-4207-b720-58645638e90e" + ":" + "8dfad975-bc66-438c-a3de-6d7ca0cfbb04").getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
        }
}
