package pzm.petchatbot.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import pzm.petchatbot.Chat.ChatArrayAdapter;
import pzm.petchatbot.Chat.ChatMessage;
import pzm.petchatbot.R;

/**
 * Created by pat on 10/11/2016.
 */
public class ChatHelper {

    final static File chatFile = new File(PetChatBotApp.getContext().getFilesDir(), StaticVars.CHAT_CONVERSATION_FILE);

    public static void saveChat(ChatArrayAdapter chatArrayAdapter) {
        String data = "";
        for(int i =0; i<chatArrayAdapter.getCount(); i++) {
            String s = chatArrayAdapter.getItem(i).message;
            s = s.replace("\n","");
            s=s.replace("\\n", "");
            s=s.replace("[!","");
            data+=s;
            if(chatArrayAdapter.getItem(i).left) {
                data+="[!\n";
            } else {
                data+="\n";
            }
        }
        FileOutputStream outstream;
        try {
            if(!chatFile.exists()){
                chatFile.createNewFile();
            }
            outstream = new FileOutputStream(chatFile);
            outstream.write(data.getBytes());
            outstream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static ChatArrayAdapter loadChat() {
        ChatArrayAdapter chatArrayAdapter = new ChatArrayAdapter(PetChatBotApp.getContext(), R.layout.chat_singlemessage);
        try {
            BufferedReader br = new BufferedReader(new FileReader(chatFile));
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.endsWith("[!")) {
                    chatArrayAdapter.add(new ChatMessage(true, line.substring(0,line.length()-2)));
                } else {
                    chatArrayAdapter.add(new ChatMessage(false, line));
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            return new ChatArrayAdapter(PetChatBotApp.getContext(), R.layout.chat_singlemessage);
        }
        return chatArrayAdapter;
    }
}
