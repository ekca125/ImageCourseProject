package com.trainer.courserunner.bandsupport;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

//휴대폰만 존재하는 경우
public class PhoneFunctionSupporter extends FunctionSupporter implements BaseFunctionSupport, SensorEventListener {
    private final SensorManager sensorManager;
    private int initStep;
    private int currentStep;

    public PhoneFunctionSupporter(Context context) {
        super(context);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorStep = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorStep, SensorManager.SENSOR_DELAY_GAME);
        //
        initStep = -1;
        currentStep = -1;
    }

    public void destructor() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public int getStep() {
        return currentStep;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int sensorStep = (int) sensorEvent.values[0];
            if (initStep == -1 || currentStep == -1) {
                initStep = sensorStep;
                currentStep = 0;
            }
            currentStep = initStep - sensorStep;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
