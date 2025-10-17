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

    TickerListFragment tickerListFragment = new TickerListFragment();
    InfoWebFragment infoWebFragment = new InfoWebFragment();

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



            ft.add(R.id.fragmentContainerView, tickerListFragment, "tickerListFrag");
            ft.add(R.id.fragmentContainerView2, infoWebFragment, "infoWebFrag");

            ft.commit();
        }
    }
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String tickerPattern = "^Ticker:<<[a-zA-Z]+>>$";
        String tickerPatternBroad = "^Ticker:<<.+>>$";
        String sms = intent.getStringExtra("sms");
        //Toast.makeText(this,sms, Toast.LENGTH_LONG).show();
        if(sms.matches(tickerPatternBroad) && sms.matches(tickerPattern)) {
            String ticker = sms.substring(9, sms.indexOf(">>")).toUpperCase();
            //Toast.makeText(this, ticker, Toast.LENGTH_LONG).show();
            String url = "https://seekingalpha.com/symbol/" + ticker;
            if (infoWebFragment != null) {
                //change later?
                infoWebFragment.loadUrl(url);
            }
            tickerListFragment.addTicker(ticker);
        } else if (sms.matches(tickerPatternBroad)) {
            Toast.makeText(this, "Invalid Ticker Symbol", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Invalid Message Format",
                Toast.LENGTH_LONG).show();
        }
    }
}