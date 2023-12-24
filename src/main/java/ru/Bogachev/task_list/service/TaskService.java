package ru.Bogachev.task_list.service;

import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.domain.task.TaskImage;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<Task> getAllByUserId(Long id);

    Task update(Task task);

    Task create(Task task, Long userId);

    void delete(Long id);

    void uploadImage(Long id, TaskImage taskImage);
}
