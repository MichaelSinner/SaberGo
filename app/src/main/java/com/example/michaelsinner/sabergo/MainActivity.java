package com.example.michaelsinner.sabergo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.michaelsinner.sabergo.Activities.Index;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends Activity
{

    Button btnStart;
    TextView tvInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        printHashKey();

        /*
        * se referencia la parte logica y visual del Button btnStart y se implenta su evento del click
        * */
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Sanlabello.ttf");
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(toIndex());
            }
        });
        btnStart.setTypeface(font);
    }

    public void printHashKey()
    {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.michaelsinner.sabergo", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("SHA: ", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public Intent toIndex()
    {
        Intent toIndex = new Intent(MainActivity.this, Index.class);
        return toIndex;
    }
}