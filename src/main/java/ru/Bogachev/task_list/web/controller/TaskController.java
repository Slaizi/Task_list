package ru.Bogachev.task_list.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.domain.task.TaskImage;
import ru.Bogachev.task_list.service.TaskService;
import ru.Bogachev.task_list.web.dto.task.TaskDto;
import ru.Bogachev.task_list.web.dto.task.TaskImageDto;
import ru.Bogachev.task_list.web.dto.validation.OnUpdate;
import ru.Bogachev.task_list.web.mappers.TaskImageMapper;
import ru.Bogachev.task_list.web.mappers.TaskMapper;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskImageMapper taskImageMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get TaskDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public TaskDto getById(
            @PathVariable final Long id
    ) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @PutMapping
    @Operation(summary = "Update task")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#dto.id)")
    public TaskDto update(
            @Validated(OnUpdate.class)
            @RequestBody final TaskDto dto
    ) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public void deleteById(@PathVariable final Long id) {
        taskService.delete(id);
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task.")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public void uploadImage(
            @PathVariable final Long id,
            @Validated
            @ModelAttribute final TaskImageDto taskImageDto
    ) {
        TaskImage image = taskImageMapper.toEntity(taskImageDto);
        taskService.uploadImage(id, image);

    }
}
