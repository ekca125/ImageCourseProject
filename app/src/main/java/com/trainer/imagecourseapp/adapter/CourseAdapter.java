package com.trainer.imagecourseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trainer.imagecourseapp.R;
import com.trainer.imagecourseapp.data.CourseInfo;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    private List<CourseInfo> courseInfoList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_course_name;
        ViewHolder(View itemView) {
            super(itemView) ;
            text_course_name = itemView.findViewById(R.id.text_course_name) ;
        }
    }

    //function
    public CourseAdapter(List<CourseInfo> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }

    //override function
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.carditem_course_record,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CourseAdapter.ViewHolder holder, int position) {
        holder.text_course_name.setText(courseInfoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return courseInfoList.size();
    }
}
