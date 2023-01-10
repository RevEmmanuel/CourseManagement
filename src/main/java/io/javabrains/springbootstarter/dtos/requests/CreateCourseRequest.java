package io.javabrains.springbootstarter.dtos.requests;

import io.javabrains.springbootstarter.data.models.Topic;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCourseRequest {

    private String courseId;
    private String name;
    private String description;
}
