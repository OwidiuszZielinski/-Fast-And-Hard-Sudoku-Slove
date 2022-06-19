package pl.owi;

import java.util.ArrayList;

public class Stack {


    public Stack(Cords cords, int[][] sudoku, ArrayList<Integer> possible) {

        this.cords = cords;
        this.sudoku = sudoku;
        this.possible = possible;
    }




    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public ArrayList<Integer> getPossible() {
        return possible;
    }

    public void setPossible(ArrayList<Integer> possible) {
        this.possible = possible;
    }

    public Cords getCords() {
        return cords;
    }

    public void setCords(Cords cords) {
        this.cords = cords;
    }

    Cords cords;
    int [][] sudoku;
    ArrayList<Integer> possible;


}
