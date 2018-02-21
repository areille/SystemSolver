package sysolver;

import java.util.ArrayList;

public class Solver {
    
    protected int rows;
    protected int cols;
    protected ArrayList<ArrayList<Double>> A;
    protected ArrayList<Double> b;

    public Solver() {
        rows = 0;
        cols = 0;
        A = null;
        b = null;
    }

    public Solver(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void setCols(int val){
        this.cols = val;
    }

    public void setVector(String[] chain){
        for (String carac : chain) {
            System.out.println(carac);
        }
    }

    public void addVectToMatrix(String[] chain){
        for (String carac : chain){
            System.out.println(carac);
        }
    }

}