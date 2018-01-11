package pzm.petchatbot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.io.File;
import java.io.PrintWriter;

import pzm.petchatbot.Utils.ChartHelper;
import pzm.petchatbot.Utils.PetChatBotApp;
import pzm.petchatbot.Utils.StaticVars;

/**
 * Created by pat on 10/14/2016.
 */
public class AppCompatWithToolbarActivity extends AppCompatActivity{
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;

        switch (item.getItemId()) {
            case R.id.feedback:
                intent = new Intent(this, FeedbackActivity.class);
                startActivity(intent);
                return true;
            case R.id.toneHelp:
                intent = new Intent(this, ToneinfoActivity.class);
                startActivity(intent);
                return true;
            case R.id.clearData:
                clearAllData();
            default:
                return true;
        }
    }

    private void clearAllData() {

        new AlertDialog.Builder(this)
                .setTitle("DELETE")
                .setMessage("Clear all A.I. knowledge?") //get from text file
                .setPositiveButton("DELETE" , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //clear DB
                        SugarContext.terminate();
                        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
                        schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
                        SugarContext.init(getApplicationContext());
                        schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());

                        //clear tone info
                        try {
                            File chatFile = new File(PetChatBotApp.getContext().getFilesDir(), StaticVars.CHAT_CONVERSATION_FILE);
                            PrintWriter pwchat = new PrintWriter(chatFile);
                            pwchat.close();

                            File toneFile = new File(PetChatBotApp.getContext().getFilesDir(), StaticVars.TONE_FILE);
                            PrintWriter pwtone = new PrintWriter(toneFile);
                            pwtone.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finish();
                        startActivity(getIntent());
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setLogo(R.drawable.ic_menu_manage);
        setSupportActionBar(toolbar);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        ){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                setUpBarCharts();
                super.onDrawerSlide(drawerView, slideOffset);
            };
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    private void setUpBarCharts() {
        HorizontalBarChart chart1 = (HorizontalBarChart) findViewById(R.id.chart1);
        ChartHelper.configureChart(chart1);
        HorizontalBarChart chart2 = (HorizontalBarChart) findViewById(R.id.chart2);
        ChartHelper.configureChart(chart2);
        HorizontalBarChart chart3 = (HorizontalBarChart) findViewById(R.id.chart3);
        ChartHelper.configureChart(chart3);


        BarData data1 = new BarData(ChartHelper.getXAxisValuesEmotional(), ChartHelper.getDataSetEmotional());
        chart1.setData(data1);
        chart1.invalidate();

        BarData data2 = new BarData(ChartHelper.getXAxisValuesLanguage(), ChartHelper.getDataSetLanguage());
        chart2.setData(data2);
        chart2.invalidate();

        BarData data3 = new BarData(ChartHelper.getXAxisValuesSocial(), ChartHelper.getDataSetSocial());
        chart3.setData(data3);
        chart3.invalidate();
    }
}
