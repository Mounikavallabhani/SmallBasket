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
import com.arkainfoteck.smallbasket.model.RestarentModel;
import com.arkainfoteck.smallbasket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestarentAdapter extends RecyclerView.Adapter<RestarentAdapter.ViewHolder> {
    Context context;
    ArrayList<RestarentModel>restarentModels;
    View view;

    public RestarentAdapter(Context context, ArrayList<RestarentModel> restarentModels) {
        this.context = context;
        this.restarentModels = restarentModels;
    }

    @NonNull
    @Override
    public RestarentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.restarentadapter,viewGroup,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RestarentAdapter.ViewHolder viewHolder, int i) {
        RestarentModel restarentModel=restarentModels.get(i);
        Picasso.with(context)
                .load("https://www.smallbasket.store/assets/img/restaurants/"+restarentModel.getRestarentimage())
                .into(viewHolder.resimage);
        viewHolder.restname.setText(restarentModel.getRestarentname());
        if(restarentModel.getRestarenttype().equals("veg")){
            viewHolder.resttype.setText(" VEG");

        }else {
            viewHolder.resttype.setText(" VEG & NON-VEG");

        }
        viewHolder.restviews.setText(restarentModel.getReviews());

    }

    @Override
    public int getItemCount() {
        return restarentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;
        ImageView resimage;
        TextView restname,resttype,restviews;
        public ViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context=context;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            resimage=itemView.findViewById(R.id.restrentimage);
            restname=itemView.findViewById(R.id.restatentname);
            resttype=itemView.findViewById(R.id.restarenttype);
            restviews=itemView.findViewById(R.id.restarentreviews);
        }

        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            RestarentModel restarentModel=restarentModels.get(position);

          //  System.out.println("only_one"+restarentModel.getId()+"  "+restarentModel.getRestarentimage()+"  "+restarentModel.getProduct_type()+"  "+restarentModel.getRestarenttype());
            Intent intent=new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("EXTRA_SESSION_ID", restarentModel.getId());
            intent.putExtra("EXTRA_SESSION_TYPE",restarentModel.getProduct_type());
            context.startActivity(intent);

        }
    }
}
