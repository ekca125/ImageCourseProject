package com.trainer.courserunner.bandsupport;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;

import com.jellygom.miband_sdk.MiBandIO.Listener.HeartrateListener;
import com.jellygom.miband_sdk.MiBandIO.Listener.RealtimeStepListener;
import com.jellygom.miband_sdk.MiBandIO.MibandCallback;
import com.jellygom.miband_sdk.Miband;

public class MiBandFunctionSupporter extends FunctionSupporter implements BandFunctionSupport {
    private final Miband miband;
    private final MibandCallback mibandCallback = new MibandCallback() {
        @Override
        public void onSuccess(Object data, int status) {
            switch (status) {
                case MibandCallback.STATUS_SEARCH_DEVICE:
                    miband.connect((BluetoothDevice) data, this);
                    break;
                case MibandCallback.STATUS_CONNECT:
                    break;
                case MibandCallback.STATUS_SEND_ALERT:
                    break;
                case MibandCallback.STATUS_GET_USERINFO:
                    break;
                case MibandCallback.STATUS_SET_USERINFO:
                    break;
                case MibandCallback.STATUS_START_HEARTRATE_SCAN:
                    break;
                case MibandCallback.STATUS_GET_BATTERY:
                    int level = (int) data;
                    break;
                case MibandCallback.STATUS_GET_ACTIVITY_DATA:
                    int steps = (int) data;
                    break;
            }
        }

        @Override
        public void onFail(int errorCode, String msg, int status) {
            switch (status) {
                case MibandCallback.STATUS_SEARCH_DEVICE:
                    break;
                case MibandCallback.STATUS_CONNECT:
                    break;
                case MibandCallback.STATUS_SEND_ALERT:
                    break;
                case MibandCallback.STATUS_GET_USERINFO:
                    break;
                case MibandCallback.STATUS_SET_USERINFO:
                    break;
                case MibandCallback.STATUS_START_HEARTRATE_SCAN:
                    break;
                case MibandCallback.STATUS_GET_BATTERY:
                    break;
                case MibandCallback.STATUS_GET_ACTIVITY_DATA:
                    break;
            }
        }
    };

    private int currentHeartRate;
    private int currentStep;

    public MiBandFunctionSupporter(Context context) {
        super(context);
        miband = new Miband(context);

        BluetoothAdapter bluetoothAdapter = ((BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
        miband.searchDevice(bluetoothAdapter, mibandCallback);
        miband.setHeartRateScanListener(new HeartrateListener() {
            @Override
            public void onNotify(int heartRate) {
                currentHeartRate = heartRate;
            }
        });
        miband.setRealtimeStepListener(new RealtimeStepListener() {
            @Override
            public void onNotify(int steps) {
                currentStep = steps;
            }
        });
    }

    @Override
    public int getHeartRate() {
        return currentHeartRate;
    }

    @Override
    public void alarm() {
        miband.sendAlert(mibandCallback);
    }

    @Override
    public int getStep() {
        return currentStep;
    }
}
