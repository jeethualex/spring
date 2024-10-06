package com.example.demo.utils.scan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileScanner {

    public static final String out = "C:\\Users\\j\\Documents\\out.csv";
    public static final String scanPath = "C:\\";

    public static StringBuffer sb;

    public static void main(String[] args) throws IOException {

        clearfile("", out);
        sb = getListRet(scanPath, new StringBuffer());
        //getList(scanPath);
        writefile(sb.toString(), out);
        //writefile("in", "C:\\Users\\j\\Documents\\out.txt");
    }

    public static void getList(String path) throws IOException {
        //  System.out.println("Hello jeetu!");
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {

                    //Date date = new Date(listOfFiles[i].lastModified());

                    //long fileSizeInBytes = listOfFiles[i].length();
                    // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                    //long fileSizeInKB = fileSizeInBytes / 1024;
                    // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                    //long fileSizeInMB = fileSizeInKB / 1024;

                    //String outString = "\""+listOfFiles[i].getPath()+"\"" + "," + "\""+listOfFiles[i].length()+"\"" + "," + "\""+listOfFiles[i].lastModified()+"\"" + "\n";

                    // System.out.println(listOfFiles[i].getName());
                    //writefile("\""+listOfFiles[i].getPath()+"\"" + "," + "\""+listOfFiles[i].length()+"\"" + "," + "\""+listOfFiles[i].lastModified()+"\"" + "\n", out);
                    sb.append("\""+listOfFiles[i].getPath()+"\"" + "," + "\""+listOfFiles[i].length()+"\"" + "," + "\""+listOfFiles[i].lastModified()+"\"" + "\n");

                    System.out.println(listOfFiles[i].getName());

                    //System.out.println(writefileret("\""+listOfFiles[i].getPath()+"\"" + "," + "\""+listOfFiles[i].length()+"\"" + "," + "\""+listOfFiles[i].lastModified()+"\"" + "\n", out));

                } else if (listOfFiles[i].isDirectory()) {
                    //System.out.println("Directory " + listOfFiles[i].getName());
                    //System.out.println("Directory " + listOfFiles[i].getPath());
                    getList(listOfFiles[i].getPath());
                }
            }
        }
    }

    public static StringBuffer getListRet(String path, StringBuffer sb) throws IOException {
        //  System.out.println("Hello jeetu!");
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
