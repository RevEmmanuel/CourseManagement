package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.dtos.requests.CreateCourseRequest;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    Course findCourse(String courseId);

    void addCourse(CreateCourseRequest courseRequest);

    void deleteCourse(String courseId);

    void updateCourse(Course course);
}
