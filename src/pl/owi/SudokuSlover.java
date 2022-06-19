package pl.owi;

import java.util.*;

public class SudokuSlover {

    int[][] sudoku = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

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

    public void display() {
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

    public ArrayList<Integer> checkqrt(int cor_X, int cor_Y) {
        ArrayList<Integer> checkqr = new ArrayList<>();

        int refnumber = refsudoku[cor_X][cor_Y];


        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {

                if (refsudoku[y][x] == refnumber) {
                    int number = sudoku[x][y];
                    if (number != 0) {
                        checkqr.add(number);
                    }
                }
            }
        }
        return checkqr;
    }

    public ArrayList<Integer> checkexisting(Cords cord) {
        ArrayList<Integer> cell = new ArrayList<>();
        cell.addAll(checkrow(cord.getY()));
        cell.addAll(checkcolumn(cord.getX()));
        cell.addAll(checkqrt(cord.getX(), cord.getY()));
        cell = removeDuplicates(cell);
        return cell;
    }

    public ArrayList<Corelation> calchard() {
        ArrayList<Cords> cords = emptycells(sudoku);
        ArrayList<Corelation> corelations = new ArrayList<>();
        for (Cords x : cords) {
            corelations.add(new Corelation(checkexisting(x), x));
        }
        return corelations;
    }

    public Corelation bestcords(ArrayList<Corelation> corelations) {

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


    public void sudokuDestroy() {

        while (emptycells(sudoku).size() > 0) {

            if (bestcords(calchard()).existingnumbers.size() == 8) {
                sudoku[bestcords(calchard()).getCord().getY()][bestcords(calchard()).getCord().getX()] = possiblenumber(bestcords(calchard())).get(0);

            }

            if (bestcords(calchard()).existingnumbers.size() < 8) {
                Stack stack = new Stack(bestcords(calchard()).getCord(), sudoku, possiblenumber(bestcords(calchard())));
                sudoku[bestcords(calchard()).getCord().getY()][bestcords(calchard()).getCord().getX()] = stack.getPossible().get(0);
                stack.getPossible().remove(0);
                display();

                if (bestcords(calchard()).existingnumbers.size() == 9) {
                    System.out.println(bestcords(calchard()).existingnumbers.size());
                    sudoku = stack.getSudoku();
                    System.out.println("xd");


                }


            }


        }


    }


}





