package com.example.sheepdroid;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sh_main_menu);

        Button v_mm_button1 = findViewById(R.id.v_mm_button1);
        Button v_mm_button2 = findViewById(R.id.v_mm_button2);

        v_mm_button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openEditAlarmActivity();
            }
        });

        v_mm_button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPrefActivity();
            }
        });
    }

    public void openEditAlarmActivity(){
        Intent intent = new Intent(this, EditAlarmActivity.class);
        startActivity(intent);
    }

    public void openPrefActivity(){
        Intent intent = new Intent(this, PrefActivity.class);
        startActivity(intent);
    }
}
