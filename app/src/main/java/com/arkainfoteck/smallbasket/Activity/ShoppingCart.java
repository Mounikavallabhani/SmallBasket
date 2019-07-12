package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.smallbasket.Adapter.ShoppingCartAdapter;
import com.arkainfoteck.smallbasket.Adapter.ShoppingCartNewAdapter;
import com.arkainfoteck.smallbasket.DataBase.CartDatabse;
import com.arkainfoteck.smallbasket.model.ModelClassFeilds;
import com.arkainfoteck.smallbasket.model.ShoppingCartModel;
import com.arkainfoteck.smallbasket.model.ShoppingCartNewModel;
import com.arkainfoteck.smallbasket.R;
import com.google.android.gms.common.SignInButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart extends AppCompatActivity {
RecyclerView shoppingcart;
ShoppingCartAdapter shoppingCartAdapter;
ArrayList<ShoppingCartModel>shoppingCartModels;
LinearLayoutManager gridLayoutManager;
Button continuebutton;
CartDatabse cartDatabse;
TextView selected_items_list;
ShoppingCartNewAdapter shoppingCartNewAdapter;
TextView finalamount;
int GET_TOTAL_AMOUNT_HERE;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
String delivartype;
    List<ModelClassFeilds>list;
    private Handler handler;
    private Runnable handlerTask;
    List<ShoppingCartModel>shoppingCartModels12;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        shoppingcart=findViewById(R.id.shoppingcart);
        finalamount=findViewById(R.id.finalamount);

        continuebutton=findViewById(R.id.continuebutton);
        selected_items_list=findViewById(R.id.selected_items_list);
        cartDatabse=new CartDatabse(this);
         //  get
        SharedPreferences prefs = getSharedPreferences("Logindetails", MODE_PRIVATE);
        user_id=prefs.getString("UserId",null);


        gridLayoutManager=new LinearLayoutManager(getApplicationContext());
        shoppingcart.setLayoutManager(gridLayoutManager);
        shoppingCartModels=new ArrayList<>();
        Cursor res=cartDatabse.getdata();


        if (res.getCount()==0)
        {

            Toast.makeText(this, "cart is empty", Toast.LENGTH_SHORT).show();
        }
        while (res.moveToNext())
        {

       //     shoppingCartModels.add(new ShoppingCartModel(res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)));
           shoppingCartModels.add(new ShoppingCartModel(""+res.getString(0),""+res.getString(1),""+res.getString(2),""+res.getString(3),""+res.getString(4),""+res.getString(5),""+res.getString(6),""+res.getString(7),""+res.getString(8),""+res.getString(9)));

        }
     //   shoppingCartNewModels.add(shoppingCartNewModels);
     ////   shoppingCartNewModels.add(new ShoppingCartNewModel(R.drawable.dammy));
     //   shoppingCartNewModels.add(new ShoppingCartNewModel(R.drawable.dammy));
        selected_items_list.setText("you have "+res.getCount()+" items in your card");
        shoppingCartNewAdapter=new ShoppingCartNewAdapter(getApplicationContext(),shoppingCartModels,cartDatabse);
        shoppingcart.setAdapter(shoppingCartNewAdapter);
        StartTimer();

        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               getCompleteData();

            /*    Intent intent=new Intent(getApplicationContext(),ShippingActivity.class);
                startActivity(intent);*/
            }
        });


         // find the count value means total price amount here
                GET_TOTAL_AMOUNT_HERE=cartDatabse.getFinalCouunt();
                finalamount.setText(""+GET_TOTAL_AMOUNT_HERE+".00");

    }
    public  void getCompleteData(){

        shoppingCartModels=new ArrayList<>();
        Cursor res=cartDatabse.getdata();

        if (res.getCount()==0)
        {

            Toast.makeText(this, "cart is empty", Toast.LENGTH_SHORT).show();
        }
        while (res.moveToNext())
        {

            shoppingCartModels.add(new ShoppingCartModel(res.getString(0),res.getString(1),res.getString(2),res.getString(5),res.getString(9),res.getString(3)));
            System.out.println("finding_orders12345"+res.getString(0)+", "+res.getString(1)+" ,"+res.getString(2)+" ,"+res.getString(3)+"  ,"+res.getString(4)+"  ,"+res.getString(5)+"  ,"+res.getString(6)+" ,"+res.getString(7)+" ,"+res.getString(8)+" ,"+res.getString(9));

        }
        Gson gson=new Gson();
        final String newDataArray=gson.toJson(shoppingCartModels);

        RequestQueue rq = Volley.newRequestQueue(ShoppingCart.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://smallbasket.store/api/submitorder",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject1=new JSONObject(response);
                            String status=jsonObject1.getString("status");
                            String data=jsonObject1.getString("data");
                            if(data.equals("Order Placed Sucessfully.")){

                                String gst=jsonObject1.getString("gst");
                                String charges=jsonObject1.getString("charges");
                                String order_id=jsonObject1.getString("order id");
                                String sub_total1=jsonObject1.getString("sub total");

                                sharedPreferences=getSharedPreferences("Shoppingcard",Context.MODE_PRIVATE);
                                editor=sharedPreferences.edit();
                                editor.putString("status",status);
                                editor.putString("data",data);
                                editor.putString("gst",gst);
                                editor.putString("charges",charges);
                                editor.putString("order_id",order_id);
                                editor.putString("sub_total",sub_total1);

                                editor.commit();
                                editor.apply();

                            }else {
                                Toast.makeText(ShoppingCart.this,"Server Problem",Toast.LENGTH_LONG).show();
                            }
                             System.out.println("response_data"+response);


                            Intent intent=new Intent(getApplicationContext(),ShippingActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    };
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                //   pd.hide();
            }
        })

        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id","27");
                params.put("subtotal",""+finalamount.getText().toString());
                params.put("JSON",newDataArray);


                return params;
            }
        };
        rq.add(stringRequest);
    }
    void StartTimer(){
        handler = new Handler();
        handlerTask = new Runnable()
        {
            @Override
            public void run() {
                // do something

                int selected_count_points = cartDatabse.getFinalCouunt();
                //     Toast.makeText(MainActivity.this,""+Integer.toString(selected_count_points),Toast.LENGTH_SHORT).show();
                finalamount.setText(""+Integer.toString(selected_count_points));

                handler.postDelayed(handlerTask, 2000);
            }
        };
        handlerTask.run();
    }
}
