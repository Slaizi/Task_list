package ru.Bogachev.task_list.service.impl;

import org.springframework.stereotype.Service;
import ru.Bogachev.task_list.domain.user.User;
import ru.Bogachev.task_list.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User userGetById(Long id) {
        return null;
    }

    @Override
    public User userGetByUsername(String username) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }

}
