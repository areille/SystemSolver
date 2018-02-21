package sysolver;

import java.io.IOException;
import java.util.ArrayList;

public class Solver {

    protected int rows;
    protected int cols;
    protected ArrayList<ArrayList<Double>> A;
    protected ArrayList<Double> b;

    public Solver() {
        rows = 0;
        cols = 0;
        A = new ArrayList<ArrayList<Double>>();
        b = new ArrayList<Double>();
    }

    public Solver(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void reinit() {
        rows = 0;
        cols = 0;
        A = new ArrayList<ArrayList<Double>>();
        b = new ArrayList<Double>();
    }

    public void setCols(int val) {
        this.cols = val;
    }

    public void setVector(String[] chain) {
        for (String carac : chain) {
            try {
                b.add((Double) Double.parseDouble(carac));
            } catch (NullPointerException e) {
                System.err.println("Error : ");
                System.err.println(e);
            }
        }
        // DO NOT FORGET TO EMPTY THE VECTOR
    }

    public void addVectToMatrix(String[] chain) {
        ArrayList<Double> temp = new ArrayList<Double>();
        for (String carac : chain) {
            try {
                temp.add((Double) Double.parseDouble(carac));
            } catch (NullPointerException e) {
                System.err.println("error");
            }
        }
        A.add(temp);
    }

    public void printVector() {
        System.out.println("Vector b : ");
        for (int i = 0; i < b.size(); i++) {
            System.out.println(b.get(i));
        }
    }

    public void printMatrix() {
        System.out.println("Matrix A : ");
        int i = 0;
        for (ArrayList<Double> row : A) {
            System.out.println("Line no " + i + " :");
            for (Double val : row) {
                System.out.println(val + " ");
            }
            i++;
        }
    }

}