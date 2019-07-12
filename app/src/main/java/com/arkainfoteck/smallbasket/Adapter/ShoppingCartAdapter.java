package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.model.ShoppingCartModel;
import com.arkainfoteck.smallbasket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    Context context;
    ArrayList<ShoppingCartModel>shoppingCaerModels;
    View view;

    public ShoppingCartAdapter(Context context, ArrayList<ShoppingCartModel> shoppingCaerModels) {
        this.context = context;
        this.shoppingCaerModels = shoppingCaerModels;
    }

    @NonNull
    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.shoppingcart,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ViewHolder viewHolder, int i) {
        ShoppingCartModel shoppingCaerModel=shoppingCaerModels.get(i);
     /*   Picasso.with(context).load(shoppingCaerModel.getImage()).into(viewHolder.shoppingimage);
        viewHolder.shoppingname.setText(shoppingCaerModel.getName());
        viewHolder.shoppingcost.setText(shoppingCaerModel.getCost());
        viewHolder.shoppingquantity.setText(shoppingCaerModel.getQuantity());*/

    }

    @Override
    public int getItemCount() {
        return shoppingCaerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shoppingimage;
        TextView   shoppingname,shoppingquantity,shoppingcost;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         /*   shoppingimage=itemView.findViewById(R.id.shoppingimage);
            shoppingname=itemView.findViewById(R.id.shoppingname);
            shoppingquantity=itemView.findViewById(R.id.shoppingquantity);
            shoppingcost=itemView.findViewById(R.id.shoppingcost);*/

        }
    }
}
