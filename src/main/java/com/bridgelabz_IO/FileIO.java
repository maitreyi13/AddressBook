package com.bridgelabz_IO;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileIO {
    public void writeData(Map<String, AddressBook> addressBook) {
        File file = new File("C:\\Users\\shree\\Desktop\\Bridgelabz\\JAVA\\Day29 Assignments\\AddressBook_IO\\src\\main\\java\\com\\bridgelabz_IO\\AddressBook.java");
        BufferedWriter bw ;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, AddressBook> entry : addressBook.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
            bw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}