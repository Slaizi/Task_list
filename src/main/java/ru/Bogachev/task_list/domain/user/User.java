package ru.Bogachev.task_list.domain.user;

import lombok.Data;
import ru.Bogachev.task_list.domain.task.Task;

import java.util.List;
import java.util.Set;


@Data
public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String passwordConfirmation;
    private Set<Role> roles;
    private List<Task> tasks;

}
