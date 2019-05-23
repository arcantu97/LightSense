package com.example.lightsense;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    ImageView constraintLayout, lightico;
    TextView value;
    FloatingActionButton fab;
    SensorManager sensorManager;
    Sensor lightSensor;
    Sensor darkSensor;
    Boolean activa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Imports
        value = findViewById(R.id.valor);
        lightico = findViewById(R.id.brillo);
        constraintLayout = findViewById(R.id.activity);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        constraintLayout.setBackgroundColor(Color.WHITE);
        value.setTextColor(Color.BLACK);
    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float light = event.values[0];
        if (light >= 40) {
            constraintLayout.setBackgroundColor(Color.WHITE);
            value.setTextColor(Color.BLACK);
            value.setText("Valor: " + event.values[0]);
            lightico.setImageResource(R.drawable.brightness_off);
            Toast.makeText(MainActivity.this, "Desactivando luces!", Toast.LENGTH_SHORT).show();

        }
        else{
            constraintLayout.setBackgroundColor(Color.BLACK);
            value.setTextColor(Color.WHITE);
            value.setText("Valor: " + event.values[0]);
            activa = true;
            System.out.println("Luz activada " + activa);
            lightico.setImageResource(R.drawable.brightness_on);
            Toast.makeText(MainActivity.this, "Activando luces!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
