package com.example.task_manager.service;

import com.example.task_manager.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>;
    private int taskId = 1;
    public TaskEntity addTask(String title, String description, String deadline){
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        //task.setDeadline(new Date(deadline)); //TODO validate data format YYYY-MM-DD
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }
    public TaskEntity getTasksById(int id){
        for(TaskEntity task:tasks){
            if(task.getTaskId() == id){
                return task;
            }
        }
        return null;
    }
}
