package com.arkainfoteck.smallbasket.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.R;

public class ShippingActivity extends AppCompatActivity {
    Button secondcontinue;
    TextView shoipping_name,shoipping_email,shoipping_phone,shoipping_city,shoipping_state,shoipping_zipcode,shoipping_address;


    SharedPreferences pref; // 0 - for private mode
    SharedPreferences.Editor editor ;
    String Name,Email,Phone,City,State,Zip_code,Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        secondcontinue=findViewById(R.id.secondcontinue);
        shoipping_name=findViewById(R.id.shoipping_name);
        shoipping_email=findViewById(R.id.shoipping_email);
        shoipping_phone=findViewById(R.id.shoipping_phone);
        shoipping_city=findViewById(R.id.shoipping_city);
        shoipping_state=findViewById(R.id.shoipping_state);
        shoipping_zipcode=findViewById(R.id.shoipping_zipcode);
        shoipping_address=findViewById(R.id.shoipping_address);
        SharedPreferences prefs = getSharedPreferences("Logindetails", MODE_PRIVATE);


        Name = prefs.getString("fname", null);
        Email = prefs.getString("email", null);
        Phone = prefs.getString("mobile", null);
        City = prefs.getString("city", null);
        State = prefs.getString("state", null);
        Zip_code = prefs.getString("pincode", null);
        Address = prefs.getString("address", null);

        shoipping_name.setText(Name);
        shoipping_email.setText(Email);
        shoipping_phone.setText(Phone);
        shoipping_city.setText(City);
        shoipping_state.setText(State);
        shoipping_zipcode.setText(Zip_code);
        shoipping_address.setText(Address);
        secondcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shoipping_name.getText().toString().isEmpty())
                {
                    shoipping_name.requestFocus();
                    shoipping_name.setError("Provide Name");

                }
                else if(shoipping_email.getText().toString().isEmpty())
                {
                    shoipping_email.requestFocus();
                    shoipping_email.setError("Provide Email");

                }
                else if(shoipping_phone.getText().toString().isEmpty())
                {
                    shoipping_phone.requestFocus();
                    shoipping_phone.setError("Provide Phone Number");

                }
                else if(shoipping_city.getText().toString().isEmpty())
                {
                    shoipping_city.requestFocus();
                    shoipping_city.setError("Provide City");

                }
                else if(shoipping_state.getText().toString().isEmpty())
                {
                    shoipping_state.requestFocus();
                    shoipping_state.setError("Provide State");

                }
                else if(shoipping_zipcode.getText().toString().isEmpty())
                {
                    shoipping_zipcode.requestFocus();
                    shoipping_zipcode.setError("Provide Zipcode");

                }
                else if(shoipping_address.getText().toString().isEmpty())
                {
                    shoipping_address.requestFocus();
                    shoipping_address.setError("Provide Complete Address");


                }
                else {

                    pref=getSharedPreferences("Logindetails", Context.MODE_PRIVATE);
                    editor=pref.edit();

                    editor.putString("fname",""+shoipping_name.getText().toString());
                    editor.putString("email",""+shoipping_email.getText().toString());
                    editor.putString("mobile",""+shoipping_phone.getText().toString());
                    editor.putString("city",""+shoipping_city.getText().toString());
                    editor.putString("state",""+shoipping_state.getText().toString());
                    editor.putString("pincode",""+shoipping_zipcode.getText().toString());
                    editor.putString("address",""+shoipping_address.getText().toString());
                    editor.apply();
                    editor.commit();



                    Intent intent = new Intent(getApplicationContext(), FinalDetails.class);
                    startActivity(intent);
                }
            }
        });

    }
}
