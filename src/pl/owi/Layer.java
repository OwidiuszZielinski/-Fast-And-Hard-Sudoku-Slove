package pl.owi;

import java.util.ArrayList;
import java.util.Arrays;

public class Layer {


    public Layer(Cords cords, int[][] sudoku, ArrayList<Integer> possible) {

        this.cords = cords;
        this.sudoku = copyArray(sudoku);
        this.possible = possible;
    }


    private int[][] copyArray(int[][] array){
        int[][] result = new int[array.length][];
        for (int row = 0; row<array.length; row++) {
            result[row] = Arrays.copyOf(array[row],array[row].length);
        }
        return result;
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
