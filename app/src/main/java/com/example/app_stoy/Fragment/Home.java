package com.example.app_stoy.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.app_stoy.Adapter.HomeAdapter;
import com.example.app_stoy.R;

public class Home extends Fragment {
    View view;
    GridView gridView;
    String[] des = {"1", "2","3","4","5","6"};
    int[] price = {10, 20, 30, 40, 50,60};
    int[] sold = {1, 2, 3, 4, 5, 6};
    int[] image = {R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.gridView);
        HomeAdapter homeAdapter = new HomeAdapter(getContext(), image, des, price,sold);
        gridView.setAdapter(homeAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click" + des[position], Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }
}