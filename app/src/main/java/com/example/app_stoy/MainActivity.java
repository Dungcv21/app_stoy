package com.example.app_stoy;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.app_stoy.Fragment.Favorite;
import com.example.app_stoy.Fragment.Home;
import com.example.app_stoy.Fragment.Notification;
import com.example.app_stoy.Fragment.OfMy;
public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation meowBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meowBottomNavigation = findViewById(R.id.navigation_bar);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_favorite));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_notifications));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_my));
        meowBottomNavigation.setCount(3, "5");
        replace(new Home());
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 1:
                        replace(new Home());
                        break;
                    case 2:
                        replace(new Favorite());
                        break;
                    case 3:
                        replace(new Notification());
                        break;
                    case 4:
                        replace(new OfMy());
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
                Toast.makeText(getApplicationContext(), "Click" + item.getId() , Toast.LENGTH_SHORT).show();
            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"Resel" + item.getId() , Toast.LENGTH_SHORT).show();
            }
        });

    }
    private  void  replace(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram, fragment, null);
        fragmentTransaction.commit();
    }
}
