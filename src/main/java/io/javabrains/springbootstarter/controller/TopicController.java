package io.javabrains.springbootstarter.controller;

import io.javabrains.springbootstarter.data.models.Topic;
import io.javabrains.springbootstarter.dtos.requests.CreateTopicRequest;
import io.javabrains.springbootstarter.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/courses/{courseId}/topics")
    public List<Topic> getAllTopics(@PathVariable String courseId) {
        return topicService.getTopics(courseId);
    }

    @GetMapping("/courses/{courseId}/topics/{topicId}")
    public Topic findTopic(@PathVariable String courseId, @PathVariable String topicId) {
        return topicService.findTopic(courseId, topicId);
    }

    @PostMapping("/courses/courseId/topics")
    public void addTopic(@RequestBody CreateTopicRequest topicRequest) {
        topicService.addTopic(topicRequest);
    }

    @DeleteMapping("/courses/{courseId}/topics/{topicId}")
    public void deleteTopic(@PathVariable String topicId, @PathVariable String courseId) {
        topicService.deleteTopic(courseId, topicId);
    }

}
