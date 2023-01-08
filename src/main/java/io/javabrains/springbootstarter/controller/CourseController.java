package io.javabrains.springbootstarter.controller;

import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getCourse() {
        return courseService.getCourses();
    }

    @GetMapping("/courses/{id}")
    public Course findCourse(@PathVariable String id) {
        return courseService.findCourse(id);
    }

    @PostMapping("/courses")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }

}
