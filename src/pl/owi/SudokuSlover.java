package pl.owi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SudokuSlover {

    ArrayList<Cords> cords = new ArrayList<>();
    ArrayList<Integer> possible = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    int[][] sudoku = {
            {6, 0, 0, 0, 0, 0, 5, 3, 0},
            {0, 0, 0, 0, 0, 2, 7, 0, 0},
            {5, 0, 7, 0, 9, 6, 0, 1, 8},
            {0, 0, 6, 0, 0, 1, 0, 8, 0},
            {0, 9, 8, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 4, 3},
            {3, 1, 0, 0, 0, 9, 0, 6, 2},};

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

    public void displayref() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(" " + refsudoku[x][y]);
            }
            System.out.println();
        }
    }

    public ArrayList<Cords> emptycells(int[][] temp) {


        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (temp[y][x] == 0) {
                    Cords cor = new Cords(x, y);

                    cords.add(cor);
                    //System.out.println("[" + cor.getX() + "]" + "[" + cor.getY() + "]");
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
                //System.out.println(rownumbers);
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
                //System.out.println(columnnumbers);
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

                    //TUTAJ DZIEJE SIE KLUCZOWY MOMENT KTOREGO NIE WIEDZIALEM JAK ZROBIC
                    //WARUNEK WYKRAWA Z REFERENCYJNEJ MALY KWADRAT PONIEWAZ WEZMIE TYLKO TE SAME LICZBY
                    //DALEJ MOZEMY DODAC WSZYSTKIE ELEMENTY Z MALEGO KWADRATU

                    int number = sudoku[x][y];
                    if (number != 0) {
                        checkqr.add(number);
                        //System.out.println(checkqr);


                    }

                }
            }

        }
        return checkqr;
    }

    public ArrayList<Integer> checkcell(int cor_X, int cor_Y) {
        ArrayList<Integer> cell = new ArrayList<>();
        cell.addAll(checkrow(cor_Y));
        cell.addAll(checkcolumn(cor_X));
        cell.addAll(checkqrt(cor_X, cor_Y));
        cell = removeDuplicates(cell);
        //System.out.println(cell);

        return cell;
    }
    //LISTA KORDOW KT�RE MAJA 8 liczb pewnych


    public ArrayList<Cords> calculate_cords_8_number_check() {

        ArrayList<Cords> result = new ArrayList<>();
        for (Cords x : cords) {
            if (checkcell(x.getX(), x.getY()).size() == 8) {
                result.add(new Cords(x.getX(), x.getY()));
                //System.out.println("[" + x.getX() + "]" + "[" + x.getY() + "]");
            }

        }
        return result;

    }

    public ArrayList<Cords> calculate_cords_7_number_check() {

        ArrayList<Cords> result = new ArrayList<>();
        for (Cords x : cords) {
            if (checkcell(x.getX(), x.getY()).size() == 7) {
                result.add(new Cords(x.getX(), x.getY()));
                //System.out.println("[" + x.getX() + "]" + "[" + x.getY() + "]");
            }

        }
        return result;

    }


    public int[][] calculatee() {

        emptycells(sudoku);
        int[][] result;


        for (Cords x : calculate_cords_8_number_check()) {

            possible.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            ArrayList<Integer> value = new ArrayList<>();
            ArrayList<Cords> cords = new ArrayList<>();

            value.addAll(checkcell(x.getX(), x.getY()));
            // System.out.println(value);
            //DLACZEGO JESLI LISTA POSSIBLE BYLA ZADEKLAROWANA W KLASIE TO NIE DZIALALO USUWANIE ELEMENTOW????
            possible.removeAll(value);
            x.setNumber(possible.get(0));

            cords.add(new Cords(x.getX(), x.getY()));
            //System.out.println("[" + x.getX() + "]" + "[" + x.getY() + "]");
            // System.out.println(possible);


            sudoku[x.getY()][x.getX()] = x.getNumber();



        }
        cords.removeAll(cords);
        result = sudoku;


        return result;
    }



    public int calculatehard() {
        emptycells(calculatee());
        int result = 0;

        for (Cords x : calculate_cords_7_number_check()) {
            ArrayList<Integer> posible = new ArrayList<>();
            posible.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            ArrayList<Integer> value = new ArrayList<>();
            value.addAll(checkcell(x.getX(), x.getY()));
            System.out.println("[" + x.getX() + "]" + "[" + x.getY() + "]" + " Kordy");
            System.out.println(value + " Liczby pewne");
            posible.removeAll(value);
            posible = removeDuplicates(posible);
            System.out.println(posible + " Liczby mozliwe");
            result = posible.get(0);





        }



        return result;

    }

    public void sudokudestroy() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (sudoku[x][y] == 0) {
                    calculatee();


                }
            }
        }

        //wprowadza we wszystkie miejsca z kordow
        //chce aby wprowadzilo w pierwsze i poszlo dalej
    }

}
