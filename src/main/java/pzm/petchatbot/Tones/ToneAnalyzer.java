package pzm.petchatbot.Tones;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import pzm.petchatbot.Tones.Tone.Tone;
import pzm.petchatbot.Tones.Tone.ToneBasic;
import pzm.petchatbot.Tones.Tone.ToneEnum;
import pzm.petchatbot.Tones.Tone.ToneFull;
import pzm.petchatbot.Utils.StaticVars;
import pzm.petchatbot.Utils.ToneHelper;

/**
 * Created by pat on 9/28/2016.
 */
public class ToneAnalyzer {

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    private static String encodeURIComponent(String s) throws UnsupportedEncodingException
    {
        String result = null;
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");

        return result;
    }

    public static ToneBasic analyzeTone(String s) {
        class OneShotTask implements Callable<ToneBasic> {
            String text;
            OneShotTask(String s) { text = s; }

            public ToneBasic call() {
                try {
                    text = encodeURIComponent(text);
                    URL url = new URL(StaticVars.TONE_ANALYSIS_ENDPOINT + text);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");


                    if(conn.getResponseCode() == 400) {
                        return null;
                    }

                    String body = IOUtils.toString(conn.getInputStream(), "UTF-8");

                    ToneFull toneFull = new ToneFull();

                    JsonParser parser = new JsonParser();
                    JsonObject rootObj = parser.parse(body.toString()).getAsJsonObject();
                    JsonArray locObj = rootObj.getAsJsonObject("document_tone")
                            .getAsJsonArray("tone_categories");
                    ToneBasic toneBasic = new ToneBasic();
                    for(int j=0; j<3; j++) {
                        JsonArray toneArray = locObj.get(j).getAsJsonObject().getAsJsonArray("tones");
                        for (int i = 0; i < toneArray.size(); i++) {
                            JsonObject toneObject = toneArray.get(i).getAsJsonObject();
                            ToneEnum toneEnum = ToneEnum.getToneEnumById(toneObject.get("tone_id").getAsString());
                            float trueValue = ToneEnum.getToneTrueValue(toneEnum, toneObject.get("score").getAsFloat());
                            if(toneBasic.getValue() < trueValue) {
                                toneBasic.setToneId(toneObject.get("tone_id").getAsString());
                                toneBasic.setValue(trueValue);
                            }
                            toneFull.setToneValue(toneObject.get("tone_id").getAsString(), trueValue);
                        }
                    }

                    ToneHelper.updateTone(toneFull);
                    return toneBasic;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        Future<ToneBasic> tone = EXECUTOR.submit(new OneShotTask(s));
        try {
            return tone.get();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
