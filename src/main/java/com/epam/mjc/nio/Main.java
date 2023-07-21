package com.epam.mjc.nio;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File file = new File("src/main/resources/Profile.txt");
        FileReader fileReader = new FileReader();
        fileReader.getDataFromFile(file);
    }
}
