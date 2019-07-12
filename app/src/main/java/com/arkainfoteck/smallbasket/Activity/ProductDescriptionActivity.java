package com.arkainfoteck.smallbasket.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.Adapter.ProductNewAdapter;
import com.arkainfoteck.smallbasket.model.ProductNewModel;
import com.arkainfoteck.smallbasket.R;

import java.util.ArrayList;
public class ProductDescriptionActivity extends Activity implements ItemClickListener{
    RecyclerView productdescription;
   /* ProductDescriptionAdapter productDescriptionAdapter;
    ArrayList<ProductDescriptionModel>productDescriptionModels;*/
   ProductNewAdapter productNewAdapter;
    ArrayList<ProductNewModel>productNewModels;
    GridLayoutManager gridLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        productdescription=findViewById(R.id.productdescription);



       /* gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        productdescription.setLayoutManager(gridLayoutManager);
        productDescriptionModels=new ArrayList<>();
        productDescriptionModels.add(new ProductDescriptionModel("Fresh Apple","https://4.imimg.com/data4/VU/YD/ANDROID-28787331/product-500x500.jpeg","75.00"));
        productDescriptionModels.add(new ProductDescriptionModel("Fresh Apple","https://4.imimg.com/data4/VU/YD/ANDROID-28787331/product-500x500.jpeg","75.00"));
        productDescriptionModels.add(new ProductDescriptionModel("Fresh Apple","https://4.imimg.com/data4/VU/YD/ANDROID-28787331/product-500x500.jpeg","75.00"));
        productDescriptionModels.add(new ProductDescriptionModel("Fresh Apple","https://4.imimg.com/data4/VU/YD/ANDROID-28787331/product-500x500.jpeg","75.00"));

         productDescriptionAdapter=new ProductDescriptionAdapter(getApplicationContext(),productDescriptionModels);
         productdescription.setAdapter(productDescriptionAdapter);*/
        productdescription=findViewById(R.id.productdescription);
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        productdescription.setLayoutManager(gridLayoutManager);
        productNewModels=new ArrayList<>();
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"ASZXVGF","ASDFGDS","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"ASSA","","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"SASAS","","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"","","345"));
        productNewModels.add(new ProductNewModel(R.drawable.dammy,"","","345"));
        productNewAdapter=new ProductNewAdapter(getApplicationContext(),productNewModels);
        productdescription.setAdapter(productNewAdapter);



    }
    @Override
    public void onClick(View view, int position) {
        Toast.makeText(this, "haiii", Toast.LENGTH_SHORT).show();
    }


}