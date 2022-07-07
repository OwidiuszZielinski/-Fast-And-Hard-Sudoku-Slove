package pl.owi;

import java.util.ArrayList;

public class Corelation implements Comparable<Corelation> {
    public Corelation(ArrayList<Integer> existingnumbers, Cords cord) {
        this.existingnumbers = existingnumbers;
        this.cord = cord;
    }ArrayList<Integer> existingnumbers;
    Cords cord;
    public Cords getCord() {
        return cord;
    }
    @Override
    public int compareTo(Corelation o) {
        //
        return -(this.existingnumbers.size() - o.existingnumbers.size()) ;
    }
}
