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
                getListRet("C:\\", new StringBuffer()).toString().getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING);

    }

    public static StringBuffer getListRet(String path, StringBuffer sb) {
        File[] listOfFiles = new File(path).listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    sb.append("\"" + listOfFiles[i].getPath() + "\"" + "," + "\"" + listOfFiles[i].length() + "\"" + "," + "\"" + listOfFiles[i].lastModified() + "\"" + "\n");
                    System.out.println(listOfFiles[i].getName());
                } else if (listOfFiles[i].isDirectory()) {
                    getListRet(listOfFiles[i].getPath(), sb);
                }
            }
        }
        return sb;
    }
}