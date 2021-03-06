package com.uan.michaelsinner.sabergo;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.uan.michaelsinner.sabergo.Activities.Index;
import com.uan.michaelsinner.sabergo.Utilities.GeneradorExmDiagno;
import com.uan.michaelsinner.sabergo.Utilities.TextFade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends Activity {

    ImageButton nube01, nube02, nube03, nube04;
    Button btnStart;
    TextView tvInicio;
    GeneradorExmDiagno generador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(com.uan.michaelsinner.sabergo.R.layout.activity_main);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Sanlabello.ttf");
        tvInicio = (TextView) findViewById(com.uan.michaelsinner.sabergo.R.id.tvSubtitle);
        tvInicio.setTypeface(font);

        printHashKey();
        new TextFade(getBaseContext(), tvInicio);

        final Animation animTranslate = AnimationUtils.loadAnimation(this,R.anim.move_to);

        nube01 = (ImageButton) findViewById(R.id.ibNube00);
        nube02 = (ImageButton) findViewById(R.id.ibNube01);
        nube03 = (ImageButton) findViewById(R.id.ibNube02);
        nube04 = (ImageButton) findViewById(R.id.ibNube03);

        nube01.startAnimation(animTranslate);
        nube02.startAnimation(animTranslate);
        nube03.startAnimation(animTranslate);
        nube04.startAnimation(animTranslate);




        /*
        * se referencia la parte logica y visual del Button btnStart y se implenta su evento del click
        * */
        btnStart = (Button) findViewById(com.uan.michaelsinner.sabergo.R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toIndex());
            }
        });
        btnStart.setTypeface(font);


    }

    public void printHashKey() {
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

    public Intent toIndex() {
        Intent toIndex = new Intent(MainActivity.this, Index.class);
        return toIndex;
    }
}
