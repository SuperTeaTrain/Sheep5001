package com.example.sheepdroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class EnergyPollActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sh_energy_poll);

        Button submit = findViewById(R.id.v_ep_button1);

        RadioGroup energy = findViewById(R.id.rg_ep_energetic);
        RadioGroup happy = findViewById(R.id.rg_ep_happy);
        RadioGroup rest = findViewById(R.id.rg_ep_rested);

        energy.check(R.id.v_ep_energetic_b3);
        happy.check(R.id.v_ep_happy_b3);
        rest.check(R.id.v_ep_rested_b3);
        // Note that we are not restoring anything here.

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                SharedPreferences.Editor editor = sheepContext.edit();
                RadioGroup energy = findViewById(R.id.rg_ep_energetic);
                RadioGroup happy = findViewById(R.id.rg_ep_happy);
                RadioGroup rest = findViewById(R.id.rg_ep_rested);
                System.out.println(energy.getCheckedRadioButtonId());
                String checked_energy_raw = getResources().getResourceEntryName(energy.getCheckedRadioButtonId());
                String checked_happy_raw = getResources().getResourceEntryName(happy.getCheckedRadioButtonId());
                String checked_rest_raw = getResources().getResourceEntryName(rest.getCheckedRadioButtonId());
                int checked_energy = Integer.parseInt(checked_energy_raw.substring(checked_energy_raw.length() - 1)) - 1;
                int checked_happy = Integer.parseInt(checked_happy_raw.substring(checked_happy_raw.length() - 1)) - 1;
                int checked_rest = Integer.parseInt(checked_rest_raw.substring(checked_rest_raw.length() - 1)) - 1;
                editor.putInt(getString(R.string.sp_ep_energy), checked_energy);
                editor.putInt(getString(R.string.sp_ep_happy), checked_happy);
                editor.putInt(getString(R.string.sp_ep_rest), checked_rest);
                editor.apply();
                finish();
            }
        });
    }
}
