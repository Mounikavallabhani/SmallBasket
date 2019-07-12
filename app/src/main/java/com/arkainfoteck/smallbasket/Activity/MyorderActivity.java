package com.arkainfoteck.smallbasket.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arkainfoteck.smallbasket.Adapter.MyOrdersAdapter;
import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.MyordersModel;

import java.util.ArrayList;

public class MyorderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    MyOrdersAdapter myOrdersAdapter;
    ArrayList<MyordersModel>myordersModels;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        recyclerView=findViewById(R.id.myorders);
        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.itemorder_producy_items_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Order Details");
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        myordersModels=new ArrayList<>();
        myordersModels.add(new MyordersModel("Complete"));
        myordersModels.add(new MyordersModel("Complete"));
        myordersModels.add(new MyordersModel("Complete"));
        myordersModels.add(new MyordersModel("Complete"));
        myordersModels.add(new MyordersModel("Complete"));
        myordersModels.add(new MyordersModel("Complete"));
        myOrdersAdapter=new MyOrdersAdapter(getApplicationContext(),myordersModels);
        recyclerView.setAdapter(myOrdersAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
