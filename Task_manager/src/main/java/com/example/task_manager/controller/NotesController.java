package com.example.task_manager.controller;

import com.example.task_manager.dto.CreateNoteDTO;
import com.example.task_manager.dto.createNoteResponseDTO;
import com.example.task_manager.entity.NoteEntity;
import com.example.task_manager.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable ("taskId") Integer taskId){
        var notes = noteService.getNotesForTasks(taskId);
        return ResponseEntity.ok(notes);
    }
    @PostMapping("")
    public ResponseEntity<createNoteResponseDTO> addNotes(
            @PathVariable ("taskId") Integer taskId,
            @RequestBody CreateNoteDTO body){
        var note = noteService.addNoteForTask(taskId,body.getTitle(), body.getBody() );
        return ResponseEntity.ok(new createNoteResponseDTO(taskId, note));
    }
}
