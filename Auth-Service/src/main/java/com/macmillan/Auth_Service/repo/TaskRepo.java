package com.macmillan.Auth_Service.repo;


import com.macmillan.Auth_Service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {


    List<Task> findByCreatedBy(int userId);
}
