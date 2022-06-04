package pl.owi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SudokuSlover sc = new SudokuSlover();
        sc.display();

        sc.emptycells();
        sc.calculatee();
        //sc.sudokudestroy();
        sc.display();
        //sc.getnumber();
        //sc.checkqrt(1,2);
        //sc.checkrow(0);
        //sc.checkcolumn(0);
        //sc.checkcell(1,0);
        //sc.checkvalue();
    }
}