package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.Activity.ProductDetails;
import com.arkainfoteck.smallbasket.Activity.ShoppingCart;
import com.arkainfoteck.smallbasket.DataBase.CartDatabse;
import com.arkainfoteck.smallbasket.model.ProductDescriptionModel;
import com.arkainfoteck.smallbasket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDescriptionAdapter extends RecyclerView.Adapter<ProductDescriptionAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductDescriptionModel>productDescriptionModels;
    View view;
    CartDatabse cartDatabse;
    String Quantiy="1";

    public ProductDescriptionAdapter(Context context, ArrayList<ProductDescriptionModel> productDescriptionModels) {
        this.context = context;
        this.productDescriptionModels = productDescriptionModels;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productimage;
        TextView productname, productcost;
        Button Add;
        Context context;
        ArrayList<ProductDescriptionModel> productDescriptionModels;
        Spinner qundity;
        Typeface tfavv;

        public ViewHolder(@NonNull View itemView, final Context context, ArrayList<ProductDescriptionModel> productDescriptionModels) {
            super(itemView);
            this.context = context;
            productDescriptionModels = productDescriptionModels;
            productimage = itemView.findViewById(R.id.productimage);
            productname = itemView.findViewById(R.id.productname);
            productcost = itemView.findViewById(R.id.productprice);
            // qundity=itemView.findViewById(R.id.qundity);
            // tfavv = Typeface.createFromAsset(context.getAssets(),"fonts/opensansbold.ttf");
            //qundity.setTypeface(tfavv);
            cartDatabse=new CartDatabse(context);

            Add = itemView.findViewById(R.id.add);
        }
    }

    @NonNull
    @Override
    public ProductDescriptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.productdescription,viewGroup,false);
        return new ViewHolder(view,context,productDescriptionModels);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductDescriptionAdapter.ViewHolder viewHolder, int i) {
        final ProductDescriptionModel productDescriptionModel=productDescriptionModels.get(i);
        Picasso.with(context).load(productDescriptionModel.getProductimage()).into(viewHolder.productimage);
        viewHolder.productname.setText(productDescriptionModel.getProductname());
        viewHolder.productcost.setText(productDescriptionModel.getProductcost());
        viewHolder.productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ProductDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        viewHolder.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       //         boolean resultt=cartDatabse.insertdata(productDescriptionModel.getProductname(),productDescriptionModel.getProductimage(),Quantiy,productDescriptionModel.getProductcost());

             /*   if (resultt==true)
                {
                    Toast.makeText(context, "added successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,ShoppingCart.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else
                {
                    Toast.makeText(context, "not added ", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return productDescriptionModels.size();
    }


}
