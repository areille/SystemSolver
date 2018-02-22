package sysolver;

import java.io.IOException;
import java.util.ArrayList;

public class Solver {

    protected int rows;
    protected int cols;
    protected ArrayList<ArrayList<Double>> A;
    protected ArrayList<ArrayList<Double>> L;
    protected ArrayList<ArrayList<Double>> U;
    protected ArrayList<Double> b;

    public Solver() {
        rows = 0;
        cols = 0;
        A = new ArrayList<ArrayList<Double>>();
        L = new ArrayList<ArrayList<Double>>();
        U = new ArrayList<ArrayList<Double>>();
        b = new ArrayList<Double>();
    }

    public void setMatrix(ArrayList<ArrayList<Double>> matrix) {
        this.A = matrix;
        System.out.println();
        // System.out.println("New matrix set : ");
        // this.printMatrix();
    }

    public void setVector(ArrayList<Double> vect) {
        this.b = vect;
        System.out.println();
        // System.out.println("New vector set : ");
        // this.printVector();
    }

    public ArrayList<ArrayList<Double>> getMatrix() {
        return this.A;
    }
    
    public ArrayList<Double> getVector() {
        return this.b;
    }

    public int getMatrixSize() {
        return A.size();
    }

    public void reinit() {
        rows = 0;
        cols = 0;
        A = new ArrayList<ArrayList<Double>>();
        b = new ArrayList<Double>();
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

    public ArrayList<ArrayList<Double>> calculateUpper() {
        // TODO
        return this.U;
    }

    public ArrayList<ArrayList<Double>> calculateLower() {
        // TODO
        return this.L;
    }

    public Double calculateDet() {
        // TODO

        return 0.0;
    }

}