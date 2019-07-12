package com.arkainfoteck.smallbasket.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arkainfoteck.smallbasket.Adapter.ProductNewAdapter;
import com.arkainfoteck.smallbasket.model.ProductNewModel;
import com.arkainfoteck.smallbasket.R;

import java.util.ArrayList;

public class ProductsNewActivity extends AppCompatActivity {
    RecyclerView products;
    ProductNewAdapter productNewAdapter;
    ArrayList<ProductNewModel>productNewModels;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_new);
        products=findViewById(R.id.products);
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        products.setLayoutManager(gridLayoutManager);
        productNewModels=new ArrayList<>();
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"","","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"","","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"","","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"","","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"","","345"));
       /* productNewAdapter=new ProductNewAdapter(ProductsNewActivity.this,productNewModels);
        products.setAdapter(productNewAdapter);*/


    }
}
