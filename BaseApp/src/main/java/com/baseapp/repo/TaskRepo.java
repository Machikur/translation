package com.baseapp.repo;

import com.baseapp.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {

    List<Task> findAllByCreatedTimeAfter(LocalDateTime createdTime);
}
