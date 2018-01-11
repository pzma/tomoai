package pzm.petchatbot.Tones.Tone;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by pat on 9/29/2016.
 */
public class ToneFull {

    private Map<String, Tone> toneMap;

    public ToneFull() {
        toneMap = Tone.getEmptyToneMap();
    }


    public float getValue(String toneId) {
        return toneMap.get(toneId).getValue();
    }

    public void setTone(String toneId, Tone tone) {
        toneMap.put(toneId, tone);
    }

    public void setToneValue(String toneId, float toneValue) {
        Tone tone = toneMap.get(toneId);
        tone.setValue(toneValue);
        toneMap.put(toneId, tone);
    }

    public Tone getTone(String toneId) {return toneMap.get(toneId);}

    public Set<String> getEntrySet() {
        return toneMap.keySet();
    }


}
