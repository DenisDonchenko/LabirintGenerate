package com.example.springlabirints.controller;


import com.example.springlabirints.labyrinthAlgoritm.FileToArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.swing.text.Document;
import java.io.*;
import java.nio.Buffer;

@Controller
@RequestMapping("/")
public class IndexController {



    @GetMapping
    public String index(){
        return "index";
    }
    @PostMapping("/uploadFile")
    public String uploudFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (!file.isEmpty()) {
            try {
                String nameFile = "myFile.txt";
                File f = new File(nameFile);
                if(f.exists()){ f.createNewFile();}
                FileWriter fileWriter = new FileWriter(f);

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(file.getName() + "-uploaded")));
                stream.write(bytes);

                for(byte b: bytes){
                    fileWriter.write((char)b);
                }

                fileWriter.close();
                stream.close();
                 FileToArray fileToArray = new FileToArray(nameFile);
                fileToArray.printLabirint();
                model.addAttribute("arr",fileToArray.getArr());
            } catch (Exception e) {
                System.out.println("Bad" + e.getMessage());
            }
        }
        else {
            System.out.println( "File is empty");
        }
        return "redirect:/";
    }

}
