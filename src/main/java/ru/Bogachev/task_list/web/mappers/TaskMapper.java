package ru.Bogachev.task_list.web.mappers;

import org.mapstruct.Mapper;
import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.web.dto.task.TaskDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto (Task task);
    List<TaskDto> toDto(List<Task> tasks);
    Task toEntity (TaskDto dto);

}
