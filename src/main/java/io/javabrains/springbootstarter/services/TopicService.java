package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Topic;
import io.javabrains.springbootstarter.dtos.requests.CreateTopicRequest;

import java.util.List;

public interface TopicService {

    List<Topic> getTopics(String courseId);

    void addTopic(CreateTopicRequest topicRequest);

    void deleteTopic(String courseId, String topicId);

    Topic findTopic(String courseId, String topicId);

    String getBelongingCourseId(Topic topic);
}
