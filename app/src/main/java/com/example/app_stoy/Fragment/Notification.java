package com.example.app_stoy.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_stoy.MainActivity;
import com.example.app_stoy.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Notification extends Fragment {
    View view;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TabLayout tabLayout;
    TabItem mchat,mcall,mstatus;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        tabLayout=view.findViewById(R.id.include);
        mchat=view.findViewById(R.id.chat);
        mcall=view.findViewById(R.id.calls);
        mstatus=view.findViewById(R.id.status);
        viewPager=view.findViewById(R.id.fragmentcontainer);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        pagerAdapter= new com.example.app_stoy.Adapter.PagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());

            if(tab.getPosition()==0 || tab.getPosition()==1|| tab.getPosition()==2)
            {
                pagerAdapter.notifyDataSetChanged();
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
   return  view;
  }


    @Override
    public void onStop() {
        super.onStop();
        DocumentReference documentReference=firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
        documentReference.update("status","Offline").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(),"Now User is Offline",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        DocumentReference documentReference=firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
        documentReference.update("status","Online").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(),"Now User is Online",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
