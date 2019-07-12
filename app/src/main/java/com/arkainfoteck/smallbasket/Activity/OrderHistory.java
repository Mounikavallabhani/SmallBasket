package com.arkainfoteck.smallbasket.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arkainfoteck.smallbasket.Adapter.OrderHistoryAdapter;
import com.arkainfoteck.smallbasket.model.OrderHistoryModel;
import com.arkainfoteck.smallbasket.R;
import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    RecyclerView orderhistory;
    OrderHistoryAdapter orderHistoryAdapter;
    ArrayList<OrderHistoryModel>orderHistoryModels;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        orderhistory=findViewById(R.id.orderhistory);
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        orderhistory.setLayoutManager(gridLayoutManager);
        orderHistoryModels=new ArrayList<>();
        orderHistoryModels.add(new OrderHistoryModel("234","Aprial 04,2019","Canceled","567"));
        orderHistoryModels.add(new OrderHistoryModel("234","Aprial 04,2019","Canceled","567"));
        orderHistoryModels.add(new OrderHistoryModel("234","Aprial 04,2019","Canceled","567"));
        orderHistoryModels.add(new OrderHistoryModel("234","Aprial 04,2019","Canceled","567"));
        orderHistoryModels.add(new OrderHistoryModel("234","Aprial 04,2019","Canceled","567"));
        orderHistoryAdapter=new OrderHistoryAdapter(getApplicationContext(),orderHistoryModels);
        orderhistory.setAdapter(orderHistoryAdapter);

    }
}
