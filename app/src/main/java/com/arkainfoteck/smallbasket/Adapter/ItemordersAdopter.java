package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.Itemsordermodel;

import java.util.ArrayList;

public class ItemordersAdopter extends RecyclerView.Adapter<ItemordersAdopter.Myview> {

    Context context;
    ArrayList<Itemsordermodel> ordermodels;

    public ItemordersAdopter(Context context, ArrayList<Itemsordermodel> ordermodels) {
        this.context = context;
        this.ordermodels = ordermodels;
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.itemsorder_design_list,viewGroup,false);
        return new Myview(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull Myview myview, int i) {
       Itemsordermodel ordermodel=ordermodels.get(i);


    }

    @Override
    public int getItemCount() {
        return ordermodels.size();
    }

    public class Myview extends RecyclerView.ViewHolder {
        TextView itemorder_title_name,itemorder_sub_title_name,item_ordered_price_kg,item_order_price,item_order_quantity;
        ImageView itemorder_image_name;
        Button view;
        Context context;
        public Myview(@NonNull View itemView, final Context context) {
            super(itemView);
            this.context=context;
            itemorder_title_name=itemView.findViewById(R.id.itemorder_title_name);
            itemorder_sub_title_name=itemView.findViewById(R.id.itemorder_sub_title_name);
            item_ordered_price_kg=itemView.findViewById(R.id.item_ordered_price_kg);
            item_order_price=itemView.findViewById(R.id.item_order_price);
            item_order_quantity=itemView.findViewById(R.id.item_order_quantity);
            itemorder_image_name=itemView.findViewById(R.id.itemorder_image_name);

        }
    }
}
