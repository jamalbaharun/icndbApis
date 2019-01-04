package id.jmlcode.sample.util;

import android.util.Log;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncodeRealm {
    public static byte[] encrypt(String strClearText){
        byte[] encrypted = new byte[0];
        try {
            Key key = generateKey();
            Cipher cipher=Cipher.getInstance(Constants.METODE_ENCRYPTION);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(strClearText.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    /*public static byte[] decrypt(String encryptedData) {
        byte[] decValue = new byte[0];
        try {
            encryptedData = encryptedData.replaceAll(" ", "+");
            Key key = generateKey();
            Cipher c = Cipher.getInstance(Constants.METODE_ENCRYPTION);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] data = Base64.decode(encryptedData,Base64.DEFAULT);
    
            decValue = c.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decValue;
    }*/
    
    private static Key generateKey() throws Exception {
        Key key = null;
        try {
            String keyStr = Constants.KEY;
            byte[] keyValue = keyStr.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyValue = sha.digest(keyValue);
            keyValue = Arrays.copyOf(keyValue, 16); // 16 byte
            key = new SecretKeySpec(keyValue, Constants.METODE_ENCRYPTION);
        }catch (Exception e){
            Log.e(Constants.TAG,e.getMessage());
        }
        return key;
    }
}
