package id.jmlcode.sample.util.gson;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import id.jmlcode.sample.util.Constants;

/**
 * Created by Jamal on 6/19/2017.
 */
public class DateSerializeDeserialize implements JsonDeserializer<Date>, JsonSerializer<Date> {

    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOF,
                            JsonDeserializationContext context) throws JsonParseException {
        for (String format : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(format, Locale.getDefault()).parse(jsonElement.getAsString());
            } catch (ParseException e) {
                Log.e(Constants.TAG,e.getMessage());
            }
        }
        throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        for (String format : DATE_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
                return new JsonPrimitive(sdf.format(src));
            } catch (Exception e) {
                Log.e(Constants.TAG,e.getMessage());
            }
        }
        throw new JsonParseException("Unparseable date: \"" + src
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }

    private static final String[] DATE_FORMATS = new String[] {
            Constants.DATE_TIME_ZONE,
            Constants.DATE_TIME_ZONE_1,
            Constants.DATE_TIME_ZONE_2,
            Constants.DATE_TIME_ZONE_3
    };

}
