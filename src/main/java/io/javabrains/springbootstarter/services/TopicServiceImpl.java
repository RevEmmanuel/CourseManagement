package io.javabrains.springbootstarter.services;


import io.javabrains.springbootstarter.data.models.Course;
import io.javabrains.springbootstarter.data.models.Topic;
import io.javabrains.springbootstarter.data.repository.TopicRepository;
import io.javabrains.springbootstarter.dtos.requests.CreateTopicRequest;
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
    public void addTopic(CreateTopicRequest topicRequest) {
        if (checkTopicExists(topicRequest.getTopicId()))  updateTopic(topicRequest);
        else  createNewTopic(topicRequest);
    }

    private void createNewTopic(CreateTopicRequest topicRequest) {
        Course course = courseService.findCourse(topicRequest.getCourseId());
        Topic topic = new Topic();
        topic.setTopicId(topicRequest.getTopicId());
        topic.setDescription(topicRequest.getDescription());
        topic.setName(topicRequest.getName());
        topic.setCourseId(topicRequest.getCourseId());
        Topic savedTopic = topicRepository.save(topic);
        course.getTopicsList().add(savedTopic);
        courseService.addCourse(course);
    }

    private void updateTopic(CreateTopicRequest topicRequest) {
        Topic foundTopic = topicRepository.findTopicByTopicId(topicRequest.getTopicId()).get();
        Course course = courseService.findCourse(foundTopic.getCourseId());
        course.getTopicsList().remove(foundTopic);
        foundTopic.setName(topicRequest.getName());
        foundTopic.setDescription(topicRequest.getDescription());
        foundTopic.setCourseId(topicRequest.getCourseId());

        course.getTopicsList().add(foundTopic);
        courseService.addCourse(course);
        topicRepository.save(foundTopic);
    }

    @Override
    public void deleteTopic(String courseId,String topicId) {
        Topic foundTopic = topicRepository.findTopicByTopicId(topicId).get();
        Course course = courseService.findCourse(foundTopic.getCourseId());
        course.getTopicsList().remove(foundTopic);
        courseService.addCourse(course);
        topicRepository.deleteTopicByTopicId(topicId);
    }

    @Override
    public Topic findTopic(String courseId, String topicId) {
        Optional<Topic> foundTopic = topicRepository.findTopicByTopicId(topicId);
        if (checkTopicExists(topicId)) {
            return (foundTopic.get().getCourseId().equals(courseId) ? foundTopic.get() : null);
        }
        else return null;
    }

    @Override
    public void deleteAll() {
        topicRepository.deleteAll();
    }

    private String getCourseId(String id) {
        return topicRepository.findTopicByTopicId(id).get().getCourseId();
    }

    private boolean checkTopicExists(String topicId) {
        return topicRepository.findTopicByTopicId(topicId).isPresent();
    }
}
