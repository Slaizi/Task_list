package ru.Bogachev.task_list.service.impl;

import org.springframework.stereotype.Service;
import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllByUserId(Long id) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Task create(Task task, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
