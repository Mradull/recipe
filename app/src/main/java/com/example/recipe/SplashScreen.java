package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    //private static final int SPLASH_SCREEN_TIMEOUT = 3000;
    Animation topanim,bottomanim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image =findViewById(R.id.imageView2);
        image.setAnimation(topanim);
        int SPLASH_SCREEN = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                // Pair[] pairs = new Pair[2];
                // pairs[0] = new Pair<View,String>(image,"logo_image");
                // pairs[1] = new Pair<View,String>(logo,"logo_text");

                // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(splashscreen.this,pairs);
                startActivity(intent//options.toBundle()//
                );
                finish();
            }
        }, SPLASH_SCREEN);
    }
}