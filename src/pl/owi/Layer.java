package pl.owi;

import java.util.ArrayList;
import java.util.Arrays;

public class Layer {
    Cords cords;
    int[][] sudoku;
    ArrayList<Integer> possible;
    public Layer(Cords cords, int[][] sudoku, ArrayList<Integer> possible) {
        this.cords = cords;
        this.sudoku = copyArray(sudoku);
        this.possible = possible;
    }
    public int[][] copyArray(int[][] array) {
        int[][] result = new int[array.length][];
        for (int row = 0; row < array.length; row++) {
            result[row] = Arrays.copyOf(array[row], array[row].length);
        }
        return result;
    }
    public int[][] getSudoku() {
        return sudoku;
    }
    public ArrayList<Integer> getPossible() {
        return possible;
    }
}
