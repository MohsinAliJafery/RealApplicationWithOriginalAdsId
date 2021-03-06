package com.bhjbestkalyangame.realapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Calendar;


public class disclaimer extends AppCompatActivity {

    Button mContinue;
    private final String MyCredit = "mycredit";
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);


        final SharedPreferences mSharedPreferences = getSharedPreferences(MyCredit, Context.MODE_PRIVATE);
        mContinue = findViewById(R.id.continue_click);

        final boolean isFirstTimeLoad = mSharedPreferences.getBoolean("isFirstTimeLoad", true);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });


        mInterstitialAd = new InterstitialAd(this);


//              App ID Admob   Interestial Ad
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");


        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {


            }
        });

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    if(isFirstTimeLoad) {
                        Intent mIntent = new Intent(disclaimer.this, CongratulatingReward.class);
                        startActivity(mIntent);
                    }else{
                        Intent mIntent = new Intent(disclaimer.this, KalyanWorkActivity.class);
                        startActivity(mIntent);
                    }
                    mSharedPreferences.edit().putBoolean("isFirstTimeLoad", false).apply();

                }else {

                    if (isFirstTimeLoad) {
                        Intent mIntent = new Intent(disclaimer.this, CongratulatingReward.class);
                        startActivity(mIntent);
                    }else {
                        Intent mIntent = new Intent(disclaimer.this, KalyanWorkActivity.class);
                        startActivity(mIntent);
                    }
                    mSharedPreferences.edit().putBoolean("isFirstTimeLoad", false).apply();

                }
                }
        });



    }

}
