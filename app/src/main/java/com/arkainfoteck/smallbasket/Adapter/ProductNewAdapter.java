package com.arkainfoteck.smallbasket.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.model.DataModel;
import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.Model;
import com.arkainfoteck.smallbasket.model.ProductNewModel;

import java.util.ArrayList;



public class ProductNewAdapter extends RecyclerView.Adapter<ProductNewAdapter.MyView> {
    Context context;
     ListView listView;
    ArrayList<DataModel> dataModels;
    private static CustomAdapter adapter;
    ArrayList<ProductNewModel>productNewModels;


    public ProductNewAdapter(Context context, ArrayList<ProductNewModel> models) {
        this.context = context;
        this.productNewModels = models;
    }

    @NonNull
    @Override
    public ProductNewAdapter.MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.productnewadapter,viewGroup,false);

        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductNewAdapter.MyView myView, int i) {
        final ProductNewModel model=productNewModels.get(i);
        myView.quntity.setText(model.getName());
        myView.quntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText( context,"ASHGSDBV",Toast.LENGTH_SHORT).show();

                final Dialog MyDialog = new Dialog(context);
                MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                MyDialog.setContentView(R.layout.customdialog);
                TextView textView=MyDialog.findViewById(R.id.text);
                textView.setEnabled(true);
                MyDialog.show();

               /* listView=(ListView)MyDialog.findViewById(R.id.list);

                dataModels= new ArrayList<>();

                dataModels.add(new DataModel(" 1 kg - "," Rs 22.50","RS 15"));
                dataModels.add(new DataModel(" 2 kg - "," Rs 44.50","RS 30"));
                dataModels.add(new DataModel(" 5 kg - "," Rs 111.50","RS 90"));
                dataModels.add(new DataModel(" 10 kg - "," Rs 222.50","RS 180"));

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

                  //      view.setBackgroundResource(R.drawable.border_shape);

                        DataModel dataModel=dataModels.get(position);
                        String selectedAnimal=dataModel.getName()+" "+dataModel.getPrice()+" "+dataModel.getAmount();
                        Toast.makeText(context, "Animal Selected : "+selectedAnimal, Toast.LENGTH_LONG).show();
                        myView.quntity.setText(""+selectedAnimal);

                        try {
                            Thread.sleep(500);

                            MyDialog.cancel();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
*/

         }
        });

    }

    @Override
    public int getItemCount() {
        return productNewModels.size();
    }


    public class MyView extends RecyclerView.ViewHolder {
        TextView quntity;
        public MyView(@NonNull View itemView) {
            super(itemView);
            quntity=(TextView)itemView.findViewById(R.id.quntity) ;
        }
    }
}
  /*  ListView listView;
    ArrayList<DataModel> dataModels;
    private static CustomAdapter adapter;
    public ProductNewAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.productnewadapter,viewGroup,false);

        return new MyClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductNewAdapter.MyView myView, int i) {

    }

    @Override
    public void onBindViewHolder(@NonNull final MyClass myClass, int i) {

        final Model model=models.get(i);
        myClass.quntity.setText(model.getName());
        myClass.quntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog MyDialog = new Dialog(context);
                MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                MyDialog.setContentView(R.layout.customdialog);


                listView=(ListView)MyDialog.findViewById(R.id.list);

                dataModels= new ArrayList<>();

                dataModels.add(new DataModel(" 1 kg - "," Rs 22.50","RS 15"));
                dataModels.add(new DataModel(" 2 kg - "," Rs 44.50","RS 30"));
                dataModels.add(new DataModel(" 5 kg - "," Rs 111.50","RS 90"));
                dataModels.add(new DataModel(" 10 kg - "," Rs 222.50","RS 180"));

                adapter= new CustomAdapter(dataModels,context);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
// TODO Auto-generated method stub
*//* Animation animation1 = new AlphaAnimation(3f, 7f);
animation1.setDuration(4000);
view.startAnimation(animation1);*//*

                        view.setBackgroundResource(R.drawable.border_shape);

                        DataModel dataModel=dataModels.get(position);
                        String selectedAnimal=dataModel.getName()+" "+dataModel.getPrice()+" "+dataModel.getAmount();
                        Toast.makeText(context, "Animal Selected : "+selectedAnimal, Toast.LENGTH_LONG).show();
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

// setupPersonList();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {
        TextView quntity;
        public MyClass(@NonNull View itemView) {
            super(itemView);
            quntity=(TextView)itemView.findViewById(R.id.quntity) ;
        }
    }*/

  /* private Context context;
    ArrayList<ProductNewModel>productNewModels;
    View view;
   AlertDialog.Builder alert;
    AlertDialog dialog;
    ArrayList<DataModel>dataModels;
    ListView listView;
  private static     CustomAdapter adapter;
    int count = 1;
    private ItemClickListener clickListener;



    public ProductNewAdapter(Context context, ArrayList<ProductNewModel> productNewModels) {
        this.context = context;
        this.productNewModels = productNewModels;

    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.productnewadapter,viewGroup,false);
        return new MyView(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView viewHolder, int i) {
        ProductNewModel productNewModel=productNewModels.get(i);
        viewHolder.product_image.setBackgroundResource(productNewModel.getImage());
        viewHolder.product_price.setText(productNewModel.getCost());
        viewHolder.quntity.setText("qagfhjsa");

        viewHolder.quntity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog MyDialog = new Dialog(context);
                    MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    MyDialog.setContentView(R.layout.customdialog);


                    listView=(ListView)MyDialog.findViewById(R.id.list);

                    dataModels= new ArrayList<>();

                    dataModels.add(new DataModel(" 1 kg - "," Rs 22.50","RS 15"));
                    dataModels.add(new DataModel(" 2 kg - "," Rs 44.50","RS 30"));
                    dataModels.add(new DataModel(" 5 kg - "," Rs 111.50","RS 90"));
                    dataModels.add(new DataModel(" 10 kg - "," Rs 222.50","RS 180"));

                    adapter= new CustomAdapter(dataModels,context);

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position,
                                                long id) {
// TODO Auto-generated method stub
*//* Animation animation1 = new AlphaAnimation(3f, 7f);
animation1.setDuration(4000);
view.startAnimation(animation1);*//*

                            view.setBackgroundResource(R.drawable.border_shape);

                            DataModel dataModel=dataModels.get(position);
                            String selectedAnimal=dataModel.getName()+" "+dataModel.getPrice()+" "+dataModel.getAmount();
                            Toast.makeText(context, "Animal Selected : "+selectedAnimal, Toast.LENGTH_LONG).show();
                            viewHolder.quntity.setText(""+selectedAnimal);


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

// setupPersonList();
        }

    @Override
    public int getItemCount() {
        return productNewModels.size();
    }



    public class MyView extends RecyclerView.ViewHolder   {
        ImageView product_image;
        TextView product_price,quntity,cancecost;
        LinearLayout quntitylayout,layoutincrement,addlayout;
        Button add;
        TextView increment,decrement,value;

        Context context;
        public MyView(@NonNull View itemView, final Context context) {
            super(itemView);

            this.context=context;
            product_price=itemView.findViewById(R.id.product_price);
            product_image=itemView.findViewById(R.id.product_image);
            quntity=itemView.findViewById(R.id.quntity);
            add=itemView.findViewById(R.id.add);
           quntitylayout=itemView.findViewById(R.id.quntitylayout);
            cancecost=itemView.findViewById(R.id.cancecost);
            layoutincrement=itemView.findViewById(R.id.layoutincrement);
            addlayout=itemView.findViewById(R.id.addlayout);
            increment=itemView.findViewById(R.id.increment);
            decrement=itemView.findViewById(R.id.decrement);
            value=itemView.findViewById(R.id.value);
            cancecost.setPaintFlags(cancecost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
          *//*  product_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ShoppingCart.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layoutincrement.setVisibility(View.VISIBLE);
                    addlayout.setVisibility(View.GONE);

                }
            });
            decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  System.out.println("value1234"+value.getText().toString());

                    if (count >1) {
                        count--;
                        value.setText(String.valueOf(count));
                    }
                    else {
                        layoutincrement.setVisibility(View.GONE);
                        addlayout.setVisibility(View.VISIBLE);

                    }

                }
            });
            increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    count++;
                    value.setText(String.valueOf(count));

                }
            });
            quntity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog MyDialog = new Dialog(context);
                    MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    MyDialog.setContentView(R.layout.customdialog);


                    listView=(ListView)MyDialog.findViewById(R.id.list);

                    dataModels= new ArrayList<>();

                    dataModels.add(new DataModel(" 1 kg - "," Rs 22.50","RS 15"));
                    dataModels.add(new DataModel(" 2 kg - "," Rs 44.50","RS 30"));
                    dataModels.add(new DataModel(" 5 kg - "," Rs 111.50","RS 90"));
                    dataModels.add(new DataModel(" 10 kg - "," Rs 222.50","RS 180"));

                    adapter= new CustomAdapter(dataModels,context);

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position,
                                                long id) {


                            view.setBackgroundResource(R.drawable.border_shape);

                            DataModel dataModel=dataModels.get(position);
                            String selectedAnimal=dataModel.getName()+" "+dataModel.getPrice()+" "+dataModel.getAmount();
                            Toast.makeText(context, "Animal Selected : "+selectedAnimal, Toast.LENGTH_LONG).show();
                            quntity.setText(""+selectedAnimal);

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
*//*






        }



    }
}
*/