package com.alpine.swift.task.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tasks")
@Data
public class Task {
    @Id
    private String guid;
    private String title;
    private String description;
    private String status;
    private LocalDateTime deadline;
    private List<History> history;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    public static class History{
        private String status;
        private String changeBy;
        private LocalDateTime changedAt;

    }

}
