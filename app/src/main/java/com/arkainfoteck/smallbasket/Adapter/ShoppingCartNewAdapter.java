package com.arkainfoteck.smallbasket.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.Activity.Model;
import com.arkainfoteck.smallbasket.DataBase.CartDatabse;
import com.arkainfoteck.smallbasket.model.ShoppingCartModel;
import com.arkainfoteck.smallbasket.model.ShoppingCartNewModel;
import com.arkainfoteck.smallbasket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoppingCartNewAdapter extends RecyclerView.Adapter<ShoppingCartNewAdapter.ViewHolder> {
    Context context;
    ArrayList<ShoppingCartModel>shoppingCartNewModels;
    CartDatabse cartDatabse;
    View view;

    public ShoppingCartNewAdapter(Context context, ArrayList<ShoppingCartModel> shoppingCartNewModels,CartDatabse cartDatabse) {
        this.context = context;
        this.shoppingCartNewModels = shoppingCartNewModels;
        this.cartDatabse=cartDatabse;
    }

    @NonNull
    @Override
    public ShoppingCartNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.shoppingcartnewadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartNewAdapter.ViewHolder viewHolder, int i) {
        ShoppingCartModel shoppingCartNewModel=shoppingCartNewModels.get(i);
      //  viewHolder.cart_image.setBackgroundResource(shoppingCartNewModel.getImage());
        System.out.println("finding_orders"+shoppingCartNewModel.getPrice()+","+shoppingCartNewModel.getProduct_name()+","+shoppingCartNewModel.getPrice()+","+shoppingCartNewModel.getRestaurant_id()+","+shoppingCartNewModel.getImage()+","+shoppingCartNewModel.getQuantity());
     viewHolder.selected_product_title_name.setText(shoppingCartNewModel.getProduct_name());
     viewHolder.selected_product_count.setText(shoppingCartNewModel.getQuantity());
     viewHolder.cart_cancel_cost.setText(shoppingCartNewModel.getPrice());
     viewHolder.selected_product_price.setText(shoppingCartNewModel.getRs_cost());


        Picasso.with(context)

                .load("https://smallbasket.store/assets/img/products/"+shoppingCartNewModel.getImage())
                .into(viewHolder.cart_image);

    System.out.println("CartNewModel"+shoppingCartNewModel.getAprice12()+"Mrp_cost"+shoppingCartNewModel.getPrice()+"getCount"+shoppingCartNewModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return shoppingCartNewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cart_image;
        TextView selected_product_title_name,cart_cancel_cost,selected_product_price,selected_product_count,decrement,increment;
        LinearLayout addlayout,layoutincrement;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_cancel_cost=itemView.findViewById(R.id.cart_cancel_cost);
            cart_image=itemView.findViewById(R.id.cart_image);

            selected_product_title_name=itemView.findViewById(R.id.selected_product_title_name);
            selected_product_price=itemView.findViewById(R.id.selected_product_price);
            decrement=(TextView)itemView.findViewById(R.id.decrement);
            increment=(TextView)itemView.findViewById(R.id.increment);
            selected_product_count=(TextView)itemView.findViewById(R.id.selected_product_count);
            layoutincrement=(LinearLayout) itemView.findViewById(R.id.layoutincrement);
            addlayout=(LinearLayout)itemView.findViewById(R.id.addlayout);
            cart_cancel_cost.setPaintFlags(cart_cancel_cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int row=getAdapterPosition();
                    ShoppingCartModel model=shoppingCartNewModels.get(row);

                    int count=Integer.parseInt(cartDatabse.getCountvalue(model.getProduct_name(),model.getProduct_type_one(),model.getRestaurant_name()));
                    if (count >1) {

                        count--;

                        //   Toast.makeText(context,"row  "+model.getTitle_name()+"1"+model.getMrp_cost()+"1"+model.getRs_cost()+"1"+model.getImage()+"1"+model.getProduct_type_one()+"1"+model.getRestaurant_name()+"1"+model.getAprice12(),Toast.LENGTH_SHORT).show();
                        System.out.println("row  "+model.getProduct_name()+""+model.getPrice()+""+model.getRs_cost()+""+model.getImage()+""+model.getProduct_type_one()+""+model.getRestaurant_name()+""+model.getAprice12());
                        boolean resultt = cartDatabse.UpdateQuery(""+model.getProduct_id(),""+model.getRestaurant_id(),""+model.getProduct_name(), model.getPrice(),model.getRs_cost(),model.getImage(),model.getProduct_type_one(),model.getRestaurant_name(),model.getAprice12(), String.valueOf(count));

                        if (resultt == true) {
                            Toast.makeText(context, "added successfull", Toast.LENGTH_SHORT).show();
                            //           StartTimer();
                        } else {
                            Toast.makeText(context, "not added ", Toast.LENGTH_SHORT).show();
                        }
                        selected_product_count.setText(String.valueOf(count));
                    }
                    else {
                        addlayout.setVisibility(View.VISIBLE);
                        layoutincrement.setVisibility(View.GONE);
                        //      Toast.makeText(context,"row  "+model.getTitle_name()+"1"+model.getMrp_cost()+"1"+model.getRs_cost()+"1"+model.getImage()+"1"+model.getProduct_type_one()+"1"+model.getRestaurant_name()+"1"+model.getAprice12(),Toast.LENGTH_SHORT).show();

                        // Remove the item on remove/button click
                        shoppingCartNewModels.remove(row);
                        notifyItemRemoved(row);

                /*
                    public final void notifyItemRemoved (int position)
                        Notify any registered observers that the item previously located at position
                        has been removed from the data set. The items previously located at and
                        after position may now be found at oldPosition - 1.

                        This is a structural change event. Representations of other existing items
                        in the data set are still considered up to date and will not be rebound,
                        though their positions may be altered.

                    Parameters
                        position : Position of the item that has now been removed
                */
                   //     notifyItemRemoved(position);


                        int result = cartDatabse.deleteData(model.getProduct_name(),model.getProduct_type_one(),model.getRestaurant_name());
                        if (result >=0) {
                            Toast.makeText(context, "changed ", Toast.LENGTH_SHORT).show();
                            //           StartTimer();
                        } else {
                            Toast.makeText(context, "not changes ", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int row=getAdapterPosition();
                    ShoppingCartModel model=shoppingCartNewModels.get(row);

                    int count=Integer.parseInt(cartDatabse.getCountvalue(model.getProduct_name(),model.getProduct_type_one(),model.getRestaurant_name()));

                    count++;
                    selected_product_count.setText(String.valueOf(count));


                    //      Toast.makeText(context,"row  "+model.getTitle_name()+"1"+model.getMrp_cost()+"1"+model.getRs_cost()+"1"+model.getImage()+"1"+model.getProduct_type_one()+"1"+model.getRestaurant_name()+"1"+model.getAprice12(),Toast.LENGTH_SHORT).show();
                    System.out.println("row  "+model.getProduct_name()+""+model.getPrice()+""+model.getRs_cost()+""+model.getImage()+""+model.getProduct_type_one()+""+model.getRestaurant_name()+""+model.getAprice12());
                    boolean resultt = cartDatabse.UpdateQuery(""+model.getProduct_id(),""+model.getRestaurant_id(),""+model.getProduct_name(), model.getPrice(),model.getRs_cost(),model.getImage(),model.getProduct_type_one(),model.getRestaurant_name(),model.getAprice12(), String.valueOf(count));

                    if (resultt == true) {
                        Toast.makeText(context, "added successfull", Toast.LENGTH_SHORT).show();
                        //           StartTimer();
                    } else {
                        Toast.makeText(context, "not added ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
