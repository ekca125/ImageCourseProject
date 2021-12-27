package com.trainer.courserunner.runactivity.set;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.trainer.courserunner.R;
import com.trainer.courserunner.component.maker.CourseMaker;
import com.trainer.courserunner.component.maker.layer.line.LineConnectLayerDfsCustom;
import com.trainer.courserunner.component.maker.layer.quanzation.QuanzationMininumGuarantee;
import com.trainer.courserunner.component.maker.layer.regist.CourseRegistLayerAll;
import com.trainer.courserunner.component.maker.layer.selection.MarkerSelectionLayerAll;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.component.maker.scopetype.ScopeMapInfo;
import com.trainer.courserunner.runactivity.run.GuideRunnerActivity;
import com.trainer.courserunner.runactivity.run.ProjectRunnerActivity;
import com.trainer.courserunner.trainertype.ModeType;
import com.trainer.courserunner.trainertype.StartType;

import java.util.Objects;

public class RunningDistanceActivity extends AppCompatActivity {
    RunningSetting runningSetting;
    Location currentLocation;
    //
    Button mBtnKm2;
    Button mBtnKm4;
    Button mBtnKm6;
    Button mBtnKm8;
    Button mBtnKm10;

    View.OnClickListener getMeterBtnListener(double kilometer) {
        return (View view) -> {
            ScopeMapInfo scopeMapInfo = ScopeMapInfo.makeScopeMapInfoOriginLeftDown(
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude(),
                    kilometer * 500);
            Bitmap bitmap = ((BitmapDrawable) Objects.requireNonNull(ContextCompat.getDrawable(this, runningSetting.getDrawableId()))).getBitmap();
            //
            CourseMaker.Builder builder = new CourseMaker.Builder();
            builder.setCourseRegistLayer(new CourseRegistLayerAll());
            builder.setLineConnectLayer(new LineConnectLayerDfsCustom(0.1));
            builder.setQuanzationLayer(new QuanzationMininumGuarantee());
            builder.setBitmap(bitmap);
            builder.setScopeMapInfo(scopeMapInfo);
            builder.setStartLocation(new ScopeDotAddress(scopeMapInfo, currentLocation.getLongitude(), currentLocation.getLatitude()));
            builder.setFinishEvent(this::nextActivity);
            builder.setMarkerSelectionLayer(new MarkerSelectionLayerAll());
            //builder.setMarkerSelectionLayer(new MarkerSelectionLayerStatic());
            //builder.setMarkerSelectionLayer(new MarkerSelectionNone());
            //
            CourseMaker courseMaker = builder.build();
            courseMaker.runComponent();
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        //Running Setting
        runningSetting = (RunningSetting) getIntent().getSerializableExtra("runningSetting");
        //
        mBtnKm2 = (Button) findViewById(R.id.km2_btn);
        mBtnKm4 = (Button) findViewById(R.id.km4_btn);
        mBtnKm6 = (Button) findViewById(R.id.km6_btn);
        mBtnKm8 = (Button) findViewById(R.id.km8_btn);
        mBtnKm10 = (Button) findViewById(R.id.km10_btn);


        //버튼 이름 변경
        if (runningSetting.getModeType() == ModeType.GUIDERUNNER) {
            mBtnKm2.setText("2km");
            mBtnKm4.setText("4km");
            mBtnKm6.setText("6km");
            mBtnKm8.setText("8km");
            mBtnKm10.setText("10km");

            mBtnKm2.setOnClickListener(getMeterBtnListener(2));
            mBtnKm4.setOnClickListener(getMeterBtnListener(4));
            mBtnKm6.setOnClickListener(getMeterBtnListener(6));
            mBtnKm8.setOnClickListener(getMeterBtnListener(8));
            mBtnKm10.setOnClickListener(getMeterBtnListener(10));
        } else if (runningSetting.getModeType() == ModeType.PROJECTRUNNER) {
            mBtnKm2.setText("4km");
            mBtnKm4.setText("8km");
            mBtnKm6.setText("12km");
            mBtnKm8.setText("16km");
            mBtnKm10.setText("20km");

            mBtnKm2.setOnClickListener(getMeterBtnListener(4));
            mBtnKm4.setOnClickListener(getMeterBtnListener(8));
            mBtnKm6.setOnClickListener(getMeterBtnListener(12));
            mBtnKm8.setOnClickListener(getMeterBtnListener(16));
            mBtnKm10.setOnClickListener(getMeterBtnListener(20));
        } else {
            throw new IllegalArgumentException();
        }

        //위치 불러오기
        gettingCurruntLocation();
    }

    private void gettingCurruntLocation() {
        //위치를 가져오기 전까지 비활성화
        mBtnKm2.setEnabled(false);
        mBtnKm4.setEnabled(false);
        mBtnKm6.setEnabled(false);
        mBtnKm8.setEnabled(false);
        mBtnKm10.setEnabled(false);

        //현재위치 가져오기
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // 새로운 위치의 발견
                currentLocation = location;
                mBtnKm2.setEnabled(true);
                mBtnKm4.setEnabled(true);
                mBtnKm6.setEnabled(true);
                mBtnKm8.setEnabled(true);
                mBtnKm10.setEnabled(true);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        //권한체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null);

    }

    protected void nextActivity(Object courseId) {
        Intent intent = null;
        if (runningSetting.getModeType() == ModeType.GUIDERUNNER) {
            intent = new Intent(getBaseContext(), GuideRunnerActivity.class);
            intent.putExtra("courseId", (Long) courseId);
            intent.putExtra("startType", StartType.NEW);
        } else if (runningSetting.getModeType() == ModeType.PROJECTRUNNER) {
            intent = new Intent(getBaseContext(), ProjectRunnerActivity.class);
            intent.putExtra("courseId", (Long) courseId);
            intent.putExtra("startType", StartType.NEW);
        } else {
            throw new IllegalArgumentException();
        }
        startActivity(intent);
    }
}