package com.trainer.imagecourseapp.data;

public class CourseInfoBuilder {
    private String name;

    public CourseInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CourseInfo createCourseInfo() {
        return new CourseInfo(name);
    }
}