package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.data.models.Topic;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("1", "Spring Framework Class 1", "Spring Framework Description 1"),
            new Topic("2", "Spring Framework Class 2", "Spring Framework Description 2"),
            new Topic("3", "Spring Framework Class 3", "Spring Framework Description 3"),
            new Topic("4", "Spring Framework Class 4", "Spring Framework Description 4")
    ));

    public List<Topic> getTopics() {
        return topics;
    }

    @Override
    public Topic findTopic(String id) {
        if (checkForTopic(id).isPresent()) return checkForTopic(id).get();
        return null;
    }

    @Override
    public void addTopic(Topic topic) {
        if(checkForTopic(topic.getId()).isPresent()) updateTopic(topic);
        else createNewTopic(topic);
    }

    @Override
    public void deleteTopic(String id) {
        if (checkForTopic(id).isPresent()) topics.remove(checkForTopic(id).get());
    }

    private void createNewTopic(Topic topic) {
        topics.add(topic);
    }

    private void updateTopic(Topic topic) {
        Topic foundTopic = checkForTopic(topic.getId()).get();
        foundTopic.setName(topic.getName());
        foundTopic.setDescription(topic.getDescription());
    }

    private Optional<Topic> checkForTopic(String id) {
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst();
    }
}
