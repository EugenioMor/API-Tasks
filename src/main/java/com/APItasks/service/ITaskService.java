package com.APItasks.service;

import com.APItasks.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITaskService {

    public List<Task> getTasks();
    public Task getTaskById(Long id);
    public ResponseEntity<?> createTask(Task task);
    public ResponseEntity<?> updateTask(Task newTask, Long id);
    public void deleteTask(Long id);

}
