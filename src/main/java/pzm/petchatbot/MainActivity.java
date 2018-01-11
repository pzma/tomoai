package pzm.petchatbot;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;

import org.apache.commons.io.FileUtils;

import java.io.File;

import pzm.petchatbot.Tones.ToneAnalyzer;
import pzm.petchatbot.Utils.ChartHelper;
import pzm.petchatbot.Utils.ImageHelper;
import pzm.petchatbot.Utils.StaticVars;

public class MainActivity extends AppCompatWithToolbarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        File toneFile = new File(getApplicationContext().getFilesDir(), StaticVars.TONE_FILE);
        //toneFile.delete();
        try {
            toneFile.createNewFile();
            if (toneFile.length() == 0) {
                for (int i = 0; i < 13; i++) {
                    FileUtils.writeStringToFile(toneFile, "0 0 0 0 0 0 0" + StaticVars.NEW_LINE, "UTF-8", true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageHelper.updateToneValues();

        super.onCreate(savedInstanceState);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        ImageHelper.updateBotImageColoring(imageView);

        //setUpBarCharts();

    }

    @Override
    protected void onRestart() {
// TODO Auto-generated method stub
        super.onRestart();

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        ImageHelper.updateBotImageColoring(imageView);
    }




    public void goToChat(View view) {
        //EditText editText = (EditText) findViewById(R.id.editTextTest);
        //ImageView lineColorCode = (ImageView) findViewById(R.id.imageView);
        //int color = Color.parseColor("#AE6118"); //The color u want
        //lineColorCode.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        //ToneAnalyzer.analyzeTone(editText.getText().toString());
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(intent);
    }


    public void goToTeach(View view) {
        Intent intent = new Intent(MainActivity.this, TeachActivity.class);
        startActivity(intent);
    }

}
