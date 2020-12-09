package com.neojal.advent2020.day08;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Handheld {

    /**
     * represents a line of code of the bootCode
     */
    @Getter @Setter @AllArgsConstructor
    private static class CodeLine {
        private String instruction;
        private Integer value;
    }

    private String bootCodeInputFile;
    private ArrayList<CodeLine> code = new ArrayList<>();

    public Handheld(String bootCodeInputFile) throws FileNotFoundException {
        this.bootCodeInputFile = bootCodeInputFile;
        setBootCodeFromInputFile();
    }

    private void setBootCodeFromInputFile() throws FileNotFoundException {
        Scanner scanner = new Scanner( new File( bootCodeInputFile ) );
        while ( scanner.hasNextLine()) {
            code.add( new CodeLine(scanner.next(), scanner.nextInt()) );
        }
    }


    /**
     *
     * @return accumulator value. Part 1 of day 8 quiz.
     */
    public int getValueOfAccumulator() {

        int accumulator = 0;

        for (int i = 0; code.get(i).getValue() != null; i++ ) {

            int value = code.get(i).getValue();
            switch (code.get(i).getInstruction()) {

                case "acc":
                    accumulator += value;
                    break;

                case "jmp":
                    i = --i + value;

                    break;
                case "nop":
                    break;
            }
            code.get(i).setValue( null );
        }

        return accumulator;
    }

    /**
     *
     * @return value of accumulator at the end of the fixed program. Part2 of day8.
     */
    public Integer getValueOfAccumulatorFixed() {

        return getValueOfAccumulatorFixed(code, 0, false);
    }

    /**
     *
     * @param fixedCode
     * @param position
     * @param isFixed
     * @return the total accumulator after fixing the code. The alternative path required a deep copy of fixedCode.
     */
    private Integer getValueOfAccumulatorFixed( ArrayList<CodeLine> fixedCode, Integer position, Boolean isFixed ) {

        Integer accumulator = 0;
        for (int i = position; i != fixedCode.size(); i++ ) {
            String instruction = fixedCode.get(i).getInstruction();
            Integer value = fixedCode.get(i).getValue();

            // return null if...
            if( value == null ||
                    (instruction.equals("jmp") && value == 0) ) {
                return null;
            }

            // lets try a fix!
            if ( !instruction.equals("acc") && !isFixed ) {

                // flips jmp<->nop
                fixedCode.get(i).setInstruction( instruction.equals("jmp") ? "nop" : "jmp" );

                ArrayList<CodeLine> copyFixedCode = deepCopyArrayList( fixedCode );
                Integer newAccumulator = getValueOfAccumulatorFixed(copyFixedCode, i, true );

                // gotcha! adds the fixed path with the current one.
                if ( newAccumulator != null) {
                    return accumulator + newAccumulator;
                }
                // unfix and continues in the original path
                else {
                    fixedCode.get(i).setInstruction( instruction.equals("jmp") ? "nop" : "jmp" );
                }
            }

            fixedCode.get(i).setValue( null );
            switch ( instruction ) {

                case "acc":
                    accumulator += value;
                    break;

                case "jmp":
                    i = --i + value;

                    // return null if...
                    if ( i <= 0 )
                        return null;

                    break;
                case "nop":
                    break;
            }
        }
        return accumulator;
    }

    /**
     *
     * @param fixedCode
     * @return a deep-copy of the fixedCode
     */
    private ArrayList<CodeLine> deepCopyArrayList(ArrayList<CodeLine> fixedCode) {
        ArrayList<CodeLine> copy = new ArrayList<>(fixedCode.size());

        for (CodeLine codeLine : fixedCode) {

            copy.add( new CodeLine(codeLine.getInstruction(), codeLine.getValue()) );
        }
        return copy;
    }
}
