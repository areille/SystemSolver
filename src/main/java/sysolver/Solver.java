package sysolver;

import java.io.IOException;
import java.util.ArrayList;

public class Solver {

    protected int n;
    // protected ArrayList<ArrayList<Double>> A;
    protected Double[][] A;
    // protected ArrayList<ArrayList<Double>> L;
    protected Double[][] L;
    // protected ArrayList<ArrayList<Double>> U;
    protected Double[][] U;
    // protected ArrayList<Double> b;
    protected Double[] b;

    public Solver() {
        n = 0;
        // A = new ArrayList<ArrayList<Double>>();
        // L = new ArrayList<ArrayList<Double>>();
        // U = new ArrayList<ArrayList<Double>>();
        // b = new ArrayList<Double>();
        reinit(n);
    }

    public void setMatrix(Double[][] matrix) {
        this.A = matrix;
        this.n = matrix.length;
        System.out.println();
        System.out.println("New matrix set : ");
        this.printMatrix();
    }

    public void setVector(Double[] vect) {
        this.b = vect;
        System.out.println();
        System.out.println("New vector set : ");
        this.printVector();
    }

    public Double[][] getMatrix() {
        return this.A;
    }

    public Double[][] getLMatrix() {
        return this.L;
    }

    public Double[][] getUMatrix() {
        return this.U;
    }

    public Double[] getVector() {
        return this.b;
    }

    public int getMatrixSize() {
        return n;
    }

    public void reinit(int size) {
        A = new Double[size][size];
        b = new Double[size];
    }

    public void printVector() {
        System.out.println("Vector b : ");
        String vect = "";
        for (int i = 0; i < n; i++) {
            vect += b[i] + " ";
        }
        System.out.println(vect);
    }

    public void printMatrix() {
        System.out.println("Matrix A : ");
        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < n; j++) {
                row += A[i][j] + " ";
            }
            System.out.println(row);
        }
    }

    // public void LUFact() {
    //     ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
    //     Double sum = 0.0;
    //     int i, j, k;
    //     int n = this.getMatrixSize();
    //     temp = this.getMatrix();

    //     for (i = 0; i < n; i++) {
    //         // UPPER
    //         for (k = i; k < n; k++) {
    //             sum = 0.0;
    //             for (j = 0; j < i; j++) {
    //                 sum += L.get(i).get(j) * U.get(i).get(j);
    //             }
    //             U.get(i).set(j, A.get(i).get(k) - sum);
    //         }
    //         // LOWER
    //         for (k = i; k < n; k++) {
    //             if (i == k) {
    //                 L.get(i).set(i, 1.0);
    //             } else {
    //                 sum = 0.0;
    //                 for (j = 0; j < i; j++) {
    //                     sum += L.get(k).get(j) * U.get(j).get(i);
    //                 }
    //                 L.get(k).set(i, (A.get(k).get(i) - sum) / U.get(i).get(i));
    //             }
    //         }
    //     }

    // for (k = 0; k < n - 1; k++) {

    //     for (i = k + 1; i < n; i++) {
    //         if (Math.abs(temp.get(k).get(k)) < 1.e-07) {
    //             System.out.println("Pivot is zero 1.");
    //             System.exit(1);
    //         }
    //         mult = temp.get(i).get(k) / temp.get(k).get(k);
    //         System.out.println("Mult = " + mult);
    //         temp.get(i).set(k, mult);
    //         for (j = k+1; j < n; j++) {
    //             double val = temp.get(i).get(j) - (mult * temp.get(k).get(j));
    //             System.out.println("val = " + val);
    //             temp.get(i).set(j, val);
    //             System.out.println("temp[i][i] = " + temp.get(i).get(i));
    //             if (Math.abs(temp.get(i).get(i)) < 1.e-07) {
    //                 System.out.println("Pivot is zero 2.");
    //                 System.exit(1);
    //             }
    //         }
    //     }
    // }
    // // create L and U from temp
    // for (i = 0; i < n; i++)
    //     L.get(i).set(i, 1.0);

    // for (i = 1; i < n; i++)
    //     for (j = 0; j < i; j++)
    //         L.get(i).set(j, temp.get(i).get(j));

    // for (i = 0; i < n; i++)
    //     for (j = i; j < n; j++)
    //         U.get(i).set(j, temp.get(i).get(j));
    // }
    // public ArrayList<ArrayList<Double>> calculateUpper() {
    //     // TODO

    //     return this.U;
    // }

    // public ArrayList<ArrayList<Double>> calculateLower() {
    //     // TODO
    //     return this.L;
    // }

    // public Double calculateDet() {
    //     // TODO
    //     if (this.getMatrixSize() == 2) {
    //         return A.get(0).get(0) * A.get(1).get(1) - A.get(0).get(1) * A.get(1).get(0);
    //     }
    //     return 0.0;
    // }

}