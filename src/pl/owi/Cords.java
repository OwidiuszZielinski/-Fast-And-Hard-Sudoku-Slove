package pl.owi;

import java.util.ArrayList;

public class Cords {

    public Cords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cords(int x, int y, ArrayList<Integer> posible) {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    int x;
    int y;

    public Cords(int x, int y, int number,ArrayList<Integer> temp) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.temp = temp;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    int number;

    public ArrayList<Integer> getTemp() {
        return temp;
    }

    public void setTemp(ArrayList<Integer> temp) {
        this.temp = temp;
    }

    ArrayList<Integer> temp;



}
