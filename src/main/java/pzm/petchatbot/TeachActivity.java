package pzm.petchatbot;

import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.List;
import java.util.Random;

import pzm.petchatbot.Entity.TeachEntity;
import pzm.petchatbot.Utils.ImageHelper;


public class TeachActivity extends AppCompatWithToolbarActivity {

    private EditText queryText, responseText;
    @Override
    protected void onRestart() {
// TODO Auto-generated method stub
        super.onRestart();

        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        ImageHelper.updateBotImageColoring(imageView);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        setContentView(R.layout.activity_teach);

        super.onCreate(savedInstanceState);

        queryText = (EditText) findViewById(R.id.personTeachText);
        responseText = (EditText) findViewById(R.id.botTeachText);
        Button clickButton = (Button) findViewById(R.id.buttonTeach);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                teachBot();
            }
        });

        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        ImageHelper.updateBotImageColoring(imageView);
    }

    private void teachBot() {
        if(queryText.getText().toString().trim().isEmpty() || responseText.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill both chat bubbles!", Toast.LENGTH_SHORT).show();
        }
        TeachEntity teachEntity = new TeachEntity(queryText.getText().toString().trim(), responseText.getText().toString());
        teachEntity.save();
        showRandomToast();
    }

    private void showRandomToast() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        double d = rand.nextDouble() * .07;

        if(n<55) {
            return;
        } else if(n<65) {
            Toast.makeText(getApplicationContext(), "Learning +" + String.format("%.1f", d*n) + "%", Toast.LENGTH_SHORT).show();
        } else if(n<71) {
            Toast.makeText(getApplicationContext(), "Loyalty +" + String.format("%.1f", d*n) + "%", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Obedience +" + String.format("%.1f", d*n) + "%", Toast.LENGTH_SHORT).show();
        }
    }



}
