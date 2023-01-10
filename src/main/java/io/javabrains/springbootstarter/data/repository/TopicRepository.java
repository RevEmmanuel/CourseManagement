package io.javabrains.springbootstarter.data.repository;

import io.javabrains.springbootstarter.data.models.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TopicRepository extends MongoRepository<Topic, String> {

    Optional<Topic> findTopicByTopicId(String topicId);

    void deleteTopicByTopicId(String topicId);
}
