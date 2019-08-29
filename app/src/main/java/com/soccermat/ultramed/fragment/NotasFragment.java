package com.soccermat.ultramed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccermat.ultramed.R;

public class NotasFragment extends Fragment {
    View rootView;
    CardView btnContinue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_description, container, false);
        initViews();

        return rootView;
    }

    private void initViews() {
        btnContinue = rootView.findViewById(R.id.btnContinue);

        btnContinue.setVisibility(View.GONE);
    }
}
