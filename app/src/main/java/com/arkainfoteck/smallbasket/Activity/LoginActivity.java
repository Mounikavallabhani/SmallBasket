package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    TextView register,forgot;
    Button login;
    EditText Login_email,login_password;
    CheckBox login_checkbox;
    String user_id,profile_name,fname,lname,email,mobile,mobile_verify,user_activation,company,postcode,area,city,district,state,country,address,coupon_id,used_number,total_number,
            password,cpassword,otp;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register=(TextView)findViewById(R.id.register);
        forgot=(TextView)findViewById(R.id.forgot);
        login=(Button) findViewById(R.id.login_button);
        Login_email=findViewById(R.id.Login_email);
        login_password=findViewById(R.id.login_password);
        login_checkbox=findViewById(R.id.login_checkbox);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Login_email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if(login_password.getText().toString().isEmpty()){
                    login_password.requestFocus();
                    login_password.setError("Enter Password ");
                }
                if (email.matches(emailPattern)||email.length()==10) {
                    Login_email.clearFocus();
                    login_password.clearFocus();
                    LoginValidation();

                }else {
                    Login_email.requestFocus();
                    Login_email.setError("Enter Proper details ");

                }

               
            }
        });
        sharedPreferences = getSharedPreferences("logindetails", Context.MODE_PRIVATE);

        String uname = sharedPreferences.getString("password", null);
        if (uname != null) {
            Intent intent = new Intent(LoginActivity.this, DashBoard.class);
            startActivity(intent);
        }
    }
    protected void LoginValidation() {

        RequestQueue rq = Volley.newRequestQueue(LoginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://smallbasket.store/api/login",
                new Response.Listener<String>() {

                    public void onResponse(String response) {


                        try {
                            // Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            String message=jsonObject1.getString("message");
                            System.out.print("message"+message);
                            // Toast.makeText(Registration.this,""+message,Toast.LENGTH_LONG).show();
                            String data=jsonObject1.getString("data");
                            JSONArray jsonArray=new JSONArray(data);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                user_id=jsonObject.getString("user_id");
                                profile_name=jsonObject.getString("profile_name");
                                fname=jsonObject.getString("fname");
                                lname=jsonObject.getString("lname");
                                email=jsonObject.getString("email");
                                mobile=jsonObject.getString("mobile");
                                mobile_verify=jsonObject.getString("mobile_verify");
                                user_activation=jsonObject.getString("user_activation");
                                company=jsonObject.getString("company");
                                postcode=jsonObject.getString("postcode");
                                area=jsonObject.getString("area");
                                city=jsonObject.getString("city");
                                district=jsonObject.getString("district");
                                state=jsonObject.getString("state");
                                country=jsonObject.getString("country");
                                address=jsonObject.getString("address");
                                coupon_id=jsonObject.getString("coupon_id");
                                used_number=jsonObject.getString("used_number");
                                total_number=jsonObject.getString("total_number");
                                password=jsonObject.getString("password");
                                cpassword=jsonObject.getString("cpassword");
                                otp=jsonObject.getString("otp");

                                sharedPreferences=getSharedPreferences("Logindetails",Context.MODE_PRIVATE);
                                editor=sharedPreferences.edit();
                                editor.putString("user_id",user_id);
                                editor.putString("fname",fname);
                                editor.putString("lname",lname);
                                editor.putString("email",email);
                                editor.putString("mobile",mobile);
                                editor.putString("city",city);
                                editor.putString("state",state);
                                editor.putString("pincode",postcode);
                                editor.putString("address",address);

                                editor.putString("password",password);
                                editor.commit();
                                editor.apply();








                            }
                            if(message.equals("User Not Valid")){
                                Toast.makeText(LoginActivity.this, "Enter Proper Details", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent=new Intent(getApplicationContext(),CatagiresType.class);
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

                params.put("email",Login_email.getText().toString());
                params.put("password",login_password.getText().toString());

                return params;
            }
        };
        rq.add(stringRequest);
    }
}


