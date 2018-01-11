package pzm.petchatbot.Tones.Tone;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pat on 9/29/2016.
 * Map of tone_ids
 */
public class Tone {

    public static final String ANGER = "anger";
    public static final String DISGUST = "disgust";
    public static final String FEAR = "fear";
    public static final String JOY = "joy";
    public static final String SADNESS = "sadness";

    public static final String ANALYTICAL = "analytical";
    public static final String CONFIDENT = "confident";
    public static final String TENTATIVE = "tentative";

    public static final String OPENNESS = "openness_big5";
    public static final String CONSCIENTIOUSNESS = "conscientiousness_big5";
    public static final String EXTRAVERSION = "extraversion_big5";
    public static final String AGREEABLENESS = "agreeableness_big5";
    public static final String EMOTIONAL_RANGE = "emotional_range_big5";

    public static final String EMOTIONAL = "emotion";
    public static final String LANGUAGE = "language";
    public static final String SOCIAL = "social";

    public static Map<String, Tone> getEmptyToneMap() {
        Map<String, Tone> toneMap = new HashMap<>();
        toneMap.put(ANGER, new Tone());
        toneMap.put(DISGUST, new Tone());
        toneMap.put(FEAR, new Tone());
        toneMap.put(JOY, new Tone());
        toneMap.put(SADNESS, new Tone());

        toneMap.put(ANALYTICAL, new Tone());
        toneMap.put(CONFIDENT, new Tone());
        toneMap.put(TENTATIVE, new Tone());

        toneMap.put(OPENNESS, new Tone());
        toneMap.put(CONSCIENTIOUSNESS, new Tone());
        toneMap.put(EXTRAVERSION, new Tone());
        toneMap.put(AGREEABLENESS, new Tone());
        toneMap.put(EMOTIONAL_RANGE, new Tone());

        return toneMap;
    }

    private float value;
    private int count;
    private float[] prevVal;

    public Tone() {
        value = 0;
        count = 0;
        prevVal = new float[5];
        prevVal[0] = 0.0f;
        prevVal[1] = 0.0f;
        prevVal[2] = 0.0f;
        prevVal[3] = 0.0f;
        prevVal[4] = 0.0f;
    }

    public float[] getPrevVal() {
        return prevVal;
    }

    public void setPrevVal(float[] prevVal) {
        this.prevVal = prevVal;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAsToneString() {
        return String.valueOf(value) + " " + String.valueOf(count) + " " + String.valueOf(prevVal[0]) + " " + String.valueOf(prevVal[1]) + " " + String.valueOf(prevVal[2]) + " " + String.valueOf(prevVal[3]) + " " + String.valueOf(prevVal[4]);
    }
}
