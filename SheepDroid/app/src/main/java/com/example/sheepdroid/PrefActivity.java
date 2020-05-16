package com.example.sheepdroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import java.util.Map;

public class PrefActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
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
        String clientId = MqttClient.generateClientId();

        final MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.0.19:1883",
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "onFailure");
                    Log.d(TAG, exception.toString());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        Button v_pref_button2 = findViewById(R.id.v_pref_button2);
        v_pref_button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Create new MQTT service? Maybe it will stay afloat? I'm not really sure how
                // this works yet.
                SharedPreferences sheepContext = getSharedPreferences("sheepContext", MODE_PRIVATE);
                Map <String, ?> prefsMap = sheepContext.getAll();
                // Okay, there are our variables. Now we need to publish them.
                for(Map.Entry<String, ?> entry: prefsMap.entrySet()){
                    String key = entry.getKey();
                    String value = entry.getValue().toString();
                    MqttMessage qtvalue = new MqttMessage(value.getBytes());
                    try {
                        client.publish(key, qtvalue);
                        Log.d(TAG, "Message published");
                    } catch(MqttPersistenceException e){
                        e.printStackTrace();
                    } catch(MqttException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
