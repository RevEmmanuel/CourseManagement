package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.data.models.Topic;
import io.javabrains.springbootstarter.data.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourse(String id) {
        if (checkCourseExists(id)) return checkForCourse(id).get();
        return null;
    }

    @Override
    public void addCourse(Course course) {
        if(checkCourseExists(course.getId())) updateCourse(course);
        else createNewCourse(course);
    }

    @Override
    public void deleteCourse(String id) {
        if (checkCourseExists(id)) courseRepository.deleteById(id);
    }

    private void createNewCourse(Course course) {
        courseRepository.save(course);
    }

    private void updateCourse(Course course) {
        Course foundCourse = checkForCourse(course.getId()).get();
        foundCourse.setName(course.getName());
        foundCourse.setDescription(course.getDescription());
        courseRepository.save(foundCourse);
    }

    private Optional<Course> checkForCourse(String id) {
        return courseRepository.findById(id);
    }

    private boolean checkCourseExists(String id) {
        return courseRepository.findById(id).isPresent();
    }
}
