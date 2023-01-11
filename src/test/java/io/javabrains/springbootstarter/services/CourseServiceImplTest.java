package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.dtos.requests.CreateCourseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @BeforeEach
    void clearRepo() {
        courseService.deleteAll();
    }

    @Test
    void findCourse() {
        CreateCourseRequest courseRequest = new CreateCourseRequest();
        courseRequest.setCourseId("FSC 112");
        courseRequest.setName("Physics");
        courseRequest.setDescription("Required by all science and engineering students");
        courseService.addCourse(courseRequest);

        assertEquals(1, courseService.getCourses().size());
        assertEquals("FSC 112", courseService.findCourse("FSC 112").getCourseId());
        assertEquals("Physics", courseService.findCourse("FSC 112").getName());
        assertEquals("Required by all science and engineering students", courseService.findCourse("FSC 112").getDescription());
    }

    @Test
    void addCourse() {
        CreateCourseRequest courseRequest = new CreateCourseRequest();
        courseRequest.setCourseId("FSC 112");
        courseRequest.setName("Physics");
        courseRequest.setDescription("Required by all science and engineering students");
        courseService.addCourse(courseRequest);

        assertEquals(1, courseService.getCourses().size());
    }

    @Test
    void deleteCourse() {
        CreateCourseRequest courseRequest = new CreateCourseRequest();
        courseRequest.setCourseId("FSC 112");
        courseRequest.setName("Physics");
        courseRequest.setDescription("Required by all science and engineering students");
        courseService.addCourse(courseRequest);

        assertEquals(1, courseService.getCourses().size());
        courseService.deleteCourse("FSC 112");
        assertEquals(0, courseService.getCourses().size());
        assertNull(courseService.findCourse("FSC 112"));
    }

    @Test
    void updateCourse() {
        CreateCourseRequest courseRequest = new CreateCourseRequest();
        courseRequest.setCourseId("FSC 112");
        courseRequest.setName("Physics");
        courseRequest.setDescription("Required by all science and engineering students");
        courseService.addCourse(courseRequest);

        assertEquals(1, courseService.getCourses().size());
        assertEquals("FSC 112", courseService.findCourse("FSC 112").getCourseId());
        assertEquals("Physics", courseService.findCourse("FSC 112").getName());
        assertEquals("Required by all science and engineering students", courseService.findCourse("FSC 112").getDescription());

        CreateCourseRequest newCourseRequest = new CreateCourseRequest();
        newCourseRequest.setCourseId("FSC 112");
        newCourseRequest.setName("Physics");
        newCourseRequest.setDescription("Required by all science and only some of the engineering students");
        courseService.addCourse(newCourseRequest);
        assertEquals(1, courseService.getCourses().size());
        assertEquals("FSC 112", courseService.findCourse("FSC 112").getCourseId());
        assertEquals("Physics", courseService.findCourse("FSC 112").getName());
        assertEquals("Required by all science and only some of the engineering students", courseService.findCourse("FSC 112").getDescription());
    }
}