package com.trainer.imagecourseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.trainer.imagecourseapp.adapter.CourseAdapter;
import com.trainer.imagecourseapp.data.CourseInfo;
import com.trainer.imagecourseapp.data.CourseInfoBuilder;
import com.trainer.imagecourseapp.enumtype.ModeType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //context
    Context mContext;
    //mod type
    ModeType currentModeType;
    //recycler view
    List<CourseInfo> mCourseInfoList;
    CourseAdapter mCourseAdapter;
    RecyclerView mCourseRecordView;
    //

    //button
    Button mButtonNewStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext=getBaseContext();

        //mode type
        currentModeType = ModeType.IMAGECOURSE;

        //buttun setting
        mButtonNewStart = findViewById(R.id.button_new_start);
        mButtonNewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"TEXT",Toast.LENGTH_SHORT).show();
            }
        });

        //recycler view setting
        mCourseInfoList = new ArrayList<>();
        for (int i=0; i<100; i++) {
            //테스트용 데이터 입력
            CourseInfoBuilder courseInfoBuilder = new CourseInfoBuilder();
            courseInfoBuilder.setName(String.format("TEXT %d", i));
            mCourseInfoList.add(courseInfoBuilder.createCourseInfo()) ;
        }

        mCourseRecordView = findViewById(R.id.recyclerview_course) ;
        mCourseRecordView.setLayoutManager(new LinearLayoutManager(this)) ;

        mCourseAdapter = new CourseAdapter(mCourseInfoList) ;
        mCourseRecordView.setAdapter(mCourseAdapter) ;
        //
    }
}