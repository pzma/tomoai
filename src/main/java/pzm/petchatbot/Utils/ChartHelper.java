package pzm.petchatbot.Utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import pzm.petchatbot.Tones.Tone.Tone;
import pzm.petchatbot.Tones.Tone.ToneEnum;
import pzm.petchatbot.Tones.Tone.ToneFull;

/**
 * Created by pat on 10/11/2016.
 */
public class ChartHelper {
    public static ArrayList<BarDataSet> getDataSetEmotional() {
        ToneFull toneFull = ToneHelper.getToneFull();

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(new float[]{toneFull.getValue(Tone.SADNESS), 1-toneFull.getValue(Tone.SADNESS)}, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(new float[]{toneFull.getValue(Tone.JOY), 1-toneFull.getValue(Tone.JOY)}, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(new float[]{toneFull.getValue(Tone.FEAR), 1-toneFull.getValue(Tone.FEAR)}, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(new float[]{toneFull.getValue(Tone.DISGUST), 1-toneFull.getValue(Tone.DISGUST)}, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(new float[]{toneFull.getValue(Tone.ANGER), 1-toneFull.getValue(Tone.ANGER)}, 4); // May
        valueSet1.add(v1e5);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColors(new int[]{ToneEnum.SADNESS.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.JOY.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.FEAR.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.DISGUST.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.ANGER.getColor(), ToneEnum.NULLTONE.getColor()});
        barDataSet1.setDrawValues(false);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    public static ArrayList<String> getXAxisValuesEmotional() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(ToneEnum.SADNESS.getName());
        xAxis.add(ToneEnum.JOY.getName());
        xAxis.add(ToneEnum.FEAR.getName());
        xAxis.add(ToneEnum.DISGUST.getName());
        xAxis.add(ToneEnum.ANGER.getName());
        return xAxis;
    }

    public static ArrayList<BarDataSet> getDataSetLanguage() {
        ToneFull toneFull = ToneHelper.getToneFull();

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(new float[]{toneFull.getValue(Tone.CONFIDENT), 1-toneFull.getValue(Tone.CONFIDENT)}, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(new float[]{toneFull.getValue(Tone.TENTATIVE), 1-toneFull.getValue(Tone.TENTATIVE)}, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(new float[]{toneFull.getValue(Tone.ANALYTICAL), 1-toneFull.getValue(Tone.ANALYTICAL)}, 2); // Mar
        valueSet1.add(v1e3);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColors(new int[]{ToneEnum.CONFIDENT.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.TENTATIVE.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.ANALYTICAL.getColor(), ToneEnum.NULLTONE.getColor()});
        barDataSet1.setDrawValues(false);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    public static ArrayList<String> getXAxisValuesLanguage() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(ToneEnum.CONFIDENT.getName());
        xAxis.add(ToneEnum.TENTATIVE.getName());
        xAxis.add(ToneEnum.ANALYTICAL.getName());
        return xAxis;
    }

    public static ArrayList<BarDataSet> getDataSetSocial() {
        ToneFull toneFull = ToneHelper.getToneFull();

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(new float[]{toneFull.getValue(Tone.EMOTIONAL_RANGE), 1-toneFull.getValue(Tone.EMOTIONAL_RANGE)}, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(new float[]{toneFull.getValue(Tone.AGREEABLENESS), 1-toneFull.getValue(Tone.AGREEABLENESS)}, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(new float[]{toneFull.getValue(Tone.EXTRAVERSION), 1-toneFull.getValue(Tone.EXTRAVERSION)}, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(new float[]{toneFull.getValue(Tone.CONSCIENTIOUSNESS), 1-toneFull.getValue(Tone.CONSCIENTIOUSNESS)}, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(new float[]{toneFull.getValue(Tone.OPENNESS), 1-toneFull.getValue(Tone.OPENNESS)}, 4); // May
        valueSet1.add(v1e5);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColors(new int[]{ToneEnum.EMOTIONAL_RANGE.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.AGREEABLENESS.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.EXTRAVERSION.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.CONSCIENTIOUSNESS.getColor(), ToneEnum.NULLTONE.getColor(), ToneEnum.OPENNESS.getColor(), ToneEnum.NULLTONE.getColor()});
        barDataSet1.setDrawValues(false);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    public static ArrayList<String> getXAxisValuesSocial() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(ToneEnum.EMOTIONAL_RANGE.getName());
        xAxis.add(ToneEnum.AGREEABLENESS.getName());
        xAxis.add(ToneEnum.EXTRAVERSION.getName());
        xAxis.add(ToneEnum.CONSCIENTIOUSNESS.getName());
        xAxis.add(ToneEnum.OPENNESS.getName());
        return xAxis;
    }



    public static void configureChart(HorizontalBarChart chart) {
        chart.setDescription("");
        chart.animateY(800);
        chart.setBackgroundColor(Color.TRANSPARENT);
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);


        YAxis leftAxis = chart.getAxisLeft();
        YAxis rightAxis = chart.getAxisRight();
        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);
        Legend l = chart.getLegend();
        l.setEnabled(false);
    }
}
