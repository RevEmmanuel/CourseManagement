package io.javabrains.springbootstarter.data.repository;

import io.javabrains.springbootstarter.data.models.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {

}
