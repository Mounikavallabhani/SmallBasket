package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.smallbasket.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpVerificationActivity extends AppCompatActivity {
    ImageView otpsubmit;
    EditText et1,et2,et3,et4,et5,et6;
    String o1,o2,o3,o4,o5,o6,otp;
    SharedPreferences sharedPreferences;
    String reg_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        otpsubmit=findViewById(R.id.otpsubmit);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);
        sharedPreferences=getSharedPreferences("register", Context.MODE_PRIVATE);
        reg_num=sharedPreferences.getString("regnum",null);


        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et1.getText().toString().length() == 1)     //size as per your requirement
                {
                    o1=et1.getText().toString();
                    et2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et2.getText().toString().length() == 1)     //size as per your requirement
                {
                    o2=et2.getText().toString();
                    et3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et3.getText().toString().length() == 1)     //size as per your requirement
                {
                    o3=et3.getText().toString();
                    et4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et4.getText().toString().length() == 1)     //size as per your requirement
                {
                    o4=et4.getText().toString();

                    et5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et5.getText().toString().length() == 1)     //size as per your requirement
                {
                    o5=et5.getText().toString();
                    et6.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et6.getText().toString().length() == 1)     //size as per your requirement
                {
                    o6=et6.getText().toString();
                    //et5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(getApplicationContext(),CatagiresType.class);
                startActivity(intent);*/
                otp=o1+o2+o3+o4+o5+o6;
                LoginValidationforotp();
            }
        });

    }

    public void  LoginValidationforotp(){
        RequestQueue rq = Volley.newRequestQueue(OtpVerificationActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://www.smallbasket.store/api/checkOtp",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String recevice= jsonObject.getString("message");
                            if(recevice.equals("Valid Otp.")){
                                Intent intent=new Intent(OtpVerificationActivity.this,CatagiresType.class);
                                startActivity(intent);
                            }else if(recevice.equals("Not valid OTP")){
                                Toast.makeText(OtpVerificationActivity.this,"OTP Is Wrong",Toast.LENGTH_LONG).show();
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

                params.put(" mobile",reg_num);
                params.put("otp",otp);


                return params;
            }
        };
        rq.add(stringRequest);
    }

}
