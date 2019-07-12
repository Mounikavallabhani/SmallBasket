package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.Activity.MyOrdersView;
import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.MyordersModel;

import java.util.ArrayList;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.Myclass> {
    Context context;
    ArrayList<MyordersModel>myordersModels;
    View view;

    public MyOrdersAdapter(Context context, ArrayList<MyordersModel> myordersModels) {
        this.context = context;
        this.myordersModels = myordersModels;
    }

    @NonNull
    @Override
    public MyOrdersAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.myordersadapter,viewGroup,false);
        return new Myclass(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.Myclass myclass, int i) {
        MyordersModel myordersModel=myordersModels.get(i);
        myclass.complete.setText(myordersModel.getComplete());

    }

    @Override
    public int getItemCount() {
        return myordersModels.size();
    }

    public class Myclass extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView complete;
        Context context;
        public Myclass(@NonNull View itemView,Context context) {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            this.context=context;
            complete=itemView.findViewById(R.id.complete);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, MyOrdersView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }
}
