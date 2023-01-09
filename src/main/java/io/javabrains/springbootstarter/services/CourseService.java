package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.data.models.Topic;
import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    Course findCourse(String id);

    void addCourse(Course course);

    void deleteCourse(String id);
}
