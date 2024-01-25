package com.example.task_manager.controller;

import com.example.task_manager.dto.CreateTaskDTO;
import com.example.task_manager.dto.ErrorResponseDto;
import com.example.task_manager.dto.TaskResponseDTO;
import com.example.task_manager.dto.UpdateTaskDTO;
import com.example.task_manager.entity.TaskEntity;
import com.example.task_manager.service.NoteService;
import com.example.task_manager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NoteService noteService;
    private ModelMapper modelMapper = new ModelMapper();

    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTasksById(@PathVariable("id") Integer id){
        var task = taskService.getTasksById(id);
        var note = noteService.getNotesForTasks(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(note);
        return ResponseEntity.ok(taskResponse);
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTasks(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        return ResponseEntity.ok(task);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTasks(@PathVariable("id") Integer id,@RequestBody UpdateTaskDTO body) throws ParseException{
        var task = taskService.updateTasks(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> HandlesError(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid Data format"));
        }
        return ResponseEntity.badRequest().body(new ErrorResponseDto("Internal Server Error"));
    }
}
