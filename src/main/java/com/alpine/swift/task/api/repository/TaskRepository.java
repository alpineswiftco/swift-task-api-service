package com.alpine.swift.task.api.repository;

import com.alpine.swift.task.api.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task,String> {

    List<Task> findByUserId(String userId) ;

}
