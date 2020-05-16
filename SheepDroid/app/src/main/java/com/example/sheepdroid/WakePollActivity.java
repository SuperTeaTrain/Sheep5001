package com.example.sheepdroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WakePollActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sh_wake_poll);
        ImageButton happy = findViewById(R.id.v_wp_happy);
        ImageButton neutral = findViewById(R.id.v_wp_neutral);
        ImageButton sad = findViewById(R.id.v_wp_sad);
        // SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
        happy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                SharedPreferences.Editor editor = sheepContext.edit();
                editor.putInt(getString(R.string.sp_most_recent_wake_poll), 2);
                editor.apply();
                finish();
            }
        });
        neutral.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                SharedPreferences.Editor editor = sheepContext.edit();
                editor.putInt(getString(R.string.sp_most_recent_wake_poll), 1);
                editor.apply();
                finish();
            }
        });
        sad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                SharedPreferences.Editor editor = sheepContext.edit();
                editor.putInt(getString(R.string.sp_most_recent_wake_poll), 0);
                editor.apply();
                finish();
            }
        });
    }
}
