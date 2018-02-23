package sysolver;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.stream.IntStream.range;

public class Solver {

    protected int n;
    // protected ArrayList<ArrayList<Double>> A;
    protected Double[][] A;
    protected Double[][] m; // pivoted matrix
    // protected ArrayList<ArrayList<Double>> L;
    protected Double[][] L;
    // protected ArrayList<ArrayList<Double>> U;
    protected Double[][] U;
    // protected ArrayList<Double> b;
    protected Double[] b;
    protected Double[][] p;

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

    // static Double dotProduct(Double[] a, Double[] b) {
    //     return range(0, a.length).mapToDouble(i -> a[i] * b[i]).sum();
    // }

    // static Double[][] matrixMul(Double[][] A, Double[][] B) {
    //     Double[][] result = new Double[A.length][B[0].length];
    //     Double[] aux = new Double[B.length];

    //     for (int j = 0; j < B[0].length; j++) {

    //         for (int k = 0; k < B.length; k++)
    //             aux[k] = B[k][j];

    //         for (int i = 0; i < A.length; i++)
    //             result[i][j] = dotProduct(A[i], aux);
    //     }
    //     return result;
    // }

    // static Double[][] pivotize(Double[][] m) {
    //     int n = m.length;
    //     Double[][] id = new Double[n][n];
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (i == j)
    //                 id[i][j] = 1.0;
    //             else
    //                 id[i][j] = 0.0;
    //         }
    //     }
    //     for (int i = 0; i < n; i++) {
    //         Double maxm = m[i][i];
    //         int row = i;
    //         for (int j = i; j < n; j++)
    //             if (m[j][i] > maxm) {
    //                 maxm = m[j][i];
    //                 row = j;
    //             }

    //         if (i != row) {
    //             Double[] tmp = id[i];
    //             id[i] = id[row];
    //             id[row] = tmp;
    //         }
    //     }
    //     return id;
    // }

    // public void LUFact() {
    //     U = new Double[n][n];
    //     L = new Double[n][n];
    //     P = new Double[n][n];
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < n; j++) {
    //             U[i][j] = 0.0;
    //             L[i][j] = 0.0;
    //         }
    //     }
    //     P = pivotize(this.A);
    //     Double[][] A2 = matrixMul(P, A);
    //     for (int j = 0; j < n; j++) {
    //         L[j][j] = 1.0;
    //         for (int i = 0; i < j + 1; i++) {
    //             double s1 = 0;
    //             for (int k = 0; k < i; k++)
    //                 s1 += U[k][j] * L[i][k];
    //             U[i][j] = A2[i][j] - s1;
    //         }
    //         for (int i = j; i < n; i++) {
    //             double s2 = 0;
    //             for (int k = 0; k < j; k++)
    //                 s2 += U[k][j] * L[i][k];
    //             L[i][j] = (A2[i][j] - s2) / U[j][j];
    //         }
    //         // L[j][j] = 1.0;
    //     }
    // }

    public void LUFact() {
        Double[][] temp = new Double[n][n];
        L = new Double[n][n];
        U = new Double[n][n];
        Double mult = 0.0;
        int i, j, k;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                temp[i][j] = m[i][j];
                L[i][j] = 0.0;
                U[i][j] = 0.0;
            }
        }
        for (k = 0; k < n - 1; k++) {
            for (i = k + 1; i < n; i++) {
                if (Math.abs(temp[k][k]) < 1.e-07) {
                    System.out.println("Pivot is zero (1)");
                    System.exit(1);
                }
                mult = temp[i][k] / temp[k][k];
                temp[i][k] = mult;
                for (j = k + 1; j < n; j++) {
                    temp[i][j] -= mult * temp[k][j];
                    if (Math.abs(temp[i][i]) < 1.e-07) {
                        System.out.println("Pivot is zero (2)");
                        System.exit(1);
                    }
                }
            }
        }
        // create l and u from temp
        for (i = 0; i < n; i++) {
            this.L[i][i] = 1.0;
        }
        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                this.L[i][j] = temp[i][j];

        for (i = 0; i < n; i++)
            for (j = i; j < n; j++)
                this.U[i][j] = temp[i][j];
    }

    public void findPivot() {

        int i, j, k;
        int pvtk, pvti;
        Double[] scale = new Double[n];
        Double aet, tmp, mult;
        Double[][] temp = new Double[n][n];
        p = new Double[n][n];
        int[] pvt = new int[n];

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                temp[i][j] = A[i][j];
                p[i][j] = 0.0;
            }
        }
        for (k = 0; k < n; k++)
            pvt[k] = k;
        for (k = 0; k < n; k++) {
            scale[k] = 0.0;
            for (j = 0; j < n; j++)
                if (Math.abs(scale[k]) < Math.abs(temp[k][j]))
                    scale[k] = Math.abs(temp[k][j]);
        }
        for (k = 0; k < n - 1; k++) {
            int pc = k;
            aet = Math.abs(temp[pvt[k]][k] / scale[k]);
            for (i = k + 1; i < n; i++) {
                tmp = Math.abs(temp[pvt[i]][k] / scale[pvt[i]]);
                if (tmp > aet) {
                    aet = tmp;
                    pc = i;
                }
            }
            if (Math.abs(aet) < 1.e-07) {
                System.out.println("Pivot is zero (3)");
                System.exit(1);
            }
            if (pc != k) { // swap pvt[k] and pvt[pc]
                int ii = pvt[k];
                pvt[k] = pvt[pc];
                pvt[pc] = ii;
            }

            // now eliminate the column entries logically below mx[pvt[k]][k]
            pvtk = pvt[k]; // pivot row
            for (i = k + 1; i < n; i++) {
                pvti = pvt[i];
                if (temp[pvti][k] != 0) {
                    mult = temp[pvti][k] / temp[pvtk][k];
                    temp[pvti][k] = mult;
                    for (j = k + 1; j < n; j++)
                        temp[pvti][j] -= mult * temp[pvtk][j];
                }
            }
        }
        for (i = 0; i < n; i++)
            p[i][pvt[i]] = 1.0;
    }

    public void multiplyMatrixToPivot() {
        this.m = new Double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                m[i][j] = 0.0;
            }
        }
        System.out.println("Matrix p : ");
        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < n; j++) {
                row += p[i][j] + " ";
            }
            System.out.println(row);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // System.out.println(p[i][j] + " ");
                for (int k = 0; k < n; k++) {
                    //     System.out.println(p[k][j]);
                    m[i][j] += p[i][k] * A[k][j];
                }
            }
        }
        System.out.println("Matrix m : ");
        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < n; j++) {
                row += m[i][j] + " ";
            }
            System.out.println(row);
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
        Double det = U[0][0];
        int nbOnes = 0;
        for (int i = 0; i < n; i++) {
            det *= U[i][i];
            if (p[i][i] == 1.0) {
                nbOnes++;
            }
        }
        if (nbOnes % 2 == 0)
            return det;
        else
            return -det;
    }

}