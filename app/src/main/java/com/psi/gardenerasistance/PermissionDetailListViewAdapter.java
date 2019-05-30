package com.psi.gardenerasistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class PermissionDetailListViewAdapter extends ArrayAdapter<String> implements View.OnClickListener {
    private ArrayList<String> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        CheckBox permissionCheckBox;
    }

    public PermissionDetailListViewAdapter(ArrayList<String> data, Context context) {
        super(context, R.layout.permission_details_listview_row_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        String dataModel = (String) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.permission_details_listview_row_item, parent, false);
            viewHolder.permissionCheckBox = (CheckBox) convertView.findViewById(R.id.permission);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.permissionCheckBox.setText(dataModel);
        viewHolder.permissionCheckBox.setChecked(new Random().nextInt() > 0 ? true : false);
        // Return the completed view to render on screen
        return convertView;
    }
}
