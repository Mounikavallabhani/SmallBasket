package com.arkainfoteck.smallbasket.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkainfoteck.smallbasket.Adapter.CategoriesAdopter;
import com.arkainfoteck.smallbasket.R;
import com.arkainfoteck.smallbasket.model.CategoriesModel;

import java.util.ArrayList;

import static com.arkainfoteck.smallbasket.Activity.DashBoard.toolbar1;

public class Categories extends Fragment {
    RecyclerView categories;
    ArrayList<CategoriesModel>categoriesModels;
    LinearLayoutManager linearLayoutManager;
    CategoriesAdopter categoriesAdopter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.categories_fragment,container,false);

        toolbar1.setVisibility(View.GONE);
       return view;
    }
}
