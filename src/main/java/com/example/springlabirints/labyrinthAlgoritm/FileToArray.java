package com.example.springlabirints.labyrinthAlgoritm;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FileToArray {
    private int x;
    private int y;
    private int[][] arr;
    private String path;

    public FileToArray(String path) throws IOException {
        this.path = path;
        fileToArr();
        arr[x][y] = 2;
    }

    public void printLabirint() {

        System.out.println("[" + x + ";" + y + "]");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void readCoordinates() throws IOException {
        FileReader fileReader = new FileReader(path);
        Scanner scn = new Scanner(fileReader);
        x = scn.nextInt();
        y = scn.nextInt();
        fileReader.close();
        scn.close();
    }

    private void fileToArr() throws IOException {
        readCoordinates();
        FileReader file = new FileReader(path);
        Scanner scn = new Scanner(file);
        ArrayList<String[]> nums = new ArrayList<>();
        while (scn.hasNextLine()) {
            String str = scn.nextLine();
            if (str.length() > 3) {
                nums.add(str.split(" "));
            }
        }
        int columns = nums.get(0).length;
        arr = new int[nums.size()][columns];
        Iterator<String[]> iter = nums.iterator();
        for (int i = 0; i < arr.length; i++) {
            String[] s = iter.next();
            for (int j = 0; j < columns; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        scn.close();
    }

}
