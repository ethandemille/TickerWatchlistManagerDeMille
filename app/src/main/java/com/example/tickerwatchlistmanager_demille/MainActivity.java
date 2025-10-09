package com.example.tickerwatchlistmanager_demille;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            String[] perm = new String[] {Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, perm, 127);
        }

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String sms = intent.getStringExtra("sms");
        //Toast.makeText(this,sms, Toast.LENGTH_LONG).show();
        if(true) { // here you will check that it is valid data
            Toast.makeText(this, sms, Toast.LENGTH_LONG).show();
        }
    else{
            Toast.makeText(this, "No valid SMS was found",
                Toast.LENGTH_LONG).show();
        }
    }
}