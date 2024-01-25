package com.example.task_manager.dto;

import com.example.task_manager.entity.NoteEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
    private int taskId;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NoteEntity> notes;
}
