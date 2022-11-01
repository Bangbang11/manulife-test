package com.manulife.manulife.service;

import com.manulife.manulife.exception.TaskException;
import com.manulife.manulife.mapper.request.TaskRequest;
import com.manulife.manulife.mapper.response.TaskResponse;
import com.manulife.manulife.model.Task;
import com.manulife.manulife.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponse> getTaskList() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : taskList) {
            taskResponses.add(new TaskResponse(task));
        }
        return taskResponses;
    }

    public TaskResponse getTaskById(String id) throws TaskException {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskException("Task not found");
        }
        return new TaskResponse(task);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TaskResponse addNewTask(TaskRequest request) throws TaskException {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new TaskException("Name required");
        }

        Task task = new Task();
        task.setId(String.valueOf(UUID.randomUUID()));
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task = taskRepository.save(task);

        return new TaskResponse(task);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TaskResponse updateTask(String id, TaskRequest request) throws TaskException {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new TaskException("Name required");
        }

        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskException("Task not found");
        }
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task = taskRepository.save(task);

        return new TaskResponse(task);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTask(String id) throws TaskException {

        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
