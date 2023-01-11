package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.data.repository.CourseRepository;
import io.javabrains.springbootstarter.dtos.requests.CreateCourseRequest;
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
    public void addCourse(CreateCourseRequest courseRequest) {
        if(checkCourseExists(courseRequest.getCourseId())) innerUpdateCourse(courseRequest);
        else createNewCourse(courseRequest);
    }

    @Override
    public void deleteCourse(String id) {
        if (checkCourseExists(id)) courseRepository.deleteCourseByCourseId(id);
    }

    private void createNewCourse(CreateCourseRequest courseRequest) {
        Course newCourse = new Course();
        newCourse.setCourseId(courseRequest.getCourseId());
        newCourse.setName(courseRequest.getName());
        newCourse.setDescription(courseRequest.getDescription());
        courseRepository.save(newCourse);
    }

    public void addCourse(Course course) {
        Course foundCourse = checkForCourse(course.getCourseId()).get();
        foundCourse.setName(course.getName());
        foundCourse.setDescription(course.getDescription());
        foundCourse.setTopicsList(course.getTopicsList());
        courseRepository.save(foundCourse);
    }

    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }

    private void innerUpdateCourse(CreateCourseRequest courseRequest) {
        Course foundCourse = checkForCourse(courseRequest.getCourseId()).get();
        foundCourse.setName(courseRequest.getName());
        foundCourse.setDescription(courseRequest.getDescription());
        courseRepository.save(foundCourse);
    }

    private Optional<Course> checkForCourse(String id) {
        return courseRepository.findCourseByCourseId(id);
    }

    private boolean checkCourseExists(String id) {
        return courseRepository.findCourseByCourseId(id).isPresent();
    }
}
