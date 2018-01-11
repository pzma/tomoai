package pzm.petchatbot;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import pzm.petchatbot.Tones.Tone.ToneEnum;
import pzm.petchatbot.Tones.ToneInfoAdapter.ToneInfoListAdapter;

public class ToneinfoActivity extends ListActivity {

    private ToneInfoListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toneinfo);
        mAdapter = new ToneInfoListAdapter(this);


        mAdapter.addSectionHeaderItem(ToneEnum.EMOTIONAL);
        mAdapter.addItem(ToneEnum.ANGER);
        mAdapter.addItem(ToneEnum.DISGUST);
        mAdapter.addItem(ToneEnum.FEAR);
        mAdapter.addItem(ToneEnum.JOY);
        mAdapter.addItem(ToneEnum.SADNESS);

        mAdapter.addSectionHeaderItem(ToneEnum.LANGUAGE);
        mAdapter.addItem(ToneEnum.ANALYTICAL);
        mAdapter.addItem(ToneEnum.TENTATIVE);
        mAdapter.addItem(ToneEnum.CONFIDENT);

        mAdapter.addSectionHeaderItem(ToneEnum.SOCIAL);
        mAdapter.addItem(ToneEnum.OPENNESS);
        mAdapter.addItem(ToneEnum.CONSCIENTIOUSNESS);
        mAdapter.addItem(ToneEnum.EXTRAVERSION);
        mAdapter.addItem(ToneEnum.AGREEABLENESS);
        mAdapter.addItem(ToneEnum.EMOTIONAL_RANGE);


        setListAdapter(mAdapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final ToneEnum selectedValue = (ToneEnum) getListAdapter().getItem(position);

        new AlertDialog.Builder(ToneinfoActivity.this)
                .setTitle(selectedValue.getName())
                .setMessage(readTextFile(getResources().openRawResource(getResources().getIdentifier(selectedValue.getId(), "raw", getPackageName())))) //get from text file
                .setPositiveButton("Wiki" , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(readTextFile(getResources().openRawResource(getResources().getIdentifier("wiki_" + selectedValue.getId(), "raw", getPackageName()))) )); //getfromtext file
                        startActivity(browserIntent);
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(R.drawable.petchatface)
                .show();
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

}
