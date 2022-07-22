package com.example.app_stoy.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.app_stoy.Adapter.HomeAdapter;
import com.example.app_stoy.Model.Product;
import com.example.app_stoy.R;
import com.google.firebase.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    View view;
    GridView gridView;
    String[] des = {"1", "2","3","4","5","6"};
    int[] price = {10, 20, 30, 40, 50,60};
    int[] sold = {1, 2, 3, 4, 5, 6};
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int image = R.drawable.image;
    ArrayList<Product> product;
    HomeAdapter homeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.gridView);
        product = new ArrayList<>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click" + des[position], Toast.LENGTH_SHORT).show();
            }
        });
        db.collection("product")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value,
                                            @Nullable FirebaseFirestoreException e) {
                            product.clear();
                            if (e != null) {
                                Log.d("123", "Listen failed.", e);
                                return;
                            }
                            for (QueryDocumentSnapshot doc : value) {
                                Product pr = new Product("", String.valueOf(doc.get("description")), Integer.parseInt(String.valueOf(doc.get("price"))),
                                        Integer.parseInt(String.valueOf(doc.get("sole"))), String.valueOf(doc.get("date")));
                                product.add(pr);
                            }
                            homeAdapter = new HomeAdapter(getContext(), product);
                            gridView.setAdapter(homeAdapter);
                        }
                    });
//        DocumentReference docRef = db.collection("product").document("0RHGrYELvSYVRsBDgMvq");
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot snapshot,
//                                @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    return;
//                }
//                if (snapshot != null && snapshot.exists()) {
//                    Log.d("123", "Current data: " + snapshot.get("sole"));
//                    //HomeAdapter homeAdapter = new HomeAdapter(getContext(), R.drawable.image, (String) snapshot.get("description"), price, (Integer) snapshot.get("sole"));
//                    HomeAdapter homeAdapter = new HomeAdapter(getContext() , (String) snapshot.get("description"), (String) snapshot.get("price"), "456");
//                    gridView.setAdapter(homeAdapter);
//                } else {
//                    Log.d("123", "Current data: null");
//                }
//            }
//        });
        return  view;
    }
}