package ru.Bogachev.task_list.web.mappers;

import org.mapstruct.Mapper;
import ru.Bogachev.task_list.domain.task.TaskImage;
import ru.Bogachev.task_list.web.dto.task.TaskImageDto;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
