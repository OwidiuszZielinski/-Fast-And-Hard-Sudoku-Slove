package pl.owi;

public class SudokuCalc {

    int realmaxcords = 8;

    int[][] sudoku = {
            {8, 0, 0, 4, 5, 0, 1, 3, 0},
            {0, 4, 0, 0, 8, 1, 0, 0, 2},
            {5, 3, 0, 0, 0, 2, 0, 4, 0},
            {4, 0, 6, 5, 0, 0, 0, 2, 0},
            {0, 0, 3, 0, 0, 4, 0, 0, 1},
            {2, 0, 0, 9, 3, 0, 4, 5, 0},
            {0, 0, 2, 1, 4, 0, 0, 0, 3},
            {0, 8, 0, 0, 7, 0, 0, 0, 0},
            {0, 0, 4, 2, 0, 0, 6, 1, 0},};

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

    public void display() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(" " + sudoku[x][y]);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public void displayref() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(" " + refsudoku[x][y]);
            }
            System.out.println();
        }
    }

    //Metoda pobiera częściowo wypełnioną siatkę i próbuje przypisać wartości do wszystkich nieprzypisanych
    //lokalizacji w taki sposób na spełnienie wymagań dla Rozwiązania

    public boolean slove(int[][] sudoku, int row, int column) {
        while (row == realmaxcords && column == realmaxcords)
            return true;
        //Sprawdzamy czy wartość kolumny wynosi 9
        //Przechodzimy do kolejnego wiersza
        //Zaczynamy kolumne od 0
        if (column == realmaxcords) {
            row++;
            column = 0;
        }

        //sprawdzamy aktualna pozycje
        //jesli jest wieksza od 0 to iterujemy dla nastepnej kolumny
        if (sudoku[row][column] != 0)

            return slove(sudoku, row, column + 1);

        for (int num = 1; num < 10; num++) {

            //Sprawdzamy,czy mozna bezpiecznie umiescic liczbe od 1 do 9 w podany wiersz i  kolumne, przechodzimy dalej
            if (check(sudoku, row, column, num)) {

                //przypisujemy liczby w biezacym wierszu i kolumnie, zakladajac ze przypisanie jest poprawne
                sudoku[row][column] = num;

                //sprawdzamy mozliwosc z nastepna kolumna
                if (slove(sudoku, row, column + 1))
                    return true;
            }
            //usuwamy przypisana liczbe, poniewaz zalozenie bylo bledne i idziemy do nastepnego zalozenia z wartoscia num
            sudoku[row][column] = 0;
        }
        return false;
    }



    //sprawdzanie czy to bedzie odpowiednie aby przypisac num do podanego wiersza/kolumny
    public boolean check(int[][] sudoku, int row, int column,
                         int num) {
    //sprawdzamy czy znajdziemy te sama liczbe w podobnym wierszu, zwracamy falsz
        for (int x = 0; x <= 8; x++)
            if (sudoku[row][x] == num)
                return false;

        //sprawdzamy czy znajdziemy te sama liczbe w podobnym kolumnie, zwracamy falsz
        for (int x = 0; x <= 8; x++)
            if (sudoku[x][column] == num)
                return false;

        //sprawdzamy kwadrat 3 na 3
        int startRow = row - row % 3, startCol = column - column % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (sudoku[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    public void start() {
        if (slove(sudoku, 0, 0)) {
            display();
        }
    }

}


