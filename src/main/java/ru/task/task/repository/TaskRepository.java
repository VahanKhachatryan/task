package ru.task.task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.task.task.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    Task findByGuid(final String guid);

}
