package com.arkainfoteck.smallbasket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.R;

public class RatingBarActivity extends AppCompatActivity {
    RatingBar restarentratting,deliveryboyratting;
    TextView content,fourcontent,content1,fourcontent1;
    Float ratingNumber;
    int intratting;
    LinearLayout linerlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rattindscreen);
        fourcontent=findViewById(R.id.fourcontent);
        linerlayout=findViewById(R.id.linerlayout);
        deliveryboyratting=findViewById(R.id.deliveryboyratting);
       restarentratting=(RatingBar) findViewById(R.id.restarentratting);
        content=findViewById(R.id.content);
        restarentratting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ratingNumber = restarentratting.getRating();
                intratting=Math.round(ratingNumber);

                //Toast.makeText(RatingBarActivity.this, ""+ratingNumber, Toast.LENGTH_SHORT).show();
                System.out.println("ratingNumber"+intratting);
                if(intratting==3)
                {
                    content.setText("WHAT WE CAN IMPROVE");
                }
                else if(intratting==4)
                {
                    content.setText("WHAT WE CAN IMPROVE");
                    fourcontent.setVisibility(View.GONE);


                }
                else if(intratting==5)
                {
                    content.setText("WHAT DID YOU LIKE THE BEST");

                }


                return false;
            }
        });
        deliveryboyratting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linerlayout.setVisibility(View.VISIBLE);
                ratingNumber = restarentratting.getRating();
                intratting=Math.round(ratingNumber);

                //Toast.makeText(RatingBarActivity.this, ""+ratingNumber, Toast.LENGTH_SHORT).show();
                System.out.println("ratingNumber"+intratting);
                if(intratting==3)
                {
                    content1.setText("WHAT WE CAN IMPROVE");
                }
                else if(intratting==4)
                {
                    content.setText("WHAT WE CAN IMPROVE");
                    fourcontent1.setVisibility(View.GONE);


                }
                else if(intratting==5)
                {
                    content.setText("WHAT DID YOU LIKE THE BEST");

                }


                return false;
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent  intent=new Intent(getApplicationContext(),DashBoard.class);
        startActivity(intent);
    }
}
