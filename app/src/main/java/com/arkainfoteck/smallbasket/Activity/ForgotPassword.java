package com.arkainfoteck.smallbasket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ForgotPassword extends AppCompatActivity {
    Button forgetpassword;
    EditText forget_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgetpassword=findViewById(R.id.forgetpassword);
        forget_mobile=findViewById(R.id.forget_mobile);
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Forgetpassword();

            }
        });
    }
    protected void Forgetpassword() {

        RequestQueue rq = Volley.newRequestQueue(ForgotPassword.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://www.smallbasket.store/api/forget_password",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            // Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            String message=jsonObject1.getString("message");
                            System.out.print("message"+message);
                            // Toast.makeText(Registration.this,""+message,Toast.LENGTH_LONG).show();
                           if(message.equals("Mobile number not exist")){
                                Toast.makeText(ForgotPassword.this, "Mobile number not exist", Toast.LENGTH_SHORT).show();

                            }
                            else  if(message.equals("Password Sent To Your Mobile Number")){
                                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
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

                params.put("mobile",forget_mobile.getText().toString());
                return params;
            }
        };
        rq.add(stringRequest);
    }
}
