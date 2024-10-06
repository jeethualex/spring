package com.example.demo.utils.scan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileScanner {

    public static final String out = "C:\\Users\\j\\Documents\\out.csv";
    public static final String scanPath = "C:\\";

    public static void main(String[] args) throws IOException {

        clearfile("", out);
        writefile(getListRet(scanPath, new StringBuffer()).toString(), out);
    }

    public static StringBuffer getListRet(String path, StringBuffer sb) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {

                    sb.append("\""+listOfFiles[i].getPath()+"\"" + "," + "\""+listOfFiles[i].length()+"\"" + "," + "\""+listOfFiles[i].lastModified()+"\"" + "\n");

                    System.out.println(listOfFiles[i].getName());

                } else if (listOfFiles[i].isDirectory()) {

                    getListRet(listOfFiles[i].getPath(),sb);
                }
            }
        }
        return sb;
    }

    public static void writefile(String content, String path) throws IOException {


        Files.write(
                Paths.get(path),
                content.getBytes(),
                StandardOpenOption.APPEND);
    }

    public static void clearfile(String content, String path) throws IOException {


        Files.write(
                Paths.get(path),
                content.getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
