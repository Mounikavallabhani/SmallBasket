package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class RegistrationActivity extends AppCompatActivity {
    EditText Registeration_email,registration_password,registration_mobile;
    Button registration_button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Registeration_email=findViewById(R.id.Registeration_email);
        registration_password=findViewById(R.id.registration_password);
        registration_button=findViewById(R.id.registration_button);
        registration_mobile=findViewById(R.id.registration_mobile);

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Registeration_email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(!email.matches(emailPattern))
                {
                    Registeration_email.requestFocus();
                    Registeration_email.setError("Enter mail id");
                }
                else if(registration_mobile.length()!=10)
                {
                    registration_mobile.requestFocus();
                    registration_mobile.setError("Enter mobile Number");
                }
                else if(registration_password.getText().toString().isEmpty())
                {
                    registration_password.requestFocus();
                    registration_password.setError("Enter password");
                }
                else
                {
                    Registrationvalidation();

                }

            }
        });


    }


    protected void Registrationvalidation() {

        RequestQueue rq = Volley.newRequestQueue(RegistrationActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://www.smallbasket.store/api/registration",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            // Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            String message=jsonObject1.getString("message");
                            System.out.print("message"+message);
                            // Toast.makeText(Registration.this,""+message,Toast.LENGTH_LONG).show();
                            if(message.equals("Email Already Exist")){
                                Registeration_email.requestFocus();
                                Registeration_email.setError("This Mail alredy Exist");
                            }else if(message.equals("Mobile Number Already Exist")){
                                registration_mobile.requestFocus();
                                registration_mobile.setError("This Phone Number already Exist");
                            }else if(message.equals("OTP Sent To Your Mobile Number")){
                                Intent intent = new Intent(RegistrationActivity.this, OtpVerificationActivity.class);
                                sharedPreferences=getSharedPreferences("register",Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();
                                String rnum=registration_mobile.getText().toString();
                                editor.putString("regnum",rnum);
                                editor.apply();
                                editor.commit();
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

                params.put("email",Registeration_email.getText().toString());
                params.put("mobile",registration_mobile.getText().toString());
                params.put("password",registration_password.getText().toString());



                return params;
            }
        };
        rq.add(stringRequest);
    }
}
