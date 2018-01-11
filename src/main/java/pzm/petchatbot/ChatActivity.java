package pzm.petchatbot;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pzm.petchatbot.Chat.ChatArrayAdapter;
import pzm.petchatbot.Chat.ChatMessage;
import pzm.petchatbot.Entity.TeachEntity;
import pzm.petchatbot.Tones.Tone.ToneBasic;
import pzm.petchatbot.Tones.Tone.ToneEnum;
import pzm.petchatbot.Tones.ToneAnalyzer;
import pzm.petchatbot.Utils.ChatHelper;
import pzm.petchatbot.Utils.StaticVars;

public class ChatActivity extends AppCompatWithToolbarActivity {

    private ChatArrayAdapter chatArrayAdapter;
    private EditText chatText;
    private ChatterBotSession botSession;
    private ListView listView;
    private Toast toast;

    @Override
    protected void onStop() {
        ChatHelper.saveChat(chatArrayAdapter);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chat);
        configureActivityViews();
        super.onCreate(savedInstanceState);


    }

    private void configureActivityViews() {
        chatArrayAdapter = ChatHelper.loadChat();

        chatText = (EditText) findViewById(R.id.chatText);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                    sendMessage(null);
                }
                return false;
            }
        });

        AsyncTask x = new GetChatterBotTask().execute("");

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(chatArrayAdapter);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
        listView.setSelection(chatArrayAdapter.getCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String text = results.get(0).replace("'","");
                chatText.setText(text);
            }
        }
    }

    public void voiceInput(View view) {
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getClass()
                    .getPackage().getName());

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            int noOfMatches = 1;
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, noOfMatches);

            ChatActivity.this.startActivityForResult(intent, 1001); //1001 = activity request code
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Google voice recognition not found.", Toast.LENGTH_LONG).show();
        }
    }

    public void sendMessage(View view) {
        String text = chatText.getText().toString();
        addChatMessageToAdapter(text);
        getBotResponse(text);
        chatText.setText("");
    }

    private void addChatMessageToAdapter(String text) {
        chatArrayAdapter.trimTo(StaticVars.MAX_CHAT_SAVED_LINES);
        chatArrayAdapter.add(new ChatMessage(false, text));
    }

    private void makeToneToast(String text) {
        Random rand = new Random();
        if(rand.nextFloat() < .22f) {
            return;
        }


        ToneBasic max = ToneAnalyzer.analyzeTone(text);
        try {
            if(max.getValue()==0.0f) { return; }

            ToneEnum toneEnum = ToneEnum.getToneEnumById(max.getToneId());

            toast = Toast.makeText(this, "+ " + toneEnum.getName(), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(toneEnum.getColor());
            toast.show();
        } catch (Exception e) {
            return;
        }
    }


    protected void getBotResponse(String queryText) {
        List<TeachEntity> teachEntityList = TeachEntity.find(TeachEntity.class, "userentry = ?", queryText.trim());
        if(teachEntityList.size()>0) {
            ToneAnalyzer.analyzeTone(queryText);
            sendBotChatMessage(teachEntityList.get(0).getBotreply());
        } else {
            makeToneToast(queryText);
            ChatterBotGetMessageTask task = new ChatterBotGetMessageTask();
            task.execute(new ChatterBotGetMessageTaskParams(botSession, chatText.getText().toString()));
        }
    }

    class ChatterBotGetMessageTask extends AsyncTask<ChatterBotGetMessageTaskParams, Void, String> {
        @Override
        protected String doInBackground(ChatterBotGetMessageTaskParams... botSession) {
            try {
                String response = (botSession[0].botSession.think(botSession[0].text));
                return response;
            }catch (Exception e) {
                e.printStackTrace();
            }

            return "Oops! Tomo is having a problem, please wait a second...";
        }
        @Override
        protected void onPostExecute(String text) {
            sendBotChatMessage(text);
        }
    }

    class ChatterBotGetMessageTaskParams{
        ChatterBotSession botSession;
        String text;
        ChatterBotGetMessageTaskParams(ChatterBotSession botSession, String text) {
            this.botSession = botSession;
            this.text = text;

        }

    }

    protected void sendBotChatMessage(String text){
        chatArrayAdapter.add(new ChatMessage(true, text));
    }



    public class GetChatterBotTask extends AsyncTask<String, Void, String> {
        ProgressDialog progDailog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(ChatActivity.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }

        protected String doInBackground(String... urls) {
            ChatterBotFactory factory = new ChatterBotFactory();
            try {
                ChatterBot bot = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
                botSession = bot.createSession();
            } catch (Exception e) {
                //error handle
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String str) {
            progDailog.dismiss();
        }
    }

}
