package com.example.demo.utils.scan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileScanner {
    public static void main(String[] args) throws IOException {
        Files.write(
                Paths.get("C:\\Users\\j\\Documents\\out.csv"),
                getList(new File("C:\\").listFiles(), new StringBuffer()).toString().getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static StringBuffer getList(File[] listOfFiles, StringBuffer sb) {
        if (listOfFiles != null)
            for (File i : listOfFiles)
                if (i.isDirectory()) getList(new File(i.getPath()).listFiles(), sb);
                else if (i.isFile()) {
                    sb.append("\"" + i.getPath() + "\"" + "," + "\"" + i.length() + "\"" + "," + "\"" + i.lastModified() + "\"" + "\n");
                    System.out.println(i.getName());
                }
        return sb;
    }
}

