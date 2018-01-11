package pzm.petchatbot.Tones.Tone;

/**
 * Created by pat on 10/11/2016.
 */
public class ToneBasic implements  Comparable<ToneBasic>{
    private String toneId;
    private float value;

    public ToneBasic() {
        this.value = 0.0f;
        this.toneId = "";
    }
    public ToneBasic(String id, float value) {
        this.toneId = id;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getToneId() {
        return toneId;
    }

    public void setToneId(String toneId) {
        this.toneId = toneId;
    }

    public int compareTo(ToneBasic other) {
        return Double.compare(other.getValue(), this.getValue());
    }

}
