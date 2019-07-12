package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.Activity.MainActivity;
import com.arkainfoteck.smallbasket.Activity.ProductDescriptionActivity;
import com.arkainfoteck.smallbasket.model.HomeModel;
import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.RestarentModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    ArrayList<HomeModel>homeModels;
    View view;

    public HomeAdapter(Context context, ArrayList<HomeModel> homeModels) {
        this.context = context;
        this.homeModels = homeModels;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.homeadapter,viewGroup,false);
        return new ViewHolder(view,context);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder viewHolder, int i) {
        HomeModel homeModel=homeModels.get(i);

        Picasso.with(context)
                .load("https://www.smallbasket.store/assets/img/restaurants/"+homeModel.getShopimage())
                .into(viewHolder.shopimageid);
        viewHolder.shoptextid.setText(homeModel.getShopname());

    }

    @Override
    public int getItemCount() {
        return homeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;
        ImageView shopimageid;
        TextView shoptextid;
        public ViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context=context;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            shopimageid=itemView.findViewById(R.id.shopimageid);
            shoptextid=itemView.findViewById(R.id.shoptextid);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            HomeModel homeModel=homeModels.get(position);
            Intent intent =new Intent(this.context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("EXTRA_SESSION_ID", homeModel.getId());
            intent.putExtra("EXTRA_SESSION_TYPE",homeModel.getProduct_type());

            context.startActivity(intent);

        }
    }
}
