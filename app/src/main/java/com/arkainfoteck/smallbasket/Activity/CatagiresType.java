package com.arkainfoteck.smallbasket.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.smallbasket.Adapter.CatagiresAdapter;
import com.arkainfoteck.smallbasket.model.CatagiresModel;
import com.arkainfoteck.smallbasket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CatagiresType extends AppCompatActivity {
    LinearLayout food,groceries;
    Button select;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView narasimha,narasimha1;
    String id,name,image;
    RecyclerView catagirestype;
    GridLayoutManager gridLayoutManager;
    ArrayList<CatagiresModel>catagiresModels;
    CatagiresAdapter catagiresAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagires_type);
        catagirestype=findViewById(R.id.catagirestype);
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        catagirestype.setLayoutManager(gridLayoutManager);
        catagirestype.setHasFixedSize(true);
        catagiresModels=new ArrayList<>();

        productdetails();





    }
    protected void productdetails() {

        RequestQueue rq = Volley.newRequestQueue(CatagiresType.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://www.smallbasket.store/api/products",
                new Response.Listener<String>() {

                    public void onResponse(String response) {


                        try {
                            // Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            System.out.println("jsonObject1"+jsonObject1);
                            String data=jsonObject1.getString("data");
                            JSONArray jsonArray=new JSONArray(data);

                            catagiresModels=new ArrayList<>();
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                id=jsonObject.getString("id");
                                name=jsonObject.getString("name");
                                System.out.println("name"+name);
                                image=jsonObject.getString("image");
                                catagiresModels.add(new CatagiresModel(id,name,image));
                                catagiresAdapter=new CatagiresAdapter(getApplicationContext(),catagiresModels);
                                catagirestype.setAdapter(catagiresAdapter);
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





                return params;
            }
        };
        rq.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
