package ru.Bogachev.task_list.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.domain.user.User;
import ru.Bogachev.task_list.service.TaskService;
import ru.Bogachev.task_list.service.UserService;
import ru.Bogachev.task_list.web.dto.task.TaskDto;
import ru.Bogachev.task_list.web.dto.user.UserDto;
import ru.Bogachev.task_list.web.dto.validation.OnCreate;
import ru.Bogachev.task_list.web.dto.validation.OnUpdate;
import ru.Bogachev.task_list.web.mappers.TaskMapper;
import ru.Bogachev.task_list.web.mappers.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize(
            "@customSecurityExpression.canAccessUser(#dto.id)"
    )
    public UserDto update(
            @Validated(OnUpdate.class)
            @RequestBody final UserDto dto
    ) {
        User user = userMapper.toEntity(dto);
        User updateUser = userService.update(user);
        return userMapper.toDto(updateUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get UserDto by id")
    @PreAuthorize(
            "@customSecurityExpression.canAccessUser(#id)"
    )
    public UserDto getById(
            @PathVariable final Long id
    ) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User by id")
    @PreAuthorize(
            "@customSecurityExpression.canAccessUser(#id)"
    )
    public void deleteById(
            @PathVariable final Long id
    ) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get all User tasks")
    @PreAuthorize(
            "@customSecurityExpression.canAccessUser(#id)"
    )
    public List<TaskDto> getTasksByUserId(
            @PathVariable final Long id
    ) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Add task to User")
    @PreAuthorize(
            "@customSecurityExpression.canAccessUser(#id)"
    )
    public TaskDto createTask(
            @PathVariable final Long id,
            @Validated(OnCreate.class)
            @RequestBody final TaskDto dto
    ) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }
}
