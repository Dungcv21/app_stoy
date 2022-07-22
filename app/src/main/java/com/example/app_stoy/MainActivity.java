package com.example.app_stoy;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.app_stoy.Adapter.HomeAdapter;
import com.example.app_stoy.Fragment.Favorite;
import com.example.app_stoy.Fragment.Home;
import com.example.app_stoy.Fragment.Notification;
import com.example.app_stoy.Fragment.OfMy;
import com.example.app_stoy.Model.Product;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public MeowBottomNavigation meowBottomNavigation;
    Toolbar toolbar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        meowBottomNavigation = findViewById(R.id.navigation_bar);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_favorite));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_notifications));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_my));
        meowBottomNavigation.setCount(3, String.valueOf(count));
        db.collection("product")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.d("123", "Listen failed.", e);
                            return;
                        }
                        count += 1;
                        meowBottomNavigation.setCount(3, String.valueOf(count));
                    }
                });
        replace(new Home());
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 1:
                        replace(new Home());
                        getSupportActionBar().setTitle("Home");
                        break;
                    case 2:
                        replace(new Favorite());
                        getSupportActionBar().setTitle("Favorite");
                        break;
                    case 3:
                        replace(new Notification());
                        getSupportActionBar().setTitle("Notification");
                        break;
                    case 4:
                        replace(new OfMy());
                        getSupportActionBar().setTitle("Setting");
                        break;
                    default:
                        replace(new Home());
                }
            }
        });
        meowBottomNavigation.show(1, true);
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

    }
    private  void  replace(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram, fragment, null);
        fragmentTransaction.commit();
    }
    public void meoMeo() {
//        Toast.makeText(getApplication(), "Hello", Toast.LENGTH_SHORT).show();
        Log.d("123", "hello");
        count = 0;
    }
}
