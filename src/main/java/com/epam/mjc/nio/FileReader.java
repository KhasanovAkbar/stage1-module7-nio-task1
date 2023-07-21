package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {

        StringBuilder content = new StringBuilder();
        Profile profile = new Profile();
        try {
            RandomAccessFile rFile = new RandomAccessFile(file, "r");
            FileChannel channel = rFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(256);
            int bytesRead = channel.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    content.append((char) buffer.get());
                }
                buffer.clear();
                bytesRead = channel.read(buffer);
            }
            rFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String profileLines = content.toString();
        String[] profileArr = profileLines.split("\n");
        for (String line : profileArr) {
            String[] split = line.split(" ");
            switch (split[0]) {
                case "Name:":
                    profile.setName(split[1]);
                    break;
                case "Age:":
                    profile.setAge(Integer.parseInt(split[1]));
                    break;
                case "Email:":
                    profile.setEmail(split[1]);
                    break;
                case "Phone:":
                    profile.setPhone(Long.parseLong(split[1]));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        return profile;
    }
}
