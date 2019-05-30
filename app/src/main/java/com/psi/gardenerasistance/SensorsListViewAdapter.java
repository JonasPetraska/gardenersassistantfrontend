package com.psi.gardenerasistance;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SensorsListViewAdapter extends ArrayAdapter<SensorViewModel> implements View.OnClickListener
{

    private ArrayList<SensorViewModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtParameter;
        ImageView imageType;
        ImageView imageParameters;
    }

    public SensorsListViewAdapter(ArrayList<SensorViewModel> data, Context context) {
        super(context, R.layout.sensors_listview_row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        SensorViewModel dataModel=(SensorViewModel)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SensorViewModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.sensors_listview_row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtParameter = (TextView) convertView.findViewById(R.id.displayParameter);
            viewHolder.imageParameters = (ImageView) convertView.findViewById(R.id.parameters);
            viewHolder.imageType = (ImageView) convertView.findViewById(R.id.typeView);
            //viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtParameter.setText(dataModel.getDisplayParameter());
        if(dataModel.getType() == SensorViewModel.TypesEnum.Temperature) {
            viewHolder.imageType.setImageResource(R.drawable.ic_thermometer);
        }else{
            viewHolder.imageType.setImageResource(R.drawable.ic_humidity);
        }
        //viewHolder.txtVersion.setText(dataModel.getVersion_number());

        viewHolder.imageParameters.setOnClickListener(this);
        viewHolder.imageParameters.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
