package com.example.tickerwatchlistmanager_demille;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            TickerListFragment tickerListFragment = new TickerListFragment();
            InfoWebFragment infoWebFragment = new InfoWebFragment();

            ft.add(R.id.fragmentContainerView, tickerListFragment, "tickerListFrag");
            ft.add(R.id.fragmentContainerView2, infoWebFragment, "infoWebFrag");

            ft.commit();
        }
    }
}