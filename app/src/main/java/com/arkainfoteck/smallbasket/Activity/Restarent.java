package com.arkainfoteck.smallbasket.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arkainfoteck.smallbasket.Adapter.RestarentAdapter;
import com.arkainfoteck.smallbasket.model.RestarentModel;
import com.arkainfoteck.smallbasket.R;

import java.util.ArrayList;

public class Restarent extends Activity {
    RecyclerView restarent;
    RestarentAdapter restarentAdapter;
    ArrayList<RestarentModel>restarentModels;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restarent);
        restarent=findViewById(R.id.restarent);
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        restarent.setLayoutManager(gridLayoutManager);
        restarentModels=new ArrayList<>();
      /*  restarentModels.add(new RestarentModel(R.drawable.dammy,"ANGARA RESTARUNT","VEG & NON-VEG","34"));
        restarentModels.add(new RestarentModel(R.drawable.dammy,"ANGARA RESTARUNT","VEG & NON-VEG","34"));
        restarentModels.add(new RestarentModel(R.drawable.dammy,"ANGARA RESTARUNT","VEG & NON-VEG","34"));
        restarentModels.add(new RestarentModel(R.drawable.dammy,"ANGARA RESTARUNT","VEG & NON-VEG","34"));
        restarentModels.add(new RestarentModel(R.drawable.dammy,"ANGARA RESTARUNT","VEG & NON-VEG","34"));
        restarentModels.add(new RestarentModel(R.drawable.dammy,"ANGARA RESTARUNT","VEG & NON-VEG","34"));
        restarentAdapter=new RestarentAdapter(getApplicationContext(),restarentModels);*/
       // restarent.setAdapter(restarentAdapter);

    }
}
