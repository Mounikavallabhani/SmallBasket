package com.arkainfoteck.smallbasket.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arkainfoteck.smallbasket.Activity.LoginActivity;
import com.arkainfoteck.smallbasket.Activity.MyorderActivity;
import com.arkainfoteck.smallbasket.Activity.NotificationsActivity;
import com.arkainfoteck.smallbasket.R;

import static com.arkainfoteck.smallbasket.Activity.DashBoard.toolbar1;


public class Me extends Fragment {
    View view;
    TextView logout;
    LinearLayout myorders,notification;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.me_fragment,container,false);
        toolbar1.setVisibility(View.GONE);
        notification=view.findViewById(R.id.notification);
        logout=view.findViewById(R.id.logout);
        myorders=view.findViewById(R.id.myorders);
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyorderActivity.class);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NotificationsActivity.class);
                startActivity(intent);
            }
        });
     return view;
    }
}
