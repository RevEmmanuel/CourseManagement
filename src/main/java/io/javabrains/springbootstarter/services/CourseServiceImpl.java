package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.data.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
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
        if (checkForCourse(id).isPresent()) courseRepository.deleteById(id);
    }

    private void createNewCourse(Course course) {
        courseRepository.save(course);
    }

    private void updateTopic(Course course) {
        Course foundCourse = checkForCourse(course.getId()).get();
        foundCourse.setName(course.getName());
        foundCourse.setDescription(course.getDescription());
        courseRepository.save(foundCourse);
    }

    private Optional<Course> checkForCourse(String id) {
        return courseRepository.findById(id);
    }
}
