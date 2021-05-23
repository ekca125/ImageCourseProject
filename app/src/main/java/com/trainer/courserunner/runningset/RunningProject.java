package com.trainer.courserunner.runningset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.R;

public class RunningProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_running);

        Button btnNewProject=findViewById(R.id.new_project);
        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RunningImageSelectionActivity.class);
                intent.putExtra("runningSetting",getIntent().getSerializableExtra("runningSetting"));
                startActivity(intent);
            }
        });

        Button btnResumeProject=findViewById(R.id.project);
        btnResumeProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RunningProjectRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
