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

    public void LUFact() {
        int N = this.getMatrixSize();
        Double[] P;
        Double absA;
        for (int i = 0; i < N; i++) {
            P[i] = i;
        }
        for (int i = 0; i < N; i++) {
            double maxA = 0.0;
            int imax = i;

            for (int k = i; k < N; k++) {
                if (absA = Math.abs(A.get(k).get(i)) > maxA) {
                    maxA = absA;
                    imax = k;
                }
            }
            if (imax != i) {
                // pivoting p
                int j = P[i];
                P[i] = P[imax];
                P[imax] = j;

                // pivoting rows of A
                Array<Double> temp = A.get(i);
                A.set(i, A.get(imax));
                A.set(imax, temp);

                P[N]++;
            }
            for (int j = i + 1; j < N; j++) {
                A.get(j).set(i, A.get(j).get(i) / A.get(i).get(i));

                for (int k = i+1; k < N; k++) {
                    A.get(j).set(k, (A.get(j).get(k) - (A.get(j).get(i)*A.get(i).get(k))));
                }
            }
        }
    }
    // public ArrayList<ArrayList<Double>> calculateUpper() {
    //     // TODO

    //     return this.U;
    // }

    // public ArrayList<ArrayList<Double>> calculateLower() {
    //     // TODO
    //     return this.L;
    // }

    public Double calculateDet() {
        // TODO
        if (this.getMatrixSize() == 2) {
            return A.get(0).get(0) * A.get(1).get(1) - A.get(0).get(1) * A.get(1).get(0);
        }
        return 0.0;
    }

}