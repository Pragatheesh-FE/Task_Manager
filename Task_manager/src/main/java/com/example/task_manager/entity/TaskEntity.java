package com.example.task_manager.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskEntity {
    private int taskId;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
}
