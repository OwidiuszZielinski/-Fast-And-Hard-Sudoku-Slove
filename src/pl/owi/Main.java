package pl.owi;

import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SudokuSlover sudokuSlover = new SudokuSlover();



        while (true) {

            System.out.println("==== SUDOKU DESTROYER ====");


            System.out.println("1.Rozwiaz");
            System.out.println("2.Zakoncz");
        int destroy = scanner.nextInt();
            switch (destroy) {

                case 1 :
                    sudokuSlover.sudokuDestroy();
                break;

                case 2 :
                    System.out.println("==== Program zakonczony ====");
                    System.exit(0);

                    break;


            }
        }

    }
}
