package com.manulife.manulife.service;

import com.manulife.manulife.exception.TaskException;
import com.manulife.manulife.mapper.request.TaskRequest;
import com.manulife.manulife.mapper.response.TaskResponse;
import com.manulife.manulife.model.Task;
import com.manulife.manulife.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getTaskListTest() {
        Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(
                new Task("c5093154-d162-498a-9eb1-2481281c5f66", "name 1", "description 1", true),
                new Task("c5093154-d162-498a-9eb1-2481281c5f67", "name 2", "description 2", true),
                new Task("c5093154-d162-498a-9eb1-2481281c5f68", "name 3", "description 3", true)
        ));

        List<TaskResponse> allTask = taskService.getTaskList();

        Assert.assertEquals("c5093154-d162-498a-9eb1-2481281c5f66", allTask.get(0).getId());
        Assert.assertEquals("c5093154-d162-498a-9eb1-2481281c5f67", allTask.get(1).getId());
        Assert.assertEquals("c5093154-d162-498a-9eb1-2481281c5f68", allTask.get(2).getId());
    }

    @Test
    public void getTaskByIdTest() throws TaskException {
        Mockito.when(taskRepository.findById("c5093154-d162-498a-9eb1-2481281c5f66")).thenReturn(
                Optional.of(new Task("c5093154-d162-498a-9eb1-2481281c5f66", "name 1", "description 1", true))
        );

        TaskResponse task = taskService.getTaskById("c5093154-d162-498a-9eb1-2481281c5f66");

        Assert.assertEquals("c5093154-d162-498a-9eb1-2481281c5f66", task.getId());
    }

    @Test
    public void addNewTaskTest() throws TaskException {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("name 1");
        taskRequest.setDescription("description 1");
        taskRequest.setStatus(true);
        Task task = new Task("c5093154-d162-498a-9eb1-2481281c5f66", "name 1", "description 1", true);

        Mockito.when(taskRepository.save(ArgumentMatchers.any(Task.class))).thenReturn(task);

        TaskResponse savedTask = taskService.addNewTask(taskRequest);

        Assert.assertEquals(task.getName(), savedTask.getName());
    }

    @Test
    public void updateTaskTest() throws TaskException {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("name 1");
        taskRequest.setDescription("description 1");
        taskRequest.setStatus(true);
        Task task = new Task("c5093154-d162-498a-9eb1-2481281c5f66", "name 1", "description 1", true);

        Mockito.when(taskRepository.findById("c5093154-d162-498a-9eb1-2481281c5f66")).thenReturn(Optional.of(task));
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        TaskResponse taskUpdated = taskService.updateTask(task.getId(), taskRequest);

        Assert.assertEquals(task.getId(), taskUpdated.getId());
    }

    @Test
    public void deleteTaskTest() throws TaskException {
        Task task = new Task("c5093154-d162-498a-9eb1-2481281c5f66", "name 1", "description 1", true);

        Mockito.when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        taskService.deleteTask(task.getId());

        Mockito.verify(taskRepository).deleteById(task.getId());
    }
}
