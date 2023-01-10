package io.javabrains.springbootstarter.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    private String id;
    private String topicId;
    private String courseId;
    private String name;
    private String description;
}
