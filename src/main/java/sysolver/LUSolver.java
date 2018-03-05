package sysolver;

public class LUSolver extends Solver {
    public void solve() {
        System.out.println("Trying to solve");

        Double[] temp = new Double[n];
        x = new Double[n];
        int i, j;
        // temp = P * b
        for (i = 0; i < n; i++) {
            Double sum = 0.0;
            for (j = 0; j < n; j++) {
                sum += p[i][j] * b[j];
            }
            temp[i] = sum;
        }
        // Ly = b
        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
                temp[i] -= (L[i][j] * temp[j]);
            }
        }
        // Ux = y
        for (i = n - 1; i >= 0; i--) {
            for (j = i + 1; j < n; j++) {
                temp[i] -= U[i][j] * temp[j];
            }
            temp[i] /= U[i][i];
        }
        for (i = 0; i < n; i++)
            x[i] = temp[i];
    }
}