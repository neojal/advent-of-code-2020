package com.neojal.advent2020.day03;

import java.io.*;

public class TobogganTrajectory {

    /**
     *
     * @param filePath
     * @param moveX
     * @param moveY
     * @return the number of trees (#) found in the trajectory described by moveX and moveY over filePath
     */
    public int getTreesFirstTrajectory(String filePath, int moveX, int moveY) {
        int treeCounter = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            int indexX = 0;
            int indexY = 0;

            String line = reader.readLine();
            int lineLength = line.length();

            while((line = reader.readLine()) != null) {

                if (++indexY % moveY != 0) {
                    continue;
                }
                else {
                    indexX = (lineLength % (indexX + moveX + 1) < lineLength )? indexX + moveX :
                            (indexX + moveX) - (lineLength);

                    if(line.charAt(indexX) == '#') {
                        treeCounter ++;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return treeCounter;
    }

    /**
     *
     * @param filePath
     * @param trajectories
     * @return the multiplication of the trees found on all the trajectories described by the trajectories array
     */
    public long getTreesMultipleTrajectories(String filePath, int[][] trajectories) {
        long treeCounter = 1;

        System.out.println("trajectories lenght: "+ trajectories.length);

        for (int i = 0; i < trajectories.length; i++) {

            treeCounter *= getTreesFirstTrajectory(filePath, trajectories[i][0], trajectories[i][1]);
        }
        return treeCounter;
    }
}
