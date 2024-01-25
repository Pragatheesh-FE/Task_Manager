package com.example.task_manager.dto;

import com.example.task_manager.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class createNoteResponseDTO {
    private Integer taskID;
    private NoteEntity note;
}
