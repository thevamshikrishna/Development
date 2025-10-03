package src.main.java.com.atyeti.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {

    public List<String> readFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + file.getName());
            e.printStackTrace();
        }
        return lines;
    }
}

