package io.javabrains.springbootstarter.data.repository;

import io.javabrains.springbootstarter.data.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

    Optional<Course> findCourseByCourseId(String courseId);

    void deleteCourseByCourseId(String courseId);
}
