package ru.Bogachev.task_list.service;

import ru.Bogachev.task_list.domain.user.User;

public interface UserService {
    User userGetById (Long id);
    User userGetByUsername(String username);
    User update (User user);
    User create (User user);
    boolean isTaskOwner (Long userId, Long taskId);
    void delete(Long id);
}
