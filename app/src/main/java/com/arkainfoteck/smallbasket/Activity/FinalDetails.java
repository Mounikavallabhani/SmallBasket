package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.smallbasket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FinalDetails extends AppCompatActivity {
    Button paynow;
    TextView offer_amount,gateway_charges,gst_price,delivery_charges,total_amount,product_total_price;
    SharedPreferences prefs;
    String Name,Email,Phone,City,State,Zip_code,Address,UserId;
    double total_amount_integer;
    String status,data,gst,charges,order_id,sub_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_details);
        product_total_price=(TextView)findViewById(R.id.product_total_price) ;
        paynow=findViewById(R.id.paynow);
        offer_amount=(TextView)findViewById(R.id.offer_amount);
        gateway_charges=(TextView)findViewById(R.id.gateway_charges);
        gst_price=(TextView)findViewById(R.id.gst_price);
        delivery_charges=(TextView)findViewById(R.id.delivery_charges);
        total_amount=(TextView)findViewById(R.id.total_amount);



        SharedPreferences prefs12 = getSharedPreferences("Shoppingcard", MODE_PRIVATE);
        gst = prefs12.getString("gst", null);
        charges = prefs12.getString("charges", null);
        order_id = prefs12.getString("order_id", null);
        sub_total=prefs12.getString("sub_total",null);

        product_total_price.setText(sub_total);
        offer_amount.setText("0.00");
        gateway_charges.setText("5.00");
        gst_price.setText(gst);
        delivery_charges.setText(charges);



        // total amount here
        totalChargescount();

        total_amount.setText(""+total_amount_integer);


        SharedPreferences prefs = getSharedPreferences("Logindetails", MODE_PRIVATE);
         UserId=prefs.getString("user_id",null);
         Name = prefs.getString("fname", null);
         Email = prefs.getString("email", null);
         Phone = prefs.getString("mobile", null);
         City = prefs.getString("city", null);
         State = prefs.getString("state", null);
         Zip_code = prefs.getString("pincode", null);
         Address = prefs.getString("Address", null);

        System.out.println("order_id_final"+order_id+"User_id"+UserId);



        Toast.makeText(FinalDetails.this,"one"+Name+" ,"+Email+", "+Phone+","+City+","+State+","+Zip_code+","+ Address,Toast.LENGTH_SHORT).show();


        System.out.println("Name "+Name+"Email "+Email+"Phone "+ Phone+"City  "+City+"State "+State+"Zip Code "+Zip_code);

        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDetailsData();
             /*   */
            }
        });


    }
    public  void  totalChargescount(){
        total_amount_integer=Double.parseDouble(""+Double.parseDouble(offer_amount.getText().toString()))+Double.parseDouble(""+Double.parseDouble(gst_price.getText().toString()))+Double.parseDouble(""+Double.parseDouble(gateway_charges.getText().toString()))+Double.parseDouble(""+Double.parseDouble(delivery_charges.getText().toString()))+Double.parseDouble(""+Double.parseDouble(product_total_price.getText().toString()));
       //         +Integer.parseInt(""+Integer.parseInt(offer_amount.getText().toString()))+Integer.parseInt(""+Integer.parseInt(gateway_charges.getText().toString())+Integer.parseInt(""+Integer.parseInt(gst_price.getText().toString())+Integer.parseInt(""+Integer.parseInt(delivery_charges.getText().toString()))));product_total_price
      System.out.println("get_finding_data"+total_amount_integer);
    }
    public void getDetailsData(){

            RequestQueue rq = Volley.newRequestQueue(FinalDetails.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    "http://smallbasket.store/api/finalsubmitorder",
                    new Response.Listener<String>() {

                        public void onResponse(String response) {
                         //   {"status":200,"data":"Order Placed Successfully."}
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String data=jsonObject.getString("data");
                                if(data.equals("Order Placed Successfully.")){
                                    Intent intent=new Intent(getApplicationContext(),SuccessActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(FinalDetails.this,"Server Problem",Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println("response_data_finding"+response);

                            try {
                            } catch (Exception e) {
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


                    params.put("user_id",""+UserId);
                    params.put("order_id",""+order_id);
                    params.put("phone_number",""+Phone);
                    params.put("fname",""+Name);
                    params.put("lname","");
                    params.put("mobile",""+Phone);
                    params.put("email",""+Email);
                    params.put("postcode",""+Zip_code);
                    params.put("city",""+City);
                    params.put("state",""+State);
                    params.put("country","India");
                    params.put("address",""+Address);
                    params.put("subtotal",""+product_total_price.getText().toString());
                    params.put("gst_percent",""+gst_price.getText().toString());
                    params.put("gateway_charges",""+gateway_charges.getText().toString());
                    params.put("discount",""+offer_amount.getText().toString());
                    params.put("delivery_charges",""+delivery_charges.getText().toString());
                    params.put("order_total",""+total_amount.getText().toString());
                    params.put("razor_id","narasimha");
                    params.put("status","");
                    params.put("payment_method","");

                    return params;
                }
            };
            rq.add(stringRequest);

    }
}
