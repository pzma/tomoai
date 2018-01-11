package pzm.petchatbot.Tones.ToneInfoAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

import pzm.petchatbot.R;
import pzm.petchatbot.Tones.Tone.ToneEnum;

/**
 * Created by pat on 10/15/2016.
 */
public class ToneInfoListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private ArrayList<ToneEnum> mData = new ArrayList<>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    public ToneInfoListAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final ToneEnum item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final ToneEnum item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ToneEnum getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.tone_info_single_item, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.label);
                    holder.textView.setBackgroundColor(mData.get(position).getColor());
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.tone_info_header, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    holder.textView.setBackgroundColor(mData.get(position).getColor());
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.textView.setBackgroundColor(mData.get(position).getColor());

        }
        holder.textView.setText(mData.get(position).getName());

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

}
