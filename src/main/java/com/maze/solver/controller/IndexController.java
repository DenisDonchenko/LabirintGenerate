package com.maze.solver.controller;

import com.maze.solver.algorithm.readFile.FileToArray;
import com.maze.solver.algorithm.maze.MazeSolver;
import com.maze.solver.entity.Maze;
import com.maze.solver.entity.User;
import com.maze.solver.service.MazeService;
import com.maze.solver.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MazeService mazeService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private String errorName = "";


    @GetMapping()
    public String index(Model model){
        MazeSolver mazeSolver = new MazeSolver();
        model.addAttribute("errorString",errorName);
        return "index";
    }

    public void readByte(byte[] buffer ) throws IOException {
        try(FileOutputStream fos=new FileOutputStream("myFile.txt"))
        {
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");
    }

    @PostMapping("/myMaze")
    public String myMaze(@RequestParam("nameUser") String name, Model model) throws IOException {
        User user = userDetailsService.getUser(name);
        List<Maze> arr= mazeService.showMazeByIdUSer(user.getId());
        for (Maze maze : arr){
        }

        return "mazeUser";

    }
    @PostMapping("/uploadFile")
    public String uploudFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        MazeSolver mazeSolver = new MazeSolver();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Maze maze = new Maze();
        maze.setUser(userDetailsService.getUser(auth.getName()));
        if (!file.isEmpty()) {
            try {

                File f = new File("myFile.txt");
                if(f.exists()){ f.createNewFile();}
                FileWriter fileWriter = new FileWriter(f);

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                stream.write(bytes);

                for(byte b: bytes){
                    fileWriter.write((char)b);
                }
                maze.setMaze(file.getBytes());

                fileWriter.close();
                stream.close();
                FileToArray fileToArray = new FileToArray(file.getOriginalFilename());
                int arr[][]= mazeSolver.maze_solve(fileToArray.getArr(),fileToArray.getX(),fileToArray.getY());
                mazeService.saveMaze(maze);

                model.addAttribute("sizeMaze","Matrix("+arr.length+", "+arr[0].length+")");
                model.addAttribute("matrix",arr);
                model.addAttribute("inference",mazeSolver.getInference());

            } catch (Exception e) {
                errorName = "Bad - " + e.getMessage();
                return "redirect:/";
            }
        }
        else {
            errorName += "File is empty";
            return "redirect:/";
        }
        return "printArr";
    }



   /* public int[] convertByteArrayToIntArray(byte[] data) {
        if (data == null || data.length % 4 != 0) return null;
        // ----------
        int[] ints = new int[data.length / 4];
        for (int i = 0; i < ints.length; i++)
            ints[i] = ( convertByteArrayToInt(new byte[] {
                    data[(i*4)],
                    data[(i*4)+1],
                    data[(i*4)+2],
                    data[(i*4)+3],
            } ));
        return ints;
    }
    private byte[] convertIntArrayToByteArray(int[] data) {
        if (data == null) return null;
        // ----------
        byte[] byts = new byte[data.length * 4];
        for (int i = 0; i < data.length; i++)
            System.arraycopy(convertIntToByteArray(data[i]), 0, byts, i * 4, 4);
        return byts;
    }*/

}
