package com.trainer.courserunner;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.Application.rooms.UserCourseAnalyzer;

import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private TextView textViewExerciseCalorie;
    private TextView textViewExerciseDistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewExerciseCalorie = findViewById(R.id.textView_exercise_calorie);
        textViewExerciseDistance = findViewById(R.id.textView_exercise_distance);


        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, day, 0, 0, 0);
                Date startDate = new Date(c.getTimeInMillis());
                c.set(year, month, day + 1, 0, 0, 0);
                Date endDate = new Date(c.getTimeInMillis());

                Long exerciseDistance = UserCourseAnalyzer.getStartEndTimeDistance(startDate, endDate).longValue();
                Long exerciseCalorie = Double.valueOf(exerciseDistance * 80 / 1000).longValue();

                textViewExerciseCalorie.setText("    칼로리 소모 : " + exerciseCalorie.toString() + "kcal");
                textViewExerciseDistance.setText("    운동 거리: " + exerciseDistance.toString() + "m");

            }
        });

        textViewExerciseCalorie.setText("    칼로리 소모 : ");
        textViewExerciseDistance.setText("    운동 거리: ");


    }
}