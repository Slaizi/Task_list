package ru.Bogachev.task_list.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import ru.Bogachev.task_list.domain.task.Task;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(name = "role")
    @CollectionTable(name = "users_roles")
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @CollectionTable(name = "users_tasks")
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Task> tasks;

}
