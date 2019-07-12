package com.miv_sher.hatcheryapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.database.AppRepository;
import com.miv_sher.hatcheryapp.utils.Utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    final Fragment hatchibatorFragment = new HatchibatorFragment();
    final Fragment storeFragment = new StoreFragment();
    final Fragment collectionsFragment = new CollectionsFragment();
    final Fragment statisticsFragment = new StatisticsFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment activeFragment = hatchibatorFragment;
    private ImageView backgroundImageView;
    AppRepository appRepository = AppRepository.getInstance();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    fragmentManager.beginTransaction().hide(activeFragment).show(storeFragment).commit();
                    activeFragment = storeFragment;
                    return true;
                case R.id.navigation_hatchibator:
                    fragmentManager.beginTransaction().hide(activeFragment).show(hatchibatorFragment).commit();
                    activeFragment = hatchibatorFragment;
                    return true;
                case R.id.navigation_collections:
                    fragmentManager.beginTransaction().hide(activeFragment).show(collectionsFragment).commit();
                    activeFragment = collectionsFragment;
                    return true;
                case R.id.navigation_statistics:
                    fragmentManager.beginTransaction().hide(activeFragment).show(statisticsFragment).commit();
                    activeFragment = statisticsFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager.beginTransaction().add(R.id.main_container, statisticsFragment, "3").hide(statisticsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, collectionsFragment, "2").hide(collectionsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, storeFragment, "0").hide(storeFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, hatchibatorFragment, "1").commit();
        navView.setSelectedItemId(R.id.navigation_hatchibator);

        backgroundImageView = findViewById(R.id.backgroundImageView);
        Utils.setScaledImage(backgroundImageView, R.drawable.background_base, false);
    }
}
