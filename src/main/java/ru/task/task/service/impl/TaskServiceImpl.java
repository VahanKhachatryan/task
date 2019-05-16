package ru.task.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import ru.task.task.model.Status;
import ru.task.task.model.Task;
import ru.task.task.repository.TaskRepository;
import ru.task.task.scheduler.ChangeTaskStatusJob;
import ru.task.task.service.TaskService;
import ru.task.task.service.TimeService;

import java.util.Date;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TimeService timeService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public Task create() {
        Task task = new Task();
        task.setStatus(Status.CREATED);
        task.setTimestamp(timeService.getTimestamp());

        return taskRepository.save(task);
    }

    @Override
    public Task get(String taskId) {
        return taskRepository.findByGuid(taskId);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void perform(Task task) {
        task.setStatus(Status.RUNNING);
        task.setTimestamp(timeService.getTimestamp());
        task = taskRepository.save(task);

        threadPoolTaskScheduler.schedule(new ChangeTaskStatusJob(task.getGuid(), Status.FINISHED, applicationContext),
                new Date(System.currentTimeMillis() + 50_000));
    }

}