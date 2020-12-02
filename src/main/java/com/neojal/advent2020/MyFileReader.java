package com.neojal.advent2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileReader {

    private Path path;

    public MyFileReader(String inputPath) {
        this.path = new File(inputPath).toPath();
    }

    public int[] getIntArray()
    {
        try
        {
            int[] array = Files.lines(path).
                        mapToInt(Integer::parseInt).
                        toArray();
            return array;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}