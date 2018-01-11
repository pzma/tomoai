package pzm.petchatbot.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pzm.petchatbot.Tones.Tone.Tone;
import pzm.petchatbot.Tones.Tone.ToneBasic;
import pzm.petchatbot.Tones.Tone.ToneFull;

/**
 * Created by pat on 9/29/2016.
 */
/*
emotionTone.txt


otherTone.txt

analytical
confident
tentative


 */
public class ToneHelper {


    public static void updateTone(ToneFull toneFull) {
        writeTone(toneFull);
    }

    public static void writeTone(ToneFull toneFull) {
        File toneFile = new File(PetChatBotApp.getContext().getFilesDir(), StaticVars.TONE_FILE);

        ToneFull currentTone = getToneFull();

        ToneFull tba = new ToneFull();
        for(String key : currentTone.getEntrySet()) {
            Tone curr = currentTone.getTone(key);

            //Double newVal = (curr.getValue() * ((double)curr.getCount()/(curr.getCount()+1))) + (toneFull.getValue(key) * ((double)1/(curr.getCount()+1)));

            curr.setCount(curr.getCount()+1);

            float[] newPrevVal = new float[5];
            newPrevVal[4] = toneFull.getValue(key);
            for(int i=3; i>=0; i--) {
                newPrevVal[i] = curr.getPrevVal()[i+1];
            }
            curr.setPrevVal(newPrevVal);
            curr.setValue((curr.getPrevVal()[4]*0.05f) + (curr.getPrevVal()[3]*0.04f) + (curr.getPrevVal()[2]*0.03f) + (curr.getPrevVal()[1]*0.02f) + (curr.getPrevVal()[0]*0.01f) + (curr.getValue() * .85f));

            tba.setTone(key, curr);
        }

        try {
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.ANGER).getAsToneString() + StaticVars.NEW_LINE, "UTF-8");
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.DISGUST).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.FEAR).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.JOY).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.SADNESS).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.ANALYTICAL).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.CONFIDENT).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.TENTATIVE).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);

            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.OPENNESS).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.CONSCIENTIOUSNESS).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.EXTRAVERSION).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.AGREEABLENESS).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);
            FileUtils.writeStringToFile(toneFile, tba.getTone(Tone.EMOTIONAL_RANGE).getAsToneString() + StaticVars.NEW_LINE, "UTF-8", true);


            ImageHelper.updateToneValues();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ToneFull getToneFull() {
        File toneFile = new File(PetChatBotApp.getContext().getFilesDir(), StaticVars.TONE_FILE);

        ToneFull currentTone = new ToneFull();
        try {
            List<String> lines = FileUtils.readLines(toneFile, "UTF-8");
            currentTone.setTone(Tone.ANGER, getToneLine(lines.get(0)));
            currentTone.setTone(Tone.DISGUST, getToneLine(lines.get(1)));
            currentTone.setTone(Tone.FEAR, getToneLine(lines.get(2)));
            currentTone.setTone(Tone.JOY, getToneLine(lines.get(3)));
            currentTone.setTone(Tone.SADNESS, getToneLine(lines.get(4)));

            currentTone.setTone(Tone.ANALYTICAL, getToneLine(lines.get(5)));
            currentTone.setTone(Tone.CONFIDENT, getToneLine(lines.get(6)));
            currentTone.setTone(Tone.TENTATIVE, getToneLine(lines.get(7)));

            currentTone.setTone(Tone.OPENNESS, getToneLine(lines.get(8)));
            currentTone.setTone(Tone.CONSCIENTIOUSNESS, getToneLine(lines.get(9)));
            currentTone.setTone(Tone.EXTRAVERSION, getToneLine(lines.get(10)));
            currentTone.setTone(Tone.AGREEABLENESS, getToneLine(lines.get(11)));
            currentTone.setTone(Tone.EMOTIONAL_RANGE, getToneLine(lines.get(12)));

            return currentTone;

        }catch(Exception e) {
            ToneFull emptyTone = new ToneFull();
            emptyTone.setTone(Tone.ANGER, new Tone());
            emptyTone.setTone(Tone.DISGUST, new Tone());
            emptyTone.setTone(Tone.FEAR, new Tone());
            emptyTone.setTone(Tone.JOY, new Tone());
            emptyTone.setTone(Tone.SADNESS, new Tone());

            emptyTone.setTone(Tone.ANALYTICAL, new Tone());
            emptyTone.setTone(Tone.CONFIDENT, new Tone());
            emptyTone.setTone(Tone.TENTATIVE, new Tone());

            emptyTone.setTone(Tone.OPENNESS, new Tone());
            emptyTone.setTone(Tone.CONSCIENTIOUSNESS, new Tone());
            emptyTone.setTone(Tone.EXTRAVERSION, new Tone());
            emptyTone.setTone(Tone.AGREEABLENESS, new Tone());
            emptyTone.setTone(Tone.EMOTIONAL_RANGE, new Tone());
            return emptyTone;
        }
    }

    public static List<ToneBasic> getToneBasicList() {
        List<ToneBasic> toneBasicList = new ArrayList<ToneBasic>();
        File toneFile = new File(PetChatBotApp.getContext().getFilesDir(), StaticVars.TONE_FILE);
        try {
            List<String> lines = FileUtils.readLines(toneFile, "UTF-8");
            toneBasicList.add(new ToneBasic(Tone.ANGER, getFirstFloatFromString(lines.get(0))));
            toneBasicList.add(new ToneBasic(Tone.DISGUST, getFirstFloatFromString(lines.get(1))));
            toneBasicList.add(new ToneBasic(Tone.FEAR, getFirstFloatFromString(lines.get(2))));
            toneBasicList.add(new ToneBasic(Tone.JOY, getFirstFloatFromString(lines.get(3))));
            toneBasicList.add(new ToneBasic(Tone.SADNESS, getFirstFloatFromString(lines.get(4))));

            toneBasicList.add(new ToneBasic(Tone.ANALYTICAL, getFirstFloatFromString(lines.get(5))));
            toneBasicList.add(new ToneBasic(Tone.CONFIDENT, getFirstFloatFromString(lines.get(6))));
            toneBasicList.add(new ToneBasic(Tone.TENTATIVE, getFirstFloatFromString(lines.get(7))));

            toneBasicList.add(new ToneBasic(Tone.OPENNESS, getFirstFloatFromString(lines.get(8))));
            toneBasicList.add(new ToneBasic(Tone.CONSCIENTIOUSNESS, getFirstFloatFromString(lines.get(9))));
            toneBasicList.add(new ToneBasic(Tone.EXTRAVERSION, getFirstFloatFromString(lines.get(10))));
            toneBasicList.add(new ToneBasic(Tone.AGREEABLENESS, getFirstFloatFromString(lines.get(11))));
            toneBasicList.add(new ToneBasic(Tone.EMOTIONAL_RANGE, getFirstFloatFromString(lines.get(12))));

            return toneBasicList;

        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static float getFirstFloatFromString(String s) {
        Scanner sc = new Scanner(s);
        return sc.nextFloat();
    }

    private static Tone getToneLine(String line) {
        if(StringUtils.isEmpty(line)) {
            return new Tone();
        }
        String split[] = StringUtils.split(line);
        Tone tone = new Tone();
        tone.setValue(Float.parseFloat(split[0]));
        tone.setCount(Integer.parseInt(split[1]));
        float[] prevVal = new float[5];
        for(int i=2; i<7; i++) {
            prevVal[i-2] = Float.parseFloat(split[i]);
        }
        tone.setPrevVal(prevVal);
        return tone;
    }
}
