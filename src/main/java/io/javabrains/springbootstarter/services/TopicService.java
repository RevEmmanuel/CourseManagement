package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Topic;

import java.util.List;

public interface TopicService {

    public List<Topic> getTopics();

    public Topic findTopic(String id);

    void addTopic(Topic topic);

    void deleteTopic(String id);
}
