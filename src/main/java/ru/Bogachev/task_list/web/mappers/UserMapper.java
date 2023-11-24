package ru.Bogachev.task_list.web.mappers;

import org.mapstruct.Mapper;
import ru.Bogachev.task_list.domain.user.User;
import ru.Bogachev.task_list.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity (UserDto userDto);

}
