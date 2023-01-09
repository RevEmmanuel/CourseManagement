package io.javabrains.springbootstarter.data.repository;

import io.javabrains.springbootstarter.data.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {

}
