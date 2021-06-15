package com.maze.solver.repository;

import com.maze.solver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

     @Query("SELECT u FROM User u WHERE u.username = :username")
     User getUserByUsername(@Param("username") String username);

}