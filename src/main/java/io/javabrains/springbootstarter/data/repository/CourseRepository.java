package io.javabrains.springbootstarter.data.repository;

import io.javabrains.springbootstarter.data.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {

    Optional<Course> findCourseByCourseId(String courseId);

    void deleteCourseByCourseId(String courseId);
}
