package com.example.sheepdroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class EditAlarmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sh_edit_alarm);

        Button button_save = findViewById(R.id.v_ed_button1);
        Button button_delete = findViewById(R.id.v_ed_button2);
        RadioGroup meridian = findViewById(R.id.rg_ed_meridian);
        Spinner hour_spinner = findViewById(R.id.v_ed_hour);
        Spinner minute_tens_spinner = findViewById(R.id.v_ed_minten);
        Spinner minute_ones_spinner = findViewById(R.id.v_ed_minone);

        SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
        int hour_index = Integer.parseInt(sheepContext.getString(getString(R.string.sp_deadline_hour), "6")) - 1;
        int ten_min_index = Integer.parseInt(sheepContext.getString(getString(R.string.sp_deadline_minute), "30")) / 10; // Intentionally integer division.
        int one_min_index = Integer.parseInt(sheepContext.getString(getString(R.string.sp_deadline_minute), "30")) % 10;
        boolean is_am = sheepContext.getBoolean(getString(R.string.sp_is_AM), true);


        hour_spinner.setSelection(hour_index);
        minute_tens_spinner.setSelection(ten_min_index);
        minute_ones_spinner.setSelection(one_min_index);
        if(is_am){
            meridian.check(R.id.v_ed_button3);
        }
        else{
            meridian.check(R.id.v_ed_button4);
        }

        button_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                SharedPreferences.Editor editor = sheepContext.edit();
                Spinner hour_spinner = findViewById(R.id.v_ed_hour);
                Spinner minute_tens_spinner = findViewById(R.id.v_ed_minten);
                Spinner minute_ones_spinner = findViewById(R.id.v_ed_minone);
                RadioButton am_check = findViewById(R.id.v_ed_button3);
                editor.putString(getString(R.string.sp_deadline_hour), hour_spinner.getSelectedItem().toString());
                int minute_value;
                minute_value = Integer.parseInt(minute_tens_spinner.getSelectedItem().toString()) * 10;
                minute_value += Integer.parseInt(minute_ones_spinner.getSelectedItem().toString());
                editor.putString(getString(R.string.sp_deadline_minute), Integer.toString(minute_value));
                editor.putBoolean(getString(R.string.sp_deadline_active), true);
                editor.putBoolean(getString(R.string.sp_is_AM), am_check.isChecked());
                editor.apply();
                finish();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                SharedPreferences.Editor editor = sheepContext.edit();
                editor.putString(getString(R.string.sp_deadline_hour), "6");
                editor.putString(getString(R.string.sp_deadline_minute), "30");
                editor.putBoolean(getString(R.string.sp_is_AM), true);
                editor.putBoolean(getString(R.string.sp_deadline_active), false);
                editor.apply();
                finish();
            }
        });
    }
}
