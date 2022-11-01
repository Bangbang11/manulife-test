package com.manulife.manulife.controller;

import com.manulife.manulife._enum.StatusCodeEnum;
import com.manulife.manulife.exception.TaskException;
import com.manulife.manulife.mapper.request.TaskRequest;
import com.manulife.manulife.mapper.response.BaseErrorMessage;
import com.manulife.manulife.mapper.response.BaseResponse;
import com.manulife.manulife.mapper.response.TaskResponse;
import com.manulife.manulife.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/GetTaskList")
    public ResponseEntity getList() {
        BaseResponse<List<TaskResponse>> baseResponseMessage = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();

        try {
            List<TaskResponse> result = taskService.getTaskList();
            baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.OK);
            baseResponseMessage.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponseMessage.getErrorMessages()
                .add(BaseErrorMessage.builder().field("Task List").message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseMessage);
    }

    @GetMapping("/GetTask/{id}")
    public ResponseEntity getTaskById(@PathVariable("id") String id) {
        BaseResponse<TaskResponse> baseResponseMessage = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();

        try {
            TaskResponse result = taskService.getTaskById(id);
            baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.OK);
            baseResponseMessage.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponseMessage);
        } catch (TaskException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponseMessage.getErrorMessages()
                .add(BaseErrorMessage.builder().field("Task By Id").message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseMessage);
    }

    @PostMapping("/newTask")
    public ResponseEntity newTask(@RequestBody TaskRequest request) {
        BaseResponse<TaskResponse> baseResponseMessage = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();

        try {
            TaskResponse result = taskService.addNewTask(request);
            baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.OK);
            baseResponseMessage.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponseMessage);
        } catch (TaskException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponseMessage.getErrorMessages()
                .add(BaseErrorMessage.builder().field("Add New Task").message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseMessage);
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity updateTask(@PathVariable("id") String id,  @RequestBody TaskRequest request) {
        BaseResponse<TaskResponse> baseResponseMessage = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();

        try {
            TaskResponse result = taskService.updateTask(id, request);
            baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.OK);
            baseResponseMessage.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponseMessage);
        } catch (TaskException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponseMessage.getErrorMessages()
                .add(BaseErrorMessage.builder().field("Update Task By Id").message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseMessage);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") String id) {
        BaseResponse<TaskResponse> baseResponseMessage = new BaseResponse<>();
        String errorMessage = StatusCodeEnum.ERROR.getDescription();

        try {
            taskService.deleteTask(id);
            baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.OK);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponseMessage);
        } catch (TaskException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponseMessage.setStatusCodeByEnum(StatusCodeEnum.BAD_REQUEST);
        baseResponseMessage.getErrorMessages()
                .add(BaseErrorMessage.builder().field("Delete Task By Id").message(errorMessage).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseMessage);
    }
}
