package pzm.petchatbot.Utils;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

import pzm.petchatbot.R;
import pzm.petchatbot.Tones.Tone.ToneBasic;
import pzm.petchatbot.Tones.Tone.ToneEnum;

/**
 * Created by pat on 10/13/2016.
 * 255 255 255 is white
 */

public class ImageHelper {

    static List<ToneBasic> list = null;
    static float emotionalTotal = 0, languageTotal = 0, socialTotal = 0;

    public static void updateToneValues() {
        //TODO: move this out of memory maybe??
        list = ToneHelper.getToneBasicList();
        for (int i = 0; i < 5; i++) {
            emotionalTotal += list.get(i).getValue();
        }

        for (int i = 5; i < 8; i++) {
            languageTotal += list.get(i).getValue();
        }

        for (int i = 8; i < 13; i++) {
            socialTotal += list.get(i).getValue();
        }
    }

    public static void updateBotImageColoring(ImageView imageView) {

        List<ToneBasic> emoList = list.subList(0, 5);
        Collections.sort(emoList);
        Drawable mdrawable = getCorrectImage(emoList);
        imageView.setImageDrawable(mdrawable);
    }

    private static Drawable getCorrectColor(Drawable drawable, List<ToneBasic> emoList) {

        int redTotal = 0, greenTotal = 0, blueTotal = 0;
        for (int i = 0; i < 5; i++) {
            ToneEnum te = ToneEnum.getToneEnumById(emoList.get(i).getToneId());
            redTotal += (5 - i) * ((te.getRed() * emoList.get(i).getValue()) / 5);
            greenTotal += (5 - i) * ((te.getGreen() * emoList.get(i).getValue()) / 5);
            blueTotal += (5 - i) * ((te.getBlue() * emoList.get(i).getValue()) / 5);
        }

        if(redTotal == greenTotal && greenTotal == blueTotal) {
            redTotal = 255; blueTotal = 255; greenTotal = 255;
        } else if(redTotal>=greenTotal && redTotal>=blueTotal) {
            blueTotal = blueTotal * (255/redTotal);
            greenTotal = greenTotal * (255/redTotal);
            redTotal = 255;
        } else if(greenTotal >= redTotal && greenTotal >= blueTotal) {
            blueTotal = blueTotal * (255/greenTotal);
            redTotal = redTotal * (255/greenTotal);
            greenTotal = 255;
        } else if(blueTotal >= redTotal && blueTotal >= greenTotal) {
            redTotal = redTotal * (255/blueTotal);
            greenTotal = greenTotal * (255/blueTotal);
            blueTotal = 255;
        }

        drawable.setColorFilter(Color.rgb(Math.min(redTotal, 255), Math.min(greenTotal, 255), Math.min(blueTotal, 255)), PorterDuff.Mode.MULTIPLY);
        return drawable;
    }

    private static Drawable brightIt(Drawable drawable, ToneBasic toneBasic) {
        int fb = Math.max(0,(int)((.9-toneBasic.getValue())*6)); //if 1.0 then want 0, if 0.0 want 30

        ColorMatrix cmB = new ColorMatrix();
        cmB.set(new float[] {
                1, 0, 0, 0, fb,
                0, 1, 0, 0, fb,
                0, 0, 1, 0, fb,
                0, 0, 0, 1, 0   });

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(cmB);

        ColorMatrixColorFilter f = new ColorMatrixColorFilter(colorMatrix);
        drawable.setColorFilter(f);
        return drawable;
    }


    private static Drawable getCorrectImage(List<ToneBasic> list) {

        if((list.get(0).getValue()) > .65 && (list.get(0).getValue() > (list.get(1).getValue() + .05))) {
            if(ToneEnum.getToneEnumById(list.get(0).getToneId()).equals(ToneEnum.ANGER)) {
                Drawable drawable = PetChatBotApp.getContext().getResources().getDrawable(R.drawable.angerchatface);
                drawable = brightIt(drawable, list.get(0));
                return drawable;
            } else if(ToneEnum.getToneEnumById(list.get(0).getToneId()).equals(ToneEnum.DISGUST)) {
                Drawable drawable = PetChatBotApp.getContext().getResources().getDrawable(R.drawable.disgchatface);
                drawable = brightIt(drawable, list.get(0));
                return drawable;
            } else if(ToneEnum.getToneEnumById(list.get(0).getToneId()).equals(ToneEnum.FEAR)) {
                Drawable drawable = PetChatBotApp.getContext().getResources().getDrawable(R.drawable.fearchatface);
                drawable = brightIt(drawable, list.get(0));
                return drawable;
            } else if(ToneEnum.getToneEnumById(list.get(0).getToneId()).equals(ToneEnum.JOY)) {
                Drawable drawable = PetChatBotApp.getContext().getResources().getDrawable(R.drawable.joychatface);
                drawable = brightIt(drawable, list.get(0));
                return drawable;
            } else if(ToneEnum.getToneEnumById(list.get(0).getToneId()).equals(ToneEnum.SADNESS)) {
                Drawable drawable = PetChatBotApp.getContext().getResources().getDrawable(R.drawable.sadnesschatface);
                drawable = brightIt(drawable, list.get(0));
                return drawable;
            }
        } else {
            return getCorrectColor(PetChatBotApp.getContext().getResources().getDrawable(R.drawable.petchatface), list);
        }
        return getCorrectColor(PetChatBotApp.getContext().getResources().getDrawable(R.drawable.petchatface), list);

    }
}
