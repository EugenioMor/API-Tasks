package com.APItasks.service;

import com.APItasks.model.Task;
import com.APItasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> createTask(Task task) {
        try {
            taskRepository.save(task);
            return new ResponseEntity<Task>(HttpStatus.CREATED);

        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> updateTask(Task task, Long id) {
        try {
            Task existingTask = this.getTaskById(id);

            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            existingTask.setCreation_date(task.getCreation_date());
            existingTask.setStatus(task.getStatus());

            this.createTask(existingTask);
            return new ResponseEntity<Task>(HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
