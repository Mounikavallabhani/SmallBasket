package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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

public class LocationActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView fname,lname,phone_number,house_no,apartment_no,street_no,landmark_no,area_details,city,pincode;
    String UserId;
    Button save_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        fname=(TextView)findViewById(R.id.fname);
        lname=(TextView)findViewById(R.id.lname);
        phone_number=(TextView)findViewById(R.id.phone_number);
        house_no=(TextView)findViewById(R.id.house_no);
        apartment_no=(TextView)findViewById(R.id.apartment_no);
        street_no=(TextView)findViewById(R.id.street_no);
        landmark_no=(TextView)findViewById(R.id.landmark_no);
        area_details=(TextView)findViewById(R.id.area_details);
        city=(TextView)findViewById(R.id.city);
        pincode=(TextView)findViewById(R.id.pincode);
        save_address=(Button)findViewById(R.id.save_address);

        toolbar=(Toolbar)findViewById(R.id.t1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Address Details");

        SharedPreferences prefs = getSharedPreferences("Logindetails", MODE_PRIVATE);
        UserId=prefs.getString("user_id",null);

        save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddressDetails();
            }
        });

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
    public void getAddressDetails(){


            RequestQueue rq = Volley.newRequestQueue(LocationActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    "http://smallbasket.store/api/addressbook",
                    new Response.Listener<String>() {

                        public void onResponse(String response) {


                            try {
                                System.out.println("response"+response);
                                Toast.makeText(LocationActivity.this,"Narasimha  "+response,Toast.LENGTH_LONG).show();
                                JSONObject jsonObject1=new JSONObject(response);

                                String data=jsonObject1.getString("data");
                                        if(data.equals("Address Updated successfully.")){
                                            Intent intent=new Intent(LocationActivity.this,DashBoard.class);
                                            startActivity(intent);
                                        }else {

                                        }
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

                    params.put("user_id",UserId);
                    params.put("fname",fname.getText().toString());
                    params.put("lname",lname.getText().toString());
                    params.put("contactno",phone_number.getText().toString());
                    params.put("houseno",house_no.getText().toString());
                    params.put("apartment",apartment_no.getText().toString());
                    params.put("street",street_no.getText().toString());
                    params.put("landmark",landmark_no.getText().toString());
                    params.put("area",area_details.getText().toString());
                    params.put("city",city.getText().toString());
                    params.put("pincode",pincode.getText().toString());
                    params.put("home","");
                    return params;
                }
            };
            rq.add(stringRequest);
        }

}
