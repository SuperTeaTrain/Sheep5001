package com.example.sheepdroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PrefActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sh_preferences);
        SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);

        RadioGroup rg_vibration = findViewById(R.id.rg_pref_vibration);
        RadioGroup rg_lights = findViewById(R.id.rg_pref_light);

        boolean vibration = sheepContext.getBoolean(getString(R.string.sp_vibration), true);
        boolean lights = sheepContext.getBoolean(getString(R.string.sp_lights), true);
        if (vibration){
            rg_vibration.check(R.id.v_pref_vibration_b1);
        }
        else{
            rg_vibration.check(R.id.v_pref_vibration_b2);
        }
        if (lights){
            rg_lights.check(R.id.v_pref_light_b1);
        }
        else{
            rg_lights.check(R.id.v_pref_light_b2);
        }
        Button v_pref_button1 = findViewById(R.id.v_pref_button1);
        v_pref_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                SharedPreferences.Editor editor = sheepContext.edit();
                RadioButton v_pref_vibration_b1 = findViewById(R.id.v_pref_vibration_b1);
                RadioButton v_pref_lights_b1 = findViewById(R.id.v_pref_light_b1);
                editor.putBoolean(getString(R.string.sp_vibration), v_pref_vibration_b1.isChecked());
                editor.putBoolean(getString(R.string.sp_lights), v_pref_lights_b1.isChecked());
                editor.apply();
                finish();
            }
        });
    }
}
