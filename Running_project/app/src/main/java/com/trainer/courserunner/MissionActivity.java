package com.trainer.courserunner;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        final TextView text = findViewById(R.id.text);
        ListView listView = findViewById(R.id.listview1);

        List<String> list = new ArrayList<>();
        list.add("2km 달리기 5회 성공");
        list.add("프로젝트 달리기 1회 성공");
        list.add("스케치북 달리기 1회 이상 시도");
        list.add("SNS 1회 이상 공유하기");

        ArrayAdapter adpater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adpater);

    }
}