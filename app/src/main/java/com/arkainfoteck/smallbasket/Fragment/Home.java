package com.arkainfoteck.smallbasket.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.smallbasket.Activity.AddressListActivity;
import com.arkainfoteck.smallbasket.Activity.CatagiresType;
import com.arkainfoteck.smallbasket.Activity.GetAddressIntentService;
import com.arkainfoteck.smallbasket.Activity.RatingBarActivity;
import com.arkainfoteck.smallbasket.Adapter.CatagiresAdapter;
import com.arkainfoteck.smallbasket.Adapter.HomeAdapter;
import com.arkainfoteck.smallbasket.Adapter.RestarentAdapter;
import com.arkainfoteck.smallbasket.model.CatagiresModel;
import com.arkainfoteck.smallbasket.model.HomeModel;
import com.arkainfoteck.smallbasket.model.RestarentModel;
import com.arkainfoteck.smallbasket.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.arkainfoteck.smallbasket.Activity.DashBoard.search;
import static com.arkainfoteck.smallbasket.Activity.DashBoard.toolbar1;

public class Home extends Fragment {
    View view;
    RecyclerView homecontent;
    HomeAdapter homeAdapter;
    ArrayList<HomeModel>homeModels;
    GridLayoutManager gridLayoutManager;

    RecyclerView restarent;
    RestarentAdapter restarentAdapter;
    ArrayList<RestarentModel>restarentModels;
    GridLayoutManager gridLayoutManager1;

    SharedPreferences sharedPreferences;
    String foodtype;
    LinearLayout linearone,lineartwo;
    LinearLayout homelinearlayout;
    ImageView cancelratting;
    LinearLayout layoutmain;
    RatingBar homeratting;


    private LocationCallback locationCallback;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.home_fragment,container,false);
        toolbar1.setVisibility(View.VISIBLE);
        linearone=view.findViewById(R.id.linearone);
        lineartwo=view.findViewById(R.id.lineartwo);
        layoutmain=view.findViewById(R.id.layoutmain);
        homeratting=view.findViewById(R.id.homeratting);
        homelinearlayout=view.findViewById(R.id.homelinearlayout);
        sharedPreferences=getActivity().getSharedPreferences("foodtype", Context.MODE_PRIVATE);
        foodtype= sharedPreferences.getString("Food",null);
        System.out.println("foodtype"+foodtype);
        cancelratting=view.findViewById(R.id.cancelratting);
        cancelratting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homelinearlayout.setVisibility(View.GONE);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        10.0f
                );
                layoutmain.setLayoutParams(param);
            }
        });
        homeratting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent=new Intent(getActivity(), RatingBarActivity.class);
                startActivity(intent);
                return false;
            }
        });





         if(foodtype.equals("1")) {


             RequestQueue rq = Volley.newRequestQueue(getActivity());
             StringRequest stringRequest = new StringRequest(Request.Method.POST,
                     "https://smallbasket.store/api/restaurantdetails",
                              //http://smallbasket.store/api/restaurantdetails
                     new Response.Listener<String>() {


                         public void onResponse(String response) {
                             homecontent = view.findViewById(R.id.homecontent);
                             gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                             homecontent.setLayoutManager(gridLayoutManager);
                             homeModels = new ArrayList<>();

                             System.out.println("response"+response);
                             try {
                                 // Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                                 JSONObject jsonObject1=new JSONObject(response);
                                 JSONArray jsonArray=jsonObject1.getJSONArray("data");
                                 for(int i=0;i<jsonArray.length();i++){
                                     JSONObject jsonObject11=jsonArray.getJSONObject(i);
                                     String id=jsonObject11.getString("id");
                                     String name=jsonObject11.getString("name");
                                     String image=jsonObject11.getString("image");
                                     String product_type=jsonObject11.getString("product_type");
                                     homeModels.add(new HomeModel(id,image, name,"rice",product_type));

                                 }

                                 homeAdapter = new HomeAdapter(getActivity(), homeModels);
                                 homecontent.setAdapter(homeAdapter);
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
                     params.put("id","2");
                     return params;
                 }
             };
             rq.add(stringRequest);





       }
       else if(foodtype.equals("2")){

             RequestQueue rq = Volley.newRequestQueue(getActivity());
             StringRequest stringRequest = new StringRequest(Request.Method.POST,
                     "https://smallbasket.store/api/restaurantdetails",
                     new Response.Listener<String>() {

                         public void onResponse(String response) {
                             lineartwo.setVisibility(View.VISIBLE);
                             linearone.setVisibility(View.GONE);

                             restarent = view.findViewById(R.id.restarent);
                             gridLayoutManager1 = new GridLayoutManager(getActivity(), 1);
                             restarent.setLayoutManager(gridLayoutManager1);
                             restarentModels = new ArrayList<>();

                             try {
                                 System.out.println("response"+response);

                                 // Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                                 JSONObject jsonObject1=new JSONObject(response);
                                 JSONArray jsonArray=jsonObject1.getJSONArray("data");
                                 for(int i=0;i<jsonArray.length();i++){
                                     JSONObject jsonObject11=jsonArray.getJSONObject(i);
                                     String id=jsonObject11.getString("id");
                                     String name=jsonObject11.getString("name");
                                     String image=jsonObject11.getString("image");
                                     String type=jsonObject11.getString("type");
                                     String product_type=jsonObject11.getString("product_type");

                                     restarentModels.add(new RestarentModel(image, name, type, "34",id,product_type));

                                 }
                                 restarentAdapter = new RestarentAdapter(getActivity(), restarentModels);
                                 restarent.setAdapter(restarentAdapter);
                                 addTextListenerforrestarent();

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
                     params.put("id","1");
                     return params;
                 }
             };
             rq.add(stringRequest);
       }
     return view;
    }
    public void addTextListenerforrestarent(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<RestarentModel> filteredList = new ArrayList<>();

                for (int i = 0; i < restarentModels.size(); i++) {

                    final String text = restarentModels.get(i).getRestarentname().toLowerCase();
                    if (text.contains(query)||text.contains("narasimha")) {

                        filteredList.add(restarentModels.get(i));
                    }
                }
                restarent.setLayoutManager(new LinearLayoutManager(getContext()));
                restarentAdapter = new RestarentAdapter(getActivity(), (ArrayList<RestarentModel>) filteredList);
                restarent.setAdapter(restarentAdapter);
                restarentAdapter.notifyDataSetChanged(); // data set changed
            }
        });
    }

    public void addTextListener(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<HomeModel> filteredList = new ArrayList<>();

                for (int i = 0; i < homeModels.size(); i++) {

                    final String text = homeModels.get(i).getShopname().toLowerCase();
                    final String text1=homeModels.get(i).getShoptitle().toLowerCase();
                    if (text.contains(query)||text1.contains(query)) {

                        filteredList.add(homeModels.get(i));
                    }
                }
                homecontent.setLayoutManager(new GridLayoutManager(getContext(),2));
                homeAdapter = new HomeAdapter(getActivity(), (ArrayList<HomeModel>) filteredList);
                homecontent.setAdapter(homeAdapter);
                homeAdapter.notifyDataSetChanged(); // data set changed
            }
        });
    }


}
