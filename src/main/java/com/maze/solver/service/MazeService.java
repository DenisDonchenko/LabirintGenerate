package com.maze.solver.service;

import com.maze.solver.entity.Maze;
import com.maze.solver.repository.FileUploudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MazeService {

    @Autowired
    private FileUploudRepository fileUploudRepository;

    public void saveMaze(Maze maze){
        fileUploudRepository.save(maze);
    }
    public List<Maze> showMazeByIdUSer(Long id){
        return fileUploudRepository.getMazeByUser(id);
    }

}
