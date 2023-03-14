package com.example.Task.Management.controller;


import com.example.Task.Management.TaskRepository;
import com.example.Task.Management.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.table.TableStringConverter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;


    @PostMapping("/saveTask")
    public ResponseEntity<?> saveTask(@RequestBody Task task){
        return new ResponseEntity<>(taskRepository.save(task), HttpStatus.ACCEPTED);
    }

    @GetMapping("/allTasks")
    public ResponseEntity<?> allTasks(){
        return new ResponseEntity<>(taskRepository.findAll(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/deleteTask/{taskId}")
    public ResponseEntity<?> deleteById(@PathVariable String taskId){
        List<Task> allTask = (List<Task>) taskRepository.findAll();

        if(allTask !=null){
            for(Task task : allTask){
                if(task.getTaskId().equals(taskId)){
                    taskRepository.deleteById(task.getId());
                    return new ResponseEntity<>("Task with specified id got deleted",HttpStatus.ACCEPTED);
                }
            }
        }

        return  new ResponseEntity<>("Task with specified id not found",HttpStatus.ACCEPTED);
    }


    @GetMapping("/getTask/{holderName}")
    public ResponseEntity<?> deleteByHolderName(@PathVariable String holderName){
        List<Task> allTask = (List<Task>) taskRepository.findAll();

        if(allTask !=null){
            for(Task task : allTask){
                if(task.getTaskHolderName().equals(holderName)){
                    //taskRepository.deleteById(task.getId());
                    return new ResponseEntity<>(task,HttpStatus.ACCEPTED);
                }
            }
        }

        return  new ResponseEntity<>("Task with specified holder name not found",HttpStatus.ACCEPTED);
    }


    @GetMapping("/changeStatus/{taskId}")
    public ResponseEntity<?> changeStatus(@PathVariable String taskId){
        List<Task> allTask = (List<Task>) taskRepository.findAll();

        if(allTask !=null){
            for(Task task : allTask){
                if(task.getTaskId().equals(taskId)){
                    task.setTaskStatus("changed");
                    return new ResponseEntity<>(task,HttpStatus.ACCEPTED);
                }
            }
        }

        return  new ResponseEntity<>("Task with specified Task Id not found",HttpStatus.ACCEPTED);
    }

}
