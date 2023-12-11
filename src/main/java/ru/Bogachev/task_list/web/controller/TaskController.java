package ru.Bogachev.task_list.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.service.TaskService;
import ru.Bogachev.task_list.web.dto.task.TaskDto;
import ru.Bogachev.task_list.web.dto.validation.OnUpdate;
import ru.Bogachev.task_list.web.mappers.TaskMapper;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get TaskDto by id")
    public TaskDto getById (@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @PutMapping
    @Operation(summary = "Update task")
    public TaskDto update (@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task")
    public void deleteById (@PathVariable Long id) {
        taskService.delete(id);
    }
}
