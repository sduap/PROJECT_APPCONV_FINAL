package com.unipiloto.project_appconv_final.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.unipiloto.project_appconv_final.R;

public class Podometro extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager = null;
    private Sensor stepsSensor;
    private int totalSteps = 0;
    private int previewsTotalSteps = 0;
    private ProgressBar progressBar;
    private TextView steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podometro);

        progressBar = findViewById(R.id.progressBar);
        steps = findViewById(R.id.pasos);

        resetSteps();
//        loadData();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepsSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    protected void onResume(){
        super.onResume();

        if (stepsSensor == null){
            Toast.makeText(this,"Este dispositivo no tiene sensor", Toast.LENGTH_SHORT).show();

        }
        else{
            mSensorManager.registerListener(this,stepsSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            totalSteps = (int) event.values[0];
            int currentSteps = totalSteps - previewsTotalSteps;
            steps.setText(String.valueOf(currentSteps));

            progressBar.setProgress(currentSteps);
        }
    }

    private void resetSteps(){
        steps.setOnClickListener(v -> Toast.makeText(Podometro.this, "Mantenga presionado para reiniciar los pasos", Toast.LENGTH_SHORT).show());

        steps.setOnLongClickListener(v -> {
            previewsTotalSteps = totalSteps;
            steps.setText("0");
            progressBar.setProgress(0);
            saveData();
            return true;
        });
    }

    private void saveData(){
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key1", String.valueOf(previewsTotalSteps));
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        int savedNumber = (int) sharedPref.getFloat("key1", 0f);
        previewsTotalSteps = savedNumber;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}