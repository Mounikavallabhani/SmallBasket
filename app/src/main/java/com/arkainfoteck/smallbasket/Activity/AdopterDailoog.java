package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.R;

import java.util.ArrayList;

public class AdopterDailoog extends RecyclerView.Adapter<AdopterDailoog.MtView> {

    Context context;
    ArrayList<ModelDailog>modelDailogs;

    public AdopterDailoog(Context context, ArrayList<ModelDailog> modelDailogs) {
        this.context = context;
        this.modelDailogs = modelDailogs;
    }

    @NonNull
    @Override
    public MtView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.layout_items_list,viewGroup,false);
        return new MtView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MtView mtView, int i) {

        ModelDailog modelDailog=modelDailogs.get(i);
        mtView.textView.setText(modelDailog.getDate());

    }

    @Override
    public int getItemCount() {
        return modelDailogs.size();
    }

    public class MtView  extends RecyclerView.ViewHolder{

        TextView textView;
        public MtView(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textviewdata);
        }
    }
}
