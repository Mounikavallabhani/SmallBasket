package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.model.OrderHistoryModel;
import com.arkainfoteck.smallbasket.R;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<OrderHistoryModel>orderHistoryModels;
    View view;

    public OrderHistoryAdapter(Context context, ArrayList<OrderHistoryModel> orderHistoryModels) {
        this.context = context;
        this.orderHistoryModels = orderHistoryModels;
    }

    @NonNull
    @Override
    public OrderHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.orderhistoryadapter,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.ViewHolder viewHolder, int i) {
       OrderHistoryModel orderHistoryModel=orderHistoryModels.get(i);
       viewHolder.ordernumber.setText(orderHistoryModel.getOrdernumber());
       viewHolder.orderdate.setText(orderHistoryModel.getOrederdate());
       viewHolder.orderstatus.setText(orderHistoryModel.getOrderstatus());
       viewHolder.orderamount.setText(orderHistoryModel.getOtderamount());
    }

    @Override
    public int getItemCount() {
        return orderHistoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ordernumber,orderdate,orderstatus,orderamount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ordernumber=itemView.findViewById(R.id.ordernumber);
            orderdate=itemView.findViewById(R.id.orderdate);
            orderstatus=itemView.findViewById(R.id.orderstatus);
            orderamount=itemView.findViewById(R.id.orderstatus);
        }
    }
}
