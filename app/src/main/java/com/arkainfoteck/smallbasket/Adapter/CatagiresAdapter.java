package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.Activity.DashBoard;
import com.arkainfoteck.smallbasket.model.CatagiresModel;
import com.arkainfoteck.smallbasket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatagiresAdapter extends RecyclerView.Adapter<CatagiresAdapter.ViewHolder> {
    Context context;
    ArrayList<CatagiresModel>catagiresModels;
    View view;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public CatagiresAdapter(Context context, ArrayList<CatagiresModel> catagiresModels) {
        this.context = context;
        this.catagiresModels = catagiresModels;
    }

    @NonNull
    @Override
    public CatagiresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.catagiresadapter,viewGroup,false);
        return new ViewHolder(view,context,catagiresModels);
    }

    @Override
    public void onBindViewHolder(@NonNull CatagiresAdapter.ViewHolder viewHolder, int i) {
        CatagiresModel catagiresModel=catagiresModels.get(i);
        viewHolder.catagires_name.setText(catagiresModel.getCatagires_name());

        Picasso.with(context)
                .load("https://smallbasket.store/assets/img/products/"+catagiresModel.getCatagires_image())
                .into(viewHolder.catagires_image);

    }

    @Override
    public int getItemCount() {
        return catagiresModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView catagires_image;
        TextView catagires_name;
        Context context;
        ArrayList<CatagiresModel>catagiresModels;
        public ViewHolder(@NonNull View itemView,Context context,ArrayList<CatagiresModel>catagiresModels) {
            super(itemView);
            this.context=context;
            this.catagiresModels=catagiresModels;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            catagires_image=itemView.findViewById(R.id.catagires_image);
            catagires_name=itemView.findViewById(R.id.catagires_name);
        }

        @Override
        public void onClick(View v) {
            int i=getAdapterPosition();
            CatagiresModel catagiresModel=this.catagiresModels.get(i);
            String id=catagiresModel.getCatagires_id();
            Intent intent=new Intent(context, DashBoard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sharedPreferences=context.getSharedPreferences("foodtype", Context.MODE_PRIVATE);
            editor=sharedPreferences.edit();
            editor.putString("Food",id);
            editor.commit();
            editor.apply();
            context.startActivity(intent);
        }
    }
}
