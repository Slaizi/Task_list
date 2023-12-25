package ru.Bogachev.task_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class TaskListApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TaskListApplication.class, args);
    }
}
