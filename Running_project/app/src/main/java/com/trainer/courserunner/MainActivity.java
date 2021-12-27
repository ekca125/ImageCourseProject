package com.trainer.courserunner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.runactivity.record.RecordListActivity;
import com.trainer.courserunner.runactivity.set.RunningActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button imageButton = (Button) findViewById((R.id.running_btn));
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RunningActivity.class);
            startActivity(intent);
        });
        Button imageButton2 = (Button) findViewById((R.id.result_btn));
        imageButton2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RecordListActivity.class);
            startActivity(intent);
        });
        Button imageButton3 = (Button) findViewById((R.id.mission_btn));
        imageButton3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MissionActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_btn2: //BMI
                Intent homeIntent = new Intent(this, UserSetActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.action_btn3: //설정 이동
                Intent homeIntent2 = new Intent(this, SettingActivity.class);
                startActivity(homeIntent2);
                return true;
            case R.id.action_btn4: //공지사항 및 QnA 카페 이동
                Intent home3Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafe.naver.com/drawingrunners"));
                startActivity(home3Intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void playBtn() {
    }
}