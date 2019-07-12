package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    // View lookup cache
    private static class ViewHolder {
        LinearLayout linearLayout;
        TextView txtName,txtprice,txtamount;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtprice = (TextView) convertView.findViewById(R.id.place);
            //viewHolder.txtamount = (TextView) convertView.findViewById(R.id.date);
            viewHolder.linearLayout=(LinearLayout)convertView.findViewById(R.id.linearlayout);


             /* viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      viewHolder.linearLayout.setBackgroundResource(R.drawable.border_shape);

                  }
              });*/
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
        viewHolder.txtprice.setText(dataModel.getPrice());
       // viewHolder.txtamount.setText(dataModel.getAmount());
         // Return the completed view to render on screen
        return convertView;
    }

   /* public void setSelected(int pos) {
        try {
            if (dataSet.size() > 1) {
                dataSet.get(mPref.getInt("position", 0)).setSelected(false);
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            dataSet.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
