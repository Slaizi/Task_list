package ru.Bogachev.task_list.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.Bogachev.task_list.config.TestConfig;
import ru.Bogachev.task_list.domain.exception.ResourceNotFoundException;
import ru.Bogachev.task_list.domain.user.Role;
import ru.Bogachev.task_list.domain.user.User;
import ru.Bogachev.task_list.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;

    @Test
    void getById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(user));
        User testUser = userService.getById(id);
        Mockito.verify(userRepository).findById(id);
        Assertions.assertEquals(user, testUser);
    }

    @Test
    void getByIdWithNotExistingId() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.getById(id));
        Mockito.verify(userRepository).findById(id);
    }

    @Test
    void getByUsername() {
        String userName = "userName";
        User user = new User();
        user.setUsername(userName);
        Mockito.when(userRepository.findByUsername(userName))
                .thenReturn(Optional.of(user));
        User testUser = userService.getByUsername(userName);
        Mockito.verify(userRepository).findByUsername(userName);
        Assertions.assertEquals(user, testUser);
    }

    @Test
    void getByUsernameWithNotExistingUsername() {
        String userName = "userName";
        Mockito.when(userRepository.findByUsername(userName))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.getByUsername(userName));
        Mockito.verify(userRepository).findByUsername(userName);
    }

    @Test
    void update() {
        String password = "password";
        User user = new User();
        user.setPassword(password);
        userService.update(user);
        Mockito.verify(passwordEncoder).encode(password);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void create() {
        String password = "password";
        String username = "username";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPasswordConfirmation(password);
        Mockito.when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());
        User testUser = userService.create(user);
        Mockito.verify(passwordEncoder).encode(password);
        Mockito.verify(userRepository).save(user);
        Assertions.assertEquals(Set.of(Role.ROLE_USER),
                testUser.getRoles());
    }

    @Test
    void createWithExistingUser() {
        String username = "username";
        User user = new User();
        user.setUsername(username);
        Mockito.when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
        Assertions.assertThrows(IllegalStateException.class,
                () -> userService.create(user));
    }

    @Test
    void createWithPasswordAndPasswordConfirmDoNotMatch() {
        String password = "password";
        String passwordConfirm = "passwordConfirm";
        User user = new User();
        user.setPassword(password);
        user.setPassword(passwordConfirm);
        Assertions.assertThrows(IllegalStateException.class,
                () -> userService.create(user));
    }

    @Test
    void isTaskOwner() {
        Long userId = 1L;
        Long taskId = 1L;
        Mockito.when(userRepository.isTaskOwner(userId, taskId))
                .thenReturn(true);
        boolean isOwner = userService.isTaskOwner(userId, taskId);
        Mockito.verify(userRepository).isTaskOwner(userId, taskId);
        Assertions.assertTrue(isOwner);
    }

    @Test
    void delete() {
        Long userId = 1L;
        userService.delete(userId);
        Mockito.verify(userRepository).deleteById(userId);
    }
}
