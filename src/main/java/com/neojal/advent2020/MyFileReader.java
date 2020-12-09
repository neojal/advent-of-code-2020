package com.neojal.advent2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MyFileReader {

    private Path path;

    public MyFileReader(String inputPath) {
        this.path = new File(inputPath).toPath();
    }

    /**
     *
     * @return an int[] array from the input file.
     */
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

    /**
     *
     * @return a long[] array from the input file.
     */
    public long[] getLongArray()
    {
        try
        {
            long[] array = Files.lines(path).
                    mapToLong(Long::parseLong).
                    toArray();
            return array;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return an ArrayList whose each element is a line from the input file.
     */
    public ArrayList<String> getLinesAsList() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            lines = (ArrayList) Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}