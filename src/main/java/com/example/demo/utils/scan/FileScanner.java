package com.example.demo.utils.scan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class FileScanner {

    public static final String out = "C:\\Users\\j\\Documents\\out.csv";
    public static final String scanPath = "C:\\";

    public static void main(String[] args) throws IOException {
        clearfile("", out);
        getList(scanPath);
        //writefile("in", "C:\\Users\\j\\Documents\\out.txt");
    }

    public static void getList(String path) throws IOException {
        //  System.out.println("Hello jeetu!");
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {

                    Date date = new Date(listOfFiles[i].lastModified());

                    long fileSizeInBytes = listOfFiles[i].length();
                    // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                    //long fileSizeInKB = fileSizeInBytes / 1024;
                    // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                    //long fileSizeInMB = fileSizeInKB / 1024;

                    // System.out.println(listOfFiles[i].getName());
                    writefile("\""+listOfFiles[i].getPath()+"\"", out);
                    writefile("," + "\""+fileSizeInBytes+"\"", out);
                    writefile("," + "\""+date+"\"", out);
                    writefile("\n", out);

                    System.out.println(listOfFiles[i].getName() + ","  + fileSizeInBytes + ","  + date);

                } else if (listOfFiles[i].isDirectory()) {
                    //System.out.println("Directory " + listOfFiles[i].getName());
                    //System.out.println("Directory " + listOfFiles[i].getPath());
                    getList(listOfFiles[i].getPath());
                }
            }
        }
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
