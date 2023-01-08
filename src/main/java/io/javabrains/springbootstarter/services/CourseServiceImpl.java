package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final List<Course> courses = new ArrayList<>(Arrays.asList(
            new Course("1", "Spring Framework Class 1", "Spring Framework Description 1"),
            new Course("2", "Spring Framework Class 2", "Spring Framework Description 2"),
            new Course("3", "Spring Framework Class 3", "Spring Framework Description 3"),
            new Course("4", "Spring Framework Class 4", "Spring Framework Description 4")
    ));

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public Course findCourse(String id) {
        if (checkForCourse(id).isPresent()) return checkForCourse(id).get();
        return null;
    }

    @Override
    public void addCourse(Course course) {
        if(checkForCourse(course.getId()).isPresent()) updateTopic(course);
        else createNewCourse(course);
    }

    @Override
    public void deleteCourse(String id) {
        if (checkForCourse(id).isPresent()) courses.remove(checkForCourse(id).get());
    }

    private void createNewCourse(Course course) {
        courses.add(course);
    }

    private void updateTopic(Course course) {
        Course foundCourse = checkForCourse(course.getId()).get();
        foundCourse.setName(course.getName());
        foundCourse.setDescription(course.getDescription());
    }

    private Optional<Course> checkForCourse(String id) {
        return courses.stream().filter(course -> course.getId().equals(id)).findFirst();
    }
}
