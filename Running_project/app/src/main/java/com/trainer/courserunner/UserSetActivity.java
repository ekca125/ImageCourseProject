package com.trainer.courserunner;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserSetActivity extends AppCompatActivity {
    TextView v1;
    TextView v2;
    EditText editText1;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set);

        v1 = (TextView) findViewById(R.id.bmiResultTextView);
        v2 = (TextView) findViewById(R.id.result_text);
        editText1 = (EditText) findViewById(R.id.input_weight);
        editText2 = (EditText) findViewById(R.id.input_height);
        editText3 = (EditText) findViewById(R.id.input_age);
    }

    public void onBtnResult(View v) {
        String strNum = editText1.getText().toString();
        double wei = Integer.parseInt(strNum);
        strNum = editText2.getText().toString();
        double hei = Integer.parseInt(strNum);
        double res = wei / hei / hei * 10000;
        strNum = Double.toString(res);
        v1.setText(strNum);

        if (res < 18.5) {
            v2.setText("저체중입니다.");
        } else if (res <= 12.9) {
            v2.setText("정상 체중입니다.");
        } else if (res <= 24.9) {
            v2.setText("과체중입니다.");
        } else if (res <= 29.9) {
            v2.setText("경도비만입니다.");
        } else {
            v2.setText("고도비만입니다.");
        }
    }
}