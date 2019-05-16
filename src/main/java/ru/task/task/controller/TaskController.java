package ru.task.task.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.task.task.model.Task;
import ru.task.task.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/{id}")
    @ApiOperation(value = "Get task by id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task", response = Task.class),
            @ApiResponse(code = 404, message = "Task not found")
    })
    public ResponseEntity<Task> getTask(@ApiParam(value = "Task's GUID.") @PathVariable String id) {
        Task task = taskService.get(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping("/task")
    @ApiOperation(value = "Add task", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Task was accepted.")
    })
    public ResponseEntity<String> createTask() {
        Task task = taskService.create();

        taskService.perform(task);

        return ResponseEntity.accepted().body(task.getGuid());
    }

}