package tech.codingclub.songfetcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FileUtility {
    public static void createFile(String fileNameWithPath) {
        try {
            File file = new File(fileNameWithPath);
            if (file.createNewFile()) {
                System.out.println("File created : " + file.getName());
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readAndPrintFile(String fileName) {
        Scanner scanner = null;
        ArrayList<String> content = new ArrayList<>();
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Some error occurred!");
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
        return content;
    }

    public static void writeToFile(String fileNameWithPath, String content) {
        FileWriter fw = null;
        try {
            File file = new File(fileNameWithPath);
            if (!file.createNewFile()) {
                createFile(fileNameWithPath);
            }
            fw = new FileWriter(file);
            System.out.println("Adding content in the file!");
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void appendToFIle(String fileNameWithPath, String content) {
        try {
            FileWriter fileWriter = new FileWriter(fileNameWithPath, true);
            fileWriter.append("\n");
            fileWriter.append(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("This is Ayush Aggarwal. FileUtility running at " + new Date().toString() + " sharp.");
        String nameOfFileCreated = "C:\\Users\\AyushKartik\\Desktop\\JAVA\\TechCodingMafia\\data\\practice\\file" + "\\create-file.txt";
        createFile(nameOfFileCreated);
        ArrayList<String> allFileContent = readAndPrintFile(nameOfFileCreated);
        for (String i : allFileContent)
            System.out.println(i);
        System.out.println("Total number of lines in the file are : " + allFileContent.size());
        nameOfFileCreated = "C:\\Users\\AyushKartik\\Desktop\\JAVA\\TechCodingMafia\\data\\practice\\file" + "\\create-file-new.txt";
        writeToFile(nameOfFileCreated, "############\n" +
                "Hello There!" +
                "\n############");
        allFileContent = readAndPrintFile(nameOfFileCreated);
        for (String i : allFileContent)
            System.out.println(i);
        System.out.println("Total number of lines in the new file : " + allFileContent.size());
        for (int i = 0; i < 100; i++) {
            String append = i + " ";
            appendToFIle(nameOfFileCreated, append);
        }
        allFileContent = readAndPrintFile(nameOfFileCreated);
        System.out.println("Appended file length : " + allFileContent.size());
    }
}
