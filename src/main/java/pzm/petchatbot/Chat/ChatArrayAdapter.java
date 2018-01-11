package pzm.petchatbot.Chat;

import android.content.Context;
import android.media.Image;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pzm.petchatbot.R;
import pzm.petchatbot.Utils.ImageHelper;

/**
 * Created by pat on 10/11/2016.
 */
public class ChatArrayAdapter extends ArrayAdapter {

    private TextView chatText;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private RelativeLayout singleMessageContainer;


    public void add(ChatMessage object) {
        chatMessageList.add(object);
        this.notifyDataSetChanged();
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public void trimTo(int size) {
        chatMessageList = chatMessageList.subList(Math.max(chatMessageList.size()-size, 0),chatMessageList.size());
        this.notifyDataSetChanged();
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chat_singlemessage, parent, false);
        }
        singleMessageContainer = (RelativeLayout) row.findViewById(R.id.singleMessageContainer);
        ChatMessage chatMessageObj = getItem(position);
        chatText = (TextView) row.findViewById(R.id.singleMessage);
        chatText.setText(chatMessageObj.message);
        chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.bubble_a : R.drawable.bubble_b);

        ImageView imageView = (ImageView) row.findViewById(R.id.imageView2);
        TextView textView = (TextView) row.findViewById(R.id.singleMessage);
        LinearLayout linearLayout = (LinearLayout) row.findViewById(R.id.picturelayout);

        RelativeLayout.LayoutParams paramsLeftText = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsLeftText.addRule(RelativeLayout.RIGHT_OF, linearLayout.getId());

        RelativeLayout.LayoutParams paramsRightText = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsRightText.addRule(RelativeLayout.LEFT_OF, linearLayout.getId());

        RelativeLayout.LayoutParams paramsLeftLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsLeftLayout.addRule(RelativeLayout.RIGHT_OF, linearLayout.getId());

        RelativeLayout.LayoutParams paramsRightLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsRightLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


        if(chatMessageObj.left) {
            singleMessageContainer.setGravity(Gravity.LEFT);
            textView.setLayoutParams(paramsLeftText);
            linearLayout.setLayoutParams(paramsLeftLayout);
            ImageHelper.updateBotImageColoring(imageView);
        } else {

            singleMessageContainer.setGravity(Gravity.RIGHT);

            textView.setLayoutParams(paramsRightText);
            linearLayout.setLayoutParams(paramsRightLayout);
            imageView.setImageResource(R.drawable.mychatface);
        }
        return row;
    }
}