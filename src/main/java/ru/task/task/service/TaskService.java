package ru.task.task.service;

import ru.task.task.model.Task;

public interface TaskService {

    /**
     * Creates new {@link Task}
     *
     * @return {@link Task}
     */
    Task create();

    /**
     * Finds task by given ID.
     *
     * @param taskId {@link Task} ID,
     * @return {@link Task}
     */
    Task get(final String taskId);

    /**
     * Updates {@link Task}
     *
     * @param task updated {@link Task}
     * @return updated {@link Task}
     */
    Task update(Task task);

    /**
     * Performs work, by passing this task to Spring Task Scheduler.
     *
     * @param task task to perform which.
     */
    void perform(Task task);

}
