package com.maze.solver.repository;

import com.maze.solver.entity.Maze;
import com.maze.solver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FileUploudRepository extends CrudRepository<Maze,Long> {

/*
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);*/

    @Query("SELECT m FROM Maze m where m.user.id=:id")
    List<Maze> getMazeByUser(@Param("id") Long id);
    }
