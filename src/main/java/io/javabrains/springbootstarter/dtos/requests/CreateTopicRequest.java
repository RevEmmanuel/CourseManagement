package io.javabrains.springbootstarter.dtos.requests;

import lombok.Data;

@Data
public class CreateTopicRequest {

    private String topicId;
    private String courseId;
    private String name;
    private String description;
}
