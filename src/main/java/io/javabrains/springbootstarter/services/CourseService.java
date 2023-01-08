package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getCourses();

    public Course findCourse(String id);

    void addCourse(Course course);

    void deleteCourse(String id);
}
