package com.arkainfoteck.smallbasket.Fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkainfoteck.smallbasket.Adapter.ItemordersAdopter;
import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.Itemsordermodel;

import java.util.ArrayList;

public class Items extends Fragment {

View view;
RecyclerView recyclerview_items;

    ArrayList<Itemsordermodel> itemordermodels;
    ItemordersAdopter itemordersAdopter;
    LinearLayoutManager linearLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_items, container, false);

        recyclerview_items=(RecyclerView)view.findViewById(R.id.recyclerview_items);
        itemordermodels=new ArrayList<>();
        itemordermodels.add(new Itemsordermodel(" SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"));
        itemordermodels.add(new Itemsordermodel(" SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"));
        itemordermodels.add(new Itemsordermodel(" SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"));
        itemordermodels.add(new Itemsordermodel(" SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"));
        itemordermodels.add(new Itemsordermodel(" SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"," SAMBER IDLY","Rs 30/- Only"));


        linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerview_items.setLayoutManager(linearLayoutManager);
        itemordersAdopter=new ItemordersAdopter(getContext(),itemordermodels);
        recyclerview_items.setAdapter(itemordersAdopter);

        return view;
    }
}
