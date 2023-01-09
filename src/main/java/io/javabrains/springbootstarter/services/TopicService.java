package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Topic;
import java.util.List;

public interface TopicService {

    List<Topic> getTopics(String courseId);

    void addTopic(Topic topic);

    void deleteTopic(String id);

    Topic findTopic(String courseId, String topicId);

    String getBelongingCourseId(Topic topic);
}
