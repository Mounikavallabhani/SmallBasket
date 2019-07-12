package com.arkainfoteck.smallbasket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arkainfoteck.smallbasket.R;

public class SuccessActivity extends AppCompatActivity {
    Button backtoshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        backtoshop=findViewById(R.id.backtoshop);
        backtoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
            }
        });
    }
}
