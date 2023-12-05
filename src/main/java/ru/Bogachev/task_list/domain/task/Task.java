package ru.Bogachev.task_list.domain.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class Task {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime expirationDate;

}
