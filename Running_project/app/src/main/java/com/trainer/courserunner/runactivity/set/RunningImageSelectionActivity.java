package com.trainer.courserunner.runactivity.set;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RunningImageSelectionActivity extends AppCompatActivity implements RunningSettingInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_image_selection);

        RunningSetting runningSetting = (RunningSetting) getIntent().getSerializableExtra("runningSetting");

        GridView courseImageView = (GridView) findViewById(R.id.courseImageView);
        courseImageView.setNumColumns(3);
        courseImageView.setAdapter(new ImageAdapter(this));
        courseImageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (runningSetting != null) {
                    runningSetting.setDrawableId((Integer) adapterView.getAdapter().getItem(i));
                    nextActivity(runningSetting);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        });
    }

    @Override
    public void nextActivity(RunningSetting runningSetting) {
        Intent intent = new Intent(getApplicationContext(), RunningDistanceActivity.class);
        intent.putExtra("runningSetting", runningSetting);
        startActivity(intent);
    }

    static class ImageAdapter extends BaseAdapter {
        private final List<Integer> mThumbIds;

        private final Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
            mThumbIds = new ArrayList<>();
            Field[] drawablesFields = R.drawable.class.getFields();
            for (Field field : drawablesFields) {
                try {
                    String fieldName = field.getName();
                    if (fieldName.contains("courseimage")) {
                        mThumbIds.add(field.getInt(null));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getCount() {
            return mThumbIds.size();
        }

        @Override
        public Object getItem(int i) {
            return mThumbIds.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) view;
            }
            imageView.setImageResource(mThumbIds.get(i));
            return imageView;
        }
    }
}