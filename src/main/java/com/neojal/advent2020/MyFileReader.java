package com.neojal.advent2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileReader {

    public static int[] getIntArray(String inputPath)
    {
        Path path = new File(inputPath).toPath();
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