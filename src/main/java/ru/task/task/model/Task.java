package ru.task.task.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Simple task.
 */
@Data
@Document(collection = "Tasks")
public class Task {

    @Id
    @ApiModelProperty(value = "Task id.")
    private String guid;
    @ApiModelProperty(required = true, value = "Task status.")
    private Status status;
    @ApiModelProperty(required = true, value = "Task creation time.")
    private String timestamp;


}
