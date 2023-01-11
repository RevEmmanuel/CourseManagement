package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Topic;
import io.javabrains.springbootstarter.dtos.requests.CreateCourseRequest;
import io.javabrains.springbootstarter.dtos.requests.CreateTopicRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TopicServiceImplTest {

    @Autowired
    private TopicService topicService;
    @Autowired
    private CourseService courseService;

    @BeforeEach
    void clearAllRepos() {
        courseService.deleteAll();
        topicService.deleteAll();

        CreateCourseRequest courseRequest = new CreateCourseRequest();
        courseRequest.setCourseId("FSC 112");
        courseRequest.setName("Physics");
        courseRequest.setDescription("Required by all science and engineering students");
        courseService.addCourse(courseRequest);
    }

    @Test
    void addTopicTest() {
        CreateTopicRequest topicRequest = new CreateTopicRequest();
        topicRequest.setTopicId("Topic 1");
        topicRequest.setName("Kinematics");
        topicRequest.setCourseId("FSC 112");
        String topicDescription = "Kinematics is the study of motion of a system of bodies without" +
                " directly considering the forces or potential fields affecting the motion";
        topicRequest.setDescription(topicDescription);
        topicService.addTopic(topicRequest);
        assertEquals(1, topicService.getTopics("FSC 112").size());
    }

    @Test
    void findTopicTest() {
        CreateTopicRequest topicRequest = new CreateTopicRequest();
        topicRequest.setTopicId("Topic 1");
        topicRequest.setName("Kinematics");
        topicRequest.setCourseId("FSC 112");
        String topicDescription = "Kinematics is the study of motion of a system of bodies without" +
                " directly considering the forces or potential fields affecting the motion";
        topicRequest.setDescription(topicDescription);
        topicService.addTopic(topicRequest);
        assertEquals(1, topicService.getTopics("FSC 112").size());
        Topic foundTopic = topicService.findTopic("FSC 112", "Topic 1");
        assertNotNull(foundTopic);
        assertEquals("Topic 1", foundTopic.getTopicId());
        assertEquals("Kinematics", foundTopic.getName());
        assertEquals("FSC 112", foundTopic.getCourseId());
        assertEquals(topicDescription, foundTopic.getDescription());
    }

    @Test
    void deleteTopicTest() {
        CreateTopicRequest topicRequest = new CreateTopicRequest();
        topicRequest.setTopicId("Topic 1");
        topicRequest.setName("Kinematics");
        topicRequest.setCourseId("FSC 112");
        String topicDescription = "Kinematics is the study of motion of a system of bodies without" +
                " directly considering the forces or potential fields affecting the motion";
        topicRequest.setDescription(topicDescription);
        topicService.addTopic(topicRequest);
        assertEquals(1, topicService.getTopics("FSC 112").size());

        topicService.deleteTopic("FSC 112", "Topic 1");
        assertEquals(0, topicService.getTopics("FSC 112").size());
        assertNull(topicService.findTopic("FSC 112", "Topic 1"));
    }

    @Test
    void updateTopicTest() {
        CreateTopicRequest topicRequest = new CreateTopicRequest();
        topicRequest.setTopicId("Topic 1");
        topicRequest.setName("Kinematics");
        topicRequest.setCourseId("FSC 112");
        String topicDescription = "Kinematics is the study of motion of a system of bodies without" +
                " directly considering the forces or potential fields affecting the motion";
        topicRequest.setDescription(topicDescription);
        topicService.addTopic(topicRequest);
        assertEquals(1, topicService.getTopics("FSC 112").size());

        Topic foundTopic = topicService.findTopic("FSC 112", "Topic 1");
        assertNotNull(foundTopic);
        assertEquals("Topic 1", foundTopic.getTopicId());
        assertEquals("Kinematics", foundTopic.getName());
        assertEquals("FSC 112", foundTopic.getCourseId());
        assertEquals(topicDescription, foundTopic.getDescription());


        CreateTopicRequest newTopicRequest = new CreateTopicRequest();
        newTopicRequest.setTopicId("Topic 1");
        newTopicRequest.setName("Dynamics");
        newTopicRequest.setCourseId("FSC 112");
        String newTopicDescription = "branch of physical science and subdivision of mechanics that " +
                "is concerned with the motion of material objects in relation to the physical factors " +
                "that affect them: force, mass, momentum, and energy.";
        newTopicRequest.setDescription(newTopicDescription);
        topicService.addTopic(newTopicRequest);
        assertEquals(1, topicService.getTopics("FSC 112").size());

        foundTopic = topicService.findTopic("FSC 112", "Topic 1");
        assertNotNull(foundTopic);
        assertEquals("Topic 1", foundTopic.getTopicId());
        assertEquals("Dynamics", foundTopic.getName());
        assertEquals("FSC 112", foundTopic.getCourseId());
        assertEquals(newTopicDescription, foundTopic.getDescription());
    }
}