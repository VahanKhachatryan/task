package ru.task.task.scheduler;

import org.springframework.context.ApplicationContext;
import ru.task.task.model.Status;
import ru.task.task.model.Task;
import ru.task.task.service.TaskService;
import ru.task.task.service.TimeService;

/**
 * Updates {@link Task} status.
 */
public class ChangeTaskStatusJob implements Runnable {

    private TimeService timeService;
    private TaskService taskService;

    private String taskGUID;
    private Status taskStatus;

    public ChangeTaskStatusJob() {
        // do nothing.
    }

    public ChangeTaskStatusJob(final String taskGUID, final Status taskStatus, ApplicationContext applicationContext) {
        this.timeService = applicationContext.getBean(TimeService.class);
        this.taskService = applicationContext.getBean(TaskService.class);

        this.taskGUID = taskGUID;
        this.taskStatus = taskStatus;
    }

    @Override
    public void run() {
        Task task = taskService.get(taskGUID);
        task.setTimestamp(timeService.getTimestamp());
        task.setStatus(taskStatus);

        taskService.update(task);
    }

}