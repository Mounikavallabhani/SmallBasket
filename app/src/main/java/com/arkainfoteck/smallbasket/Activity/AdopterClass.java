package com.arkainfoteck.smallbasket.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.DataBase.CartDatabse;
import com.arkainfoteck.smallbasket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdopterClass extends RecyclerView.Adapter<AdopterClass.MyClass> {

    Context context;
    ArrayList<Model>models;
    ListView listView;
    ArrayList<DataModel> dataModels;
    int count=1;
    private static CustomAdapter adapter;
    CartDatabse cartDatabse;
    private Handler handler;
    private Runnable handlerTask;
    public AdopterClass(Context context, ArrayList<Model> models,CartDatabse cartDatabse) {
        this.context = context;
        this.models = models;
        this.cartDatabse=cartDatabse;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.list_items,viewGroup,false);

        return new MyClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyClass myClass, int i) {

        final Model model=models.get(i);
        myClass.quntity.setText( " 1 kg - Rs 22.50 ");
        myClass.title_name.setText(model.getTitle_name());
        myClass.cancecost.setText(model.getMrp_cost());
        myClass.product_price.setText(model.getRs_cost());

        System.out.println("getImage"+model.getImage());
                Picasso.with(context)

                .load("https://smallbasket.store/assets/img/products/"+model.getImage())
                .into(myClass.product_image);
        myClass.quntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog MyDialog = new Dialog(context);
                MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                MyDialog.setContentView(R.layout.customdialog);

                listView=(ListView)MyDialog.findViewById(R.id.list);

                dataModels= new ArrayList<>();

                dataModels.add(new DataModel(" 1 kg - "," Rs 22.50"));
                dataModels.add(new DataModel(" 2 kg - "," Rs 44.50"));
                dataModels.add(new DataModel(" 5 kg - "," Rs 111.50"));
                dataModels.add(new DataModel(" 10 kg - "," Rs 222.50"));

                adapter= new CustomAdapter(dataModels,context);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        // TODO Auto-generated method stub
                        Animation animation1 = new AlphaAnimation(3f, 7f);
                        animation1.setDuration(4000);
                        view.startAnimation(animation1);

                        view.setBackgroundResource(R.drawable.border_shape);

                        DataModel dataModel=dataModels.get(position);
                        String selectedAnimal=dataModel.getName()+"  "+dataModel.getPrice();
                        Toast.makeText(context, "Animal Selected : "+selectedAnimal,   Toast.LENGTH_LONG).show();
                        myClass.quntity.setText(""+selectedAnimal);

                        try {
                            Thread.sleep(500);

                            MyDialog.cancel();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                MyDialog.show();
        }
        });
    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {
        TextView quntity,title_name,product_price;
        ImageView product_image;
        TextView add,decrement,value,increment,cancecost;
        LinearLayout layoutincrement,addlayout;
        public MyClass(@NonNull View itemView) {
            super(itemView);
            quntity=(TextView)itemView.findViewById(R.id.quntity) ;
            product_image=itemView.findViewById(R.id.product_image);
            add=itemView.findViewById(R.id.add);
            layoutincrement=itemView.findViewById(R.id.layoutincrement);
            addlayout=itemView.findViewById(R.id.addlayout);
            decrement=itemView.findViewById(R.id.decrement);
            value=itemView.findViewById(R.id.value);
            increment=itemView.findViewById(R.id.increment);
            cancecost=itemView.findViewById(R.id.cancecost);
            title_name=itemView.findViewById(R.id.title_name);
            product_price=itemView.findViewById(R.id.product_price);


            product_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /*   Intent intent=new Intent(context, ShoppingCart.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layoutincrement.setVisibility(View.VISIBLE);
                    addlayout.setVisibility(View.GONE);

                    int row = getAdapterPosition();
                    Model model = models.get(row);


                    boolean Isrow = cartDatabse.CheckIsDataAlreadyInDBorNot(model.getTitle_name(), model.getProduct_type_one(), model.getRestaurant_name());

                    if (Isrow) {
                        Toast.makeText(context, "True", Toast.LENGTH_SHORT).show();

                    } else {
                        //  Toast.makeText(context,"row  "+model.getTitle_name()+"1"+model.getMrp_cost()+"1"+model.getRs_cost()+"1"+model.getImage()+"1"+model.getProduct_type_one()+"1"+model.getRestaurant_name()+"1"+model.getAprice12(),Toast.LENGTH_SHORT).show();
                        System.out.println("row  " + model.getTitle_name() + "" + model.getMrp_cost() + "" + model.getRs_cost() + "" + model.getImage() + "" + model.getProduct_type_one() + "" + model.getRestaurant_name() + "" + model.getAprice12());
                        boolean resultt = cartDatabse.insertdata(""+model.getId(),""+model.getR_id(),"" + model.getTitle_name(), model.getMrp_cost(), model.getRs_cost(), model.getImage(), model.getProduct_type_one(), model.getRestaurant_name(), model.getAprice12(), String.valueOf(count));

                        if (resultt == true) {
                            Toast.makeText(context, "added successfull", Toast.LENGTH_SHORT).show();
                            //           StartTimer();
                        } else {
                            Toast.makeText(context, "not added ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("value1234"+value.getText().toString());
                    int row=getAdapterPosition();
                    Model model=models.get(row);

                   int count=Integer.parseInt(cartDatabse.getCountvalue(model.getTitle_name(),model.getProduct_type_one(),model.getRestaurant_name()));
                    if (count >1) {

                        count--;

                     //   Toast.makeText(context,"row  "+model.getTitle_name()+"1"+model.getMrp_cost()+"1"+model.getRs_cost()+"1"+model.getImage()+"1"+model.getProduct_type_one()+"1"+model.getRestaurant_name()+"1"+model.getAprice12(),Toast.LENGTH_SHORT).show();
                        System.out.println("row  "+model.getTitle_name()+""+model.getMrp_cost()+""+model.getRs_cost()+""+model.getImage()+""+model.getProduct_type_one()+""+model.getRestaurant_name()+""+model.getAprice12());
                        boolean resultt = cartDatabse.UpdateQuery(""+model.getId(),""+model.getR_id(),""+model.getTitle_name(), model.getMrp_cost(),model.getRs_cost(),model.getImage(),model.getProduct_type_one(),model.getRestaurant_name(),model.getAprice12(), String.valueOf(count));

                        if (resultt == true) {
                            Toast.makeText(context, "added successfull", Toast.LENGTH_SHORT).show();
                            //           StartTimer();
                        } else {
                            Toast.makeText(context, "not added ", Toast.LENGTH_SHORT).show();
                        }
                        value.setText(String.valueOf(count));
                    }
                    else {
                        layoutincrement.setVisibility(View.GONE);
                        addlayout.setVisibility(View.VISIBLE);

                  //      Toast.makeText(context,"row  "+model.getTitle_name()+"1"+model.getMrp_cost()+"1"+model.getRs_cost()+"1"+model.getImage()+"1"+model.getProduct_type_one()+"1"+model.getRestaurant_name()+"1"+model.getAprice12(),Toast.LENGTH_SHORT).show();

                        int result = cartDatabse.deleteData(model.getTitle_name(),model.getProduct_type_one(),model.getRestaurant_name());
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
                    Model model=models.get(row);

                    int count=Integer.parseInt(cartDatabse.getCountvalue(model.getTitle_name(),model.getProduct_type_one(),model.getRestaurant_name()));

                    count++;
                    value.setText(String.valueOf(count));


              //      Toast.makeText(context,"row  "+model.getTitle_name()+"1"+model.getMrp_cost()+"1"+model.getRs_cost()+"1"+model.getImage()+"1"+model.getProduct_type_one()+"1"+model.getRestaurant_name()+"1"+model.getAprice12(),Toast.LENGTH_SHORT).show();
                    System.out.println("row  "+model.getTitle_name()+""+model.getMrp_cost()+""+model.getRs_cost()+""+model.getImage()+""+model.getProduct_type_one()+""+model.getRestaurant_name()+""+model.getAprice12());
                    boolean resultt = cartDatabse.UpdateQuery(""+model.getId(),""+model.getR_id(),""+model.getTitle_name(), model.getMrp_cost(),model.getRs_cost(),model.getImage(),model.getProduct_type_one(),model.getRestaurant_name(),model.getAprice12(), String.valueOf(count));

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
