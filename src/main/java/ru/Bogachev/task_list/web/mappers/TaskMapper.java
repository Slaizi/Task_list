package ru.Bogachev.task_list.web.mappers;

import org.mapstruct.Mapper;
import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.web.dto.task.TaskDto;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}
