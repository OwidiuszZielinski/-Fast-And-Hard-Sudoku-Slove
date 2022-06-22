package pl.owi;

import java.util.*;

public class SudokuSlover {


    int[][] sudoku = {
        {0, 2, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 6, 0, 0, 0, 0, 3},
        {0, 7, 4, 0, 8, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 3, 0, 0, 2},
        {0, 8, 0, 0, 4, 0, 0, 1, 0},
        {6, 0, 0, 5, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 7, 8, 0},
        {5, 0, 0, 0, 0, 9, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 4, 0},};

    int[][] refsudoku = {
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},};

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> cella) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (Integer x : cella) {
            if (!numbers.contains(x)) {
                numbers.add(x);
            }
        }
        return numbers;
    }

    public void displaysudoku() {

        System.out.println("   0 1 2   3 4 5   6 7 8");
        System.out.println("  ========================");
        for (int rows = 0; rows < 9; rows++) {
            System.out.print(rows + " |");
            for (int cols = 0; cols < 9; cols++) {
                System.out.print(sudoku[rows][cols] + " ");
                if ((cols + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((rows + 1) % 3 == 0) {
                System.out.println("  ========================");
            }
        }
        System.out.print("\n");
    }

    public ArrayList<Cords> emptycells(int[][] temp) {
        ArrayList<Cords> cords = new ArrayList<>();

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (temp[y][x] == 0) {
                    Cords cor = new Cords(x, y);
                    cords.add(cor);
                }
            }
        }
        return cords;
    }

    public ArrayList<Integer> checkrow(int y) {
        ArrayList<Integer> rownumbers = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            int number = sudoku[y][i];
            if (number != 0) {
                rownumbers.add(number);
            }
        }
        return rownumbers;
    }

    public ArrayList<Integer> checkcolumn(int x) {
        ArrayList<Integer> columnnumbers = new ArrayList<>();

        for (int y = 0; y < 9; y++) {
            int number = sudoku[y][x];
            if (number != 0) {
                columnnumbers.add(number);
            }
        }
        return columnnumbers;
    }

    public ArrayList<Integer> check_ref_square(int cor_X, int cor_Y) {
        ArrayList<Integer> check_ref_square_table = new ArrayList<>();
        int refnumber = refsudoku[cor_X][cor_Y];

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {

                if (refsudoku[y][x] == refnumber) {
                    int number = sudoku[x][y];
                    if (number != 0) {
                        check_ref_square_table.add(number);
                    }
                }
            }
        }
        return check_ref_square_table;
    }

    public ArrayList<Integer> checkexisting(Cords cord) {
        ArrayList<Integer> cell = new ArrayList<>();
        cell.addAll(checkrow(cord.getY()));
        cell.addAll(checkcolumn(cord.getX()));
        cell.addAll(check_ref_square(cord.getX(), cord.getY()));
        cell = removeDuplicates(cell);
        return cell;
    }

    public ArrayList<Corelation> return_exsisting_with_cords() {
        ArrayList<Cords> cords = emptycells(sudoku);
        ArrayList<Corelation> corelations = new ArrayList<>();
        for (Cords x : cords) {
            corelations.add(new Corelation(checkexisting(x), x));
        }
        return corelations;
    }

    public Corelation return_cords_with_most_exsisting(ArrayList<Corelation> corelations) {

        Collections.reverse(corelations);
        corelations.sort(Corelation::compareTo);
        Corelation corel = new Corelation(corelations.get(0).existingnumbers, corelations.get(0).getCord());

        return corel;
    }

    public ArrayList<Integer> possiblenumber(Corelation corel) {
        ArrayList<Integer> possiblereferention = new ArrayList<>();
        possiblereferention.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        possiblereferention.removeAll(corel.existingnumbers);
        return possiblereferention;
    }


    public int return_y(){
        int y = return_cords_with_most_exsisting(return_exsisting_with_cords()).getCord().getY();
        return y;

    }
    public int return_x(){
        int x = return_cords_with_most_exsisting(return_exsisting_with_cords()).getCord().getX();
        return x;

    }

    public ArrayList<Integer> posible(){
        ArrayList<Integer> posible = possiblenumber(return_cords_with_most_exsisting(return_exsisting_with_cords()));
        return posible;
    }


    public void sudokuDestroy() {
        ArrayList <Layer> stack = new ArrayList<>();

        while (emptycells(sudoku).size() > 0) {
            int exsistingnumbers = return_cords_with_most_exsisting(return_exsisting_with_cords()).existingnumbers.size();

            if (exsistingnumbers == 8) {
                sudoku[return_y()][return_x()] = posible().get(0);


            }
            if (exsistingnumbers < 8 ) {

                Layer layer = new Layer(return_cords_with_most_exsisting(return_exsisting_with_cords()).getCord(),sudoku, posible());
                stack.add(layer);

                sudoku[return_y()][return_x()] = (stack.get(stack.size() - 1)).getPossible().get(0);
                stack.get(stack.size() - 1).getPossible().remove(0);
            }

            if (exsistingnumbers == 9 ) {

                if (stack.get(stack.size() - 1).getPossible().size() == 0 ) {
                    stack.remove(stack.get(stack.size() - 1));

                } else {

                    sudoku = stack.get(stack.size()-1).copyArray(stack.get(stack.size() - 1).getSudoku());
                    sudoku[return_y()][return_x()] = (stack.get(stack.size() - 1)).getPossible().get(0);
                    stack.get((stack.size() - 1)).possible.remove(0);
                }

            }
            if (stack.size() == 0 ) {
                System.out.println("pra");
            }

        }
        displaysudoku();
    }
}












