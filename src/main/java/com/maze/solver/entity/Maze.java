    package com.maze.solver.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="mazes_file")
public class Maze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "file")
    private byte[] maze;
    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User user;


    public Maze() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getMaze() {
        return maze;
    }

    public void setMaze(byte[] maze) {
        this.maze = maze;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Maze{" +
                "id=" + id +
                ", maze=" + Arrays.toString(maze) +
                ", user=" + user +
                '}';
    }
}
