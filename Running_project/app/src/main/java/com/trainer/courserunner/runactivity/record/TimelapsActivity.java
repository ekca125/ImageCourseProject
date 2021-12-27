package com.trainer.courserunner.runactivity.record;

import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.UserCourseRecord;
import com.trainer.courserunner.R;
import com.trainer.courserunner.component.drawer.CourseDrawerPolyline;
import com.trainer.courserunner.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.runactivity.papermap.MapDrawer;
import com.trainer.courserunner.runactivity.papermap.NavermapActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimelapsActivity extends NavermapActivity {
    long userCourseId;
    UserCourseRecord[] userLocationloadRecords;

    MapDrawer mapDrawer;
    TimelapsDrawer timelapsDrawer;
    ScheduledExecutorService scheduledExecutorService;

    long refreshMiliseconds = 1000;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.timelaps_all) {
            stopTimelaps();
            timelapsDrawer.currentNumber = timelapsDrawer.maxNumber - 1;
            timelapsDrawer.run();
            return super.onOptionsItemSelected(item);
        }

        switch (item.getItemId()) {
            case R.id.timelaps_speed_05:
                refreshMiliseconds = 2000;
                break;
            case R.id.timelaps_speed_1:
                refreshMiliseconds = 1000;
                break;
            case R.id.timelaps_speed_2:
                refreshMiliseconds = 500;
                break;
            case R.id.timelaps_speed_3:
                refreshMiliseconds = 300;
                break;
            case R.id.timelaps_speed_4:
                refreshMiliseconds = 250;
                break;
            default:
                break;
        }
        stopTimelaps();
        runTimelaps();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        userCourseId = getIntent().getLongExtra("userCourseId", -1);
        if (userCourseId == -1) {
            throw new IllegalArgumentException();
        }
        mapDrawer = this;
        new TimelapsActivityStarter().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimelaps();
    }

    public void runTimelaps() {
        if (scheduledExecutorService != null) {
            stopTimelaps();
        }
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(timelapsDrawer, 0, refreshMiliseconds, TimeUnit.MILLISECONDS);
    }

    public void stopTimelaps() {
        timelapsDrawer.clearOverlay();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        scheduledExecutorService = null;
    }


    class TimelapsActivityStarter extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            userLocationloadRecords = AppDatabaseConnector
                    .getAppDatabaseConnection()
                    .userCourseRecordDao()
                    .getUserLocationRecords(userCourseId);
            timelapsDrawer = new TimelapsDrawer(mapDrawer);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (userLocationloadRecords.length > 0) {
                naverMap.moveCamera(CameraUpdate.scrollTo(new LatLng(userLocationloadRecords[0].userCourseRecordLatitude, userLocationloadRecords[0].userCourseRecordLongitude)));
            }
            runTimelaps();
        }
    }


    class TimelapsDrawer extends CourseDrawerPolyline implements Runnable {
        int currentNumber;
        int maxNumber;

        public TimelapsDrawer(MapDrawer mapDrawer) {
            super(mapDrawer);
            this.maxNumber = userLocationloadRecords.length;
            this.currentNumber = 0;
        }

        @Override
        protected List<DrawingPath> makeDrawing() {
            currentNumber = (currentNumber + 1) % (maxNumber + 1);

            Object[] objects = Arrays.stream(userLocationloadRecords).limit(currentNumber).toArray();
            UserCourseRecord[] userLocationRecords = new UserCourseRecord[objects.length];
            for (int i = 0; i < objects.length; i++) {
                userLocationRecords[i] = (UserCourseRecord) objects[i];
            }

            //생성
            List<DrawingPath> drawingPathList = new ArrayList<>();
            int i = 0;
            while (i < userLocationRecords.length) {
                Integer currentDrawingColor = userLocationRecords[i].userCourseRecordColor;
                //빌더
                DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
                drawingPathBuilder.setColor(currentDrawingColor);
                drawingPathBuilder.setWidth(15);

                if (i > 0) {
                    drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[i - 1]));
                }
                //컬러 경로 만들기
                int j = i;
                while (j < userLocationRecords.length && Objects.equals(currentDrawingColor, userLocationRecords[j].userCourseRecordColor)) {
                    drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[j]));
                    j++;
                }
                drawingPathList.add(drawingPathBuilder.build());
                i++;
            }
            return drawingPathList;
        }

        @Override
        public void run() {
            this.runComponent();
        }
    }
}
