package com.psi.gardenerasistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MessagesListViewAdapter extends ArrayAdapter<MessageViewModel> implements View.OnClickListener {
    private ArrayList<MessageViewModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtSender;
        TextView txtTitle;
        TextView txtDate;
        TextView txtInicial;
    }

    public MessagesListViewAdapter(ArrayList<MessageViewModel> data, Context context) {
        super(context, R.layout.messages_listview_row_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        MessageViewModel dataModel = (MessageViewModel) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MessageViewModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.messages_listview_row_item, parent, false);
            viewHolder.txtSender = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.date);
            viewHolder.txtInicial = (TextView) convertView.findViewById(R.id.inicial);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        viewHolder.txtSender.setText(dataModel.getSender());
        viewHolder.txtTitle.setText(dataModel.getTitle());
        viewHolder.txtDate.setText(format.format(dataModel.getReceivedDate()));
        viewHolder.txtInicial.setText(dataModel.getSender().substring(0, 1));
        // Return the completed view to render on screen
        return convertView;
    }
}
