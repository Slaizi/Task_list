package ru.Bogachev.task_list.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.Bogachev.task_list.config.TestConfig;
import ru.Bogachev.task_list.domain.exception.ResourceNotFoundException;
import ru.Bogachev.task_list.domain.task.Status;
import ru.Bogachev.task_list.domain.task.Task;
import ru.Bogachev.task_list.domain.task.TaskImage;
import ru.Bogachev.task_list.repository.TaskRepository;
import ru.Bogachev.task_list.service.ImageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private ImageService imageService;

    @Autowired
    private TaskServiceImpl taskService;

    @Test
    void getById() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        Mockito.when(taskRepository.findById(id))
                .thenReturn(Optional.of(task));
        Task testTask = taskService.getById(id);
        Mockito.verify(taskRepository).findById(id);
        Assertions.assertEquals(task, testTask);
    }

    @Test
    void getByIdWithNotExistingId() {
        Long id = 1L;
        Mockito.when(taskRepository.findById(id))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> taskService.getById(id));
        Mockito.verify(taskRepository).findById(id);
    }

    @Test
    void getAllByUserId() {
        Long userId = 1L;
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tasks.add(new Task());
        }
        Mockito.when(taskRepository.findAllByUserId(userId))
                .thenReturn(tasks);
        List<Task> testTask = taskService.getAllByUserId(userId);
        Mockito.verify(taskRepository).findAllByUserId(userId);
        Assertions.assertEquals(tasks, testTask);
    }

    @Test
    void update() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("title");
        task.setDescription("description");
        task.setExpirationDate(LocalDateTime.now());
        task.setStatus(Status.DONE);

        Task testTask = taskService.update(task);
        Mockito.verify(taskRepository,
                Mockito.times(1))
                .save(task);
        Assertions.assertEquals(task, testTask);
    }

    @Test
    void updateWithNullStatus() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("title");
        task.setDescription("description");
        task.setExpirationDate(LocalDateTime.now());

        Task testTask = taskService.update(task);
        Mockito.verify(taskRepository,
                Mockito.times(1))
                .save(task);
        Assertions.assertEquals(Status.TODO, testTask.getStatus());
    }

    @Test
    void create() {
        Long taskId = 1L;
        Long userId = 1L;
        Task task = new Task();
        Mockito.doAnswer(invocationOnMock -> {
            Task savedTask = invocationOnMock.getArgument(0);
            savedTask.setId(taskId);
            return savedTask;
        }).when(taskRepository).save(task);

        Task testTask = taskService.create(task, userId);
        Mockito.verify(taskRepository).save(task);
        Assertions.assertNotNull(testTask.getId());
        Mockito.verify(taskRepository,
                Mockito.times(1))
                .assignTask(userId, task.getId());
    }

    @Test
    void createWithNotNullStatus() {
        Long userId = 1L;
        Task task = new Task();
        task.setStatus(Status.DONE);

        Task testTask = taskService.create(task, userId);
        Mockito.verify(taskRepository).save(task);
        Mockito.verify(taskRepository,
                Mockito.times(1))
                .assignTask(userId, task.getId());
        Assertions.assertEquals(Status.TODO, testTask.getStatus());
    }

    @Test
    void delete() {
        Long taskId = 1L;
        taskService.delete(taskId);
        Mockito.verify(taskRepository).deleteById(taskId);
    }

    @Test
    void uploadImage() {
        Long taskId = 1L;
        String imageName = "imageName";
        TaskImage taskImage = new TaskImage();
        Mockito.when(imageService.upload(taskImage))
                .thenReturn(imageName);
        taskService.uploadImage(taskId, taskImage);
        Mockito.verify(taskRepository).addImage(taskId, imageName);
    }
}
