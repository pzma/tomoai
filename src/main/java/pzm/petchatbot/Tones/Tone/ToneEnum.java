package pzm.petchatbot.Tones.Tone;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by pat on 10/10/2016.
 */
public enum ToneEnum {



    ANGER(Tone.ANGER,255,0,0, "Anger",ToneEnum.EMOTIONAL_TYPE), //red
    DISGUST(Tone.DISGUST,0,255,0, "Disgust",ToneEnum.EMOTIONAL_TYPE), //green
    FEAR(Tone.FEAR,255,0,255, "Fear",ToneEnum.EMOTIONAL_TYPE), //purple
    JOY(Tone.JOY,255,255,0, "Joy",ToneEnum.EMOTIONAL_TYPE), //yellow
    SADNESS(Tone.SADNESS,0,0,255, "Sadness",ToneEnum.EMOTIONAL_TYPE), //blue

    //cyan
    ANALYTICAL(Tone.ANALYTICAL,246,176,232, "Analytical",ToneEnum.LANGUAGE_TYPE),
    CONFIDENT(Tone.CONFIDENT,246,176,232, "Confident",ToneEnum.LANGUAGE_TYPE),
    TENTATIVE(Tone.TENTATIVE,246,176,232, "Tentative",ToneEnum.LANGUAGE_TYPE),

    OPENNESS(Tone.OPENNESS,0,255,247, "Openness",ToneEnum.SOCIAL_TYPE),
    CONSCIENTIOUSNESS(Tone.CONSCIENTIOUSNESS,0,255,247,"Conscientiousness",ToneEnum.SOCIAL_TYPE),
    EXTRAVERSION(Tone.EXTRAVERSION,0,255,247,"Extraversion",ToneEnum.SOCIAL_TYPE),
    AGREEABLENESS(Tone.AGREEABLENESS,0,255,247, "Agreeableness",ToneEnum.SOCIAL_TYPE),
    EMOTIONAL_RANGE(Tone.EMOTIONAL_RANGE,0,255,247, "Emotional Range",ToneEnum.SOCIAL_TYPE),

    NULLTONE("null", 138,138, 138,"",0),

    EMOTIONAL(Tone.EMOTIONAL, 0,0,0, "Emotional Tones", ToneEnum.EMOTIONAL_TYPE),
    LANGUAGE(Tone.LANGUAGE, 0,0,0, "Language Tones", ToneEnum.LANGUAGE_TYPE),
    SOCIAL(Tone.SOCIAL, 0,0,0, "Social Tones", ToneEnum.SOCIAL_TYPE);

    public static final float EMOTIONAL_TYPE = 1.7f;
    public static final float LANGUAGE_TYPE = 1.0f;
    public static final float SOCIAL_TYPE = 1.0f;


    ToneEnum(String toneId, int red, int green, int blue, String toneName, float type) {
        this.id = toneId;
        this.red=red;
        this.green=green;
        this.blue=blue;
        this.name = toneName;
        this.type = type;
    }

    public int getColor() {
        return Color.rgb(red, green, blue);
    }


    private String name;
    private String id;
    private int red;
    private int green;
    private int blue;
    private float type;

    public String getId() {
        return id;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public String getName() { return name;}

    public float getType () {return type;}

    public static ToneEnum getToneEnumById(String id) {
        for(ToneEnum toneEnum : ToneEnum.values()) {
            if(toneEnum.getId().equals(id)) {
                return toneEnum;
            }
        }
        return ToneEnum.NULLTONE;
    }

    public static float getToneTrueValue(ToneEnum toneEnum, float value) {
        if(value == 0.0f) { //for language tones mostly..
            Random rand = new Random();
            value = rand.nextFloat() * (.35f);
        }
        return ((((value) * toneEnum.getType()) > 1.0f) ? 1.0f : ((value) * toneEnum.getType()));
    }

}
