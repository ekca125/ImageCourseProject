package com.trainer.courserunner.bandsupport;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataObserver;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataStore;

import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class GalaxyFitFunctionSupporter extends FunctionSupporter implements BandFunctionSupport {
    private final HealthDataStore mStore;
    private final StepCountObserver mStepCountObserver;

    private final HealthDataResolver mHealthDataResolver;
    private final HealthDataObserver mHealthDataObserver;

    private int currentStep;

    public GalaxyFitFunctionSupporter(Context context, @NonNull HealthDataStore store,
                                      @Nullable Handler resultHandler) {
        super(context);
        mStore = store;
        mStepCountObserver = new StepCountObserver() {
            @Override
            public void onChanged(int count) {
                currentStep = count;
            }
        };

        mHealthDataResolver = new HealthDataResolver(mStore, resultHandler);
        mHealthDataObserver = new HealthDataObserver(resultHandler) {
            // Update the step count when a change event is received
            @Override
            public void onChange(String dataTypeName) {
                Log.d("TEST", "Observer receives a data changed event");
                readTodayStepCount();
            }
        };
        start();
    }

    @Override
    public int getHeartRate() {
        return -1;
    }

    @Override
    public void alarm() {
        //
    }

    public void start() {
        // Register an observer to listen changes of step count and get today step count

        HealthDataObserver.addObserver(mStore, HealthConstants.StepCount.HEALTH_DATA_TYPE, mHealthDataObserver);

        readTodayStepCount();
    }

    public void stop() {
        HealthDataObserver.removeObserver(mStore, mHealthDataObserver);
    }

    // Read the today's step count on demand
    private void readTodayStepCount() {
        // Set time range from start time of today to the current time
        long startTime = getUtcStartOfDay(System.currentTimeMillis(), TimeZone.getDefault());
        long endTime = startTime + TimeUnit.DAYS.toMillis(1);

        HealthDataResolver.AggregateRequest request = new HealthDataResolver.AggregateRequest.Builder()
                .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM, HealthConstants.StepCount.COUNT, "total_step")
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET, startTime, endTime)
                .build();

        try {
            mHealthDataResolver.aggregate(request).setResultListener(aggregateResult -> {
                try (HealthDataResolver.AggregateResult result = aggregateResult) {
                    Iterator<HealthData> iterator = result.iterator();
                    if (iterator.hasNext()) {
                        mStepCountObserver.onChanged(iterator.next().getInt("total_step"));

                    }

                }
            });
        } catch (Exception e) {
            Log.e("TEST", "Getting step count fails.", e);
        }
    }

    private long getUtcStartOfDay(long time, TimeZone tz) {
        Calendar cal = Calendar.getInstance(tz);
        cal.setTimeInMillis(time);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);

        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    @Override
    public int getStep() {
        return currentStep;
    }

    public interface StepCountObserver {
        void onChanged(int count);
    }
}
