package com.example.myprojectforbagrut;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 секунды

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Находим логотип и подпись
        ImageView ivLogo = findViewById(R.id.ivLogo);
        TextView tvSubtitle = findViewById(R.id.tvSubtitle);

        // Запускаем анимацию логотипа и текста
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        ivLogo.startAnimation(animation);
        tvSubtitle.startAnimation(animation);

        // Переход через фоновый поток Thread согласно требованиям Багрута
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SPLASH_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, SignUp.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        thread.start();
    }
}