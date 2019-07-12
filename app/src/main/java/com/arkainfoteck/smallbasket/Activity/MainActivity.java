package com.arkainfoteck.smallbasket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.smallbasket.Adapter.HomeAdapter;
import com.arkainfoteck.smallbasket.Adapter.RestarentAdapter;
import com.arkainfoteck.smallbasket.DataBase.CartDatabse;
import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.HomeModel;
import com.arkainfoteck.smallbasket.model.RestarentModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.arkainfoteck.smallbasket.Activity.DashBoard.search;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model>models;
    LinearLayoutManager linearLayoutManager;
    AdopterClass adopterClass;
    Toolbar toolbar;

    TextView count;
    CartDatabse db;
    int selected_count_points;
    private Handler handler;
    private Runnable handlerTask;
    FrameLayout sub_products_counterValuePanel;

    String sessionId,sessionName;

    TextView shop_title_name,mobile,open,timings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.producy_items_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        sub_products_counterValuePanel=findViewById(R.id.sub_products_counterValuePanel);
        search=findViewById(R.id.Sub_products_scarch);
        count=findViewById(R.id.sub_products_count);

        shop_title_name=(TextView)findViewById(R.id.shop_title_name);
        mobile=(TextView)findViewById(R.id.mobile);
        open=(TextView)findViewById(R.id.open);
        timings=(TextView)findViewById(R.id.timings);
        db=new CartDatabse(this);

        //  gettting id and type from dashboard
         sessionId= getIntent().getStringExtra("EXTRA_SESSION_ID");
         sessionName= getIntent().getStringExtra("EXTRA_SESSION_TYPE");

        System.out.println("sessionId "+sessionId+"sessionName "+sessionName);

        recyclerView=(RecyclerView)findViewById(R.id.productdescription);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getJSONData();
        recyclerView.setLayoutManager(linearLayoutManager);

        StartTimer();
        // add to card button to show total orders
        sub_products_counterValuePanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ShoppingCart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


    void StartTimer(){
        handler = new Handler();
        handlerTask = new Runnable()
        {
            @Override
            public void run() {
                // do something

                int selected_count_points = db.getProfilesCount();
           //     Toast.makeText(MainActivity.this,""+Integer.toString(selected_count_points),Toast.LENGTH_SHORT).show();
                count.setText(""+Integer.toString(selected_count_points));

                handler.postDelayed(handlerTask, 2000);
            }
        };
        handlerTask.run();
    }
    public  void getJSONData(){
        RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://smallbasket.store/api/productdetails",

                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        models=new ArrayList<>();

                        try {
                            // Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            System.out.println("jsonObject_productdetails"+jsonObject1);
                            String title1=jsonObject1.optString("title");
                            String resstatus1=jsonObject1.optString("resstatus");
                            String ontime1=jsonObject1.optString("ontime");
                            String offtime1=jsonObject1.optString("offtime");
                            String mobile1=jsonObject1.optString("mobile");

                            shop_title_name.setText(title1);
                            mobile.setText(mobile1);
                            open.setText(resstatus1);
                            timings.setText(""+ontime1.substring(0,5)+" AM "+offtime1.substring(0,5)+" PM ");
                            JSONArray jsonArray=jsonObject1.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject11=jsonArray.getJSONObject(i);
                                String id=jsonObject11.getString("id");
                                String r_id=jsonObject11.getString("r_id");
                                String name=jsonObject11.getString("name");
                                String price=jsonObject11.getString("price");
                                String image=jsonObject11.getString("image");
                                String discount=jsonObject11.getString("discount");
                                String product_type_one=jsonObject11.getString("product_type");
                                String restaurant_name=jsonObject11.getString("restaurant_name");
                                String aprice12=jsonObject11.getString("aprice");

                                models.add(new Model(""+id,""+r_id,""+name,""+price,""+discount,""+image,""+product_type_one,""+restaurant_name,""+aprice12));
                            }
                            adopterClass=new AdopterClass(MainActivity.this,models,db);
                            recyclerView.setAdapter(adopterClass);
                            addTextListener();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    };
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TO
                // DO Auto-generated method stub
                //   pd.hide();
            }
        })
        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",sessionId);
                params.put("type",sessionName);

                return params;
            }
        };
        rq.add(stringRequest);
    }
    public void addTextListener(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<Model> filteredList = new ArrayList<>();

                for (int i = 0; i < models.size(); i++) {

                    final String text = models.get(i).getTitle_name().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(models.get(i));
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adopterClass = new AdopterClass(MainActivity.this, (ArrayList<Model>) filteredList,db);
                recyclerView.setAdapter(adopterClass);
                adopterClass.notifyDataSetChanged(); // data set changed
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
}
