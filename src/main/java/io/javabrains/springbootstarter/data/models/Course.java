package io.javabrains.springbootstarter.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    private String id;
    private String courseId;
    private String name;
    private String description;
    private List<Topic> topicsList = new ArrayList<>();
}
