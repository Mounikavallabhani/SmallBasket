package com.arkainfoteck.smallbasket.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkainfoteck.smallbasket.R;

import static com.arkainfoteck.smallbasket.Activity.DashBoard.toolbar1;

public class Offers extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.offer_fragment,container,false);
        toolbar1.setVisibility(View.GONE);
       return view;
    }
}
