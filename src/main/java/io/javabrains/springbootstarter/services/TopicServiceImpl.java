package io.javabrains.springbootstarter.services;


import io.javabrains.springbootstarter.data.models.Topic;
import io.javabrains.springbootstarter.data.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public List<Topic> getTopics(String courseId) {
        return courseService.findCourse(courseId).getTopicsList();
    }

    @Override
    public void addTopic(Topic topic) {
        if (checkTopicExists(topic.getTopicId()))  updateTopic(topic);
        else  createNewTopic(topic);
    }

    private void createNewTopic(Topic topic) {
        courseService.findCourse(topic.getCourseId()).getTopicsList().add(topic);
        topicRepository.save(topic);
    }

    private void updateTopic(Topic topic) {
        Topic foundTopic = topicRepository.findById(topic.getTopicId()).get();
        foundTopic.setName(topic.getName());
        foundTopic.setDescription(topic.getDescription());
        foundTopic.setCourseId(topic.getCourseId());
        topicRepository.save(foundTopic);
    }

    @Override
    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }

    @Override
    public Topic findTopic(String courseId, String topicId) {
        Optional<Topic> foundTopic = topicRepository.findById(topicId);
        if (checkTopicExists(topicId)) {
            return (foundTopic.get().getCourseId().equals(courseId) ? foundTopic.get() : null);
        }
        else return null;
    }

    @Override
    public String getBelongingCourseId(Topic topic) {
        return (checkTopicExists(topic.getTopicId()) ? getCourseId(topic.getTopicId()) : "Topic not found");
    }

    private String getCourseId(String id) {
        return topicRepository.findById(id).get().getCourseId();
    }

    private boolean checkTopicExists(String topicId) {
        return topicRepository.findById(topicId).isPresent();
    }
}
