package pl.owi;

import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        SudokuSlover sudokuSlover = new SudokuSlover();
        double startTime = System.currentTimeMillis();
        sudokuSlover.sudokuDestroy();
        double endTime = System.currentTimeMillis();
        double totalTime = endTime - startTime;
        System.out.println("Execution time in sec  : " + totalTime / 1000 + "[s]");


    }
}


