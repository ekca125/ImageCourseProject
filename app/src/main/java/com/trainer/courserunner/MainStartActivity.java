package com.trainer.courserunner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.trainer.courserunner.runactivity.record.RecordListActivity;
import com.trainer.courserunner.runactivity.set.RunningActivity;
import com.trainer.courserunner.runactivity.set.RunningProjectRecordActivity;
import com.trainer.courserunner.runactivity.set.RunningSetting;

public class MainStartActivity extends AppCompatActivity {
    private Button mBtnLoginLogout;
    private Button mBtnNewStart;
    private Button mBtnUserLog;
    private Button mBtnLoad;
    private Button mBtnExerciseInfo;
    private Button mBtnMission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

        mBtnLoginLogout=findViewById(R.id.btn_loginlogout);
        mBtnNewStart=findViewById(R.id.btn_newstart);
        mBtnLoad=findViewById(R.id.btn_load);
        mBtnUserLog=findViewById(R.id.btn_userlog);
        mBtnExerciseInfo=findViewById(R.id.btn_exersicecal);
        mBtnMission=findViewById(R.id.btn_mission);

        mBtnLoginLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        mBtnUserLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RecordListActivity.class));
            }
        });

        mBtnNewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RunningActivity.class));
            }
        });

        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RunningProjectRecordActivity.class));
            }
        });

        mBtnExerciseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ExerciseResultsActivity.class));
            }
        });
        mBtnMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MissionActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainstart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_noticesite:
                Intent home3Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafe.naver.com/drawingrunners"));
                startActivity(home3Intent);
                return true;
            case R.id.menu_setting: //설정 이동
                Intent homeIntent2 = new Intent(this, SettingActivity.class);
                startActivity(homeIntent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}