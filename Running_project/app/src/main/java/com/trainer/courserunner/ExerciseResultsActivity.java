package com.trainer.courserunner;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.trainer.courserunner.Application.rooms.DateConverters;
import com.trainer.courserunner.Application.rooms.UserCourseAnalyzer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExerciseResultsActivity extends AppCompatActivity {
    TextView v1; //총 운동 거리
    TextView v2; //총 운동 시간
    TextView v3; //총 소비 칼로리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_results);

        v1 = (TextView) findViewById(R.id.input_distance);
        v2 = (TextView) findViewById(R.id.input_time);
        v3 = (TextView) findViewById(R.id.input_calorie);

        long userCourseId = getIntent().getLongExtra("userCourseId", -1);
        Double distance = UserCourseAnalyzer.getDistance(userCourseId);
        Pair<Date, Date> startEndTime = UserCourseAnalyzer.getStartEndTime(userCourseId);
        long time = DateConverters.dateToTimestamp(startEndTime.second) - DateConverters.dateToTimestamp(startEndTime.first);
        Date date = DateConverters.fromTimestamp(time);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.US);

        double distance2 = (double) Math.round(distance);
        int calorie = (int) ((distance2 / 1000) * 80);
        v1.setText(String.valueOf((int) distance2) + "m");
        v2.setText(format.format(date));
        v3.setText(String.valueOf(calorie));
        //1시간당 칼로리
        //모든 칼로리 구하기
    }
}