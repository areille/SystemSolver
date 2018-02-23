package sysolver;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class WindowController {
	@FXML
	private Text actiontarget;
	@FXML
	private TextArea matrixTextArea;
	@FXML
	private TextArea vectorTextArea;

	@FXML
	private TextArea outputTextArea;

	private LUSolver myLuSolver = new LUSolver();
	// private LUSolver myLuSolver = new LUSolver();
	private Boolean isInputError = false;

	@FXML
	protected void handleLUButtonAction(ActionEvent event) {
		actiontarget.setText("LU Fact button pressed");
		myLuSolver.reinit(0);
		sendMatrixToSolver(myLuSolver);
		sendVectorToSolver(myLuSolver);
		if (isInputError) {
			outputTextArea.setText("");
		} else {
			String output = "";
			output += "LU Decomposition with scaled partial pivoting\n";
			output += "\n";

			String mat = matrixToString(myLuSolver.getMatrix());
			output += "Original matrix :\n";
			output += mat;
			output += "\n";

			String vect = vectorToString(myLuSolver.getVector());
			output += "Original vector :\n";
			output += vect;
			output += "\n\n";

			// myLuSolver.LUFact();

			// String matL = matrixToString(myLuSolver.getLMatrix());
			// output += "Lower matrix :\n";
			// output += matL;
			// output += "\n";

			// String matU = matrixToString(myLuSolver.getUMatrix());
			// output += "Upper matrix :\n";
			// output += matU;
			// output += "\n";

			// String det = myLuSolver.calculateDet().toString();
			// output += "Determinant = " + det;
			outputTextArea.setText(output);
		}
		myLuSolver.solve();
		// mySolver.LUSolve();
	}

	@FXML
	protected void handleInvertButtonAction(ActionEvent event) {
		actiontarget.setText("Invert button pressed");
	}

	@FXML
	protected void handleClearButtonAction(ActionEvent event) {
		actiontarget.setText("Clear button pressed");
		matrixTextArea.setText("");
		vectorTextArea.setText("");
		outputTextArea.setText("");
		// mySolver.clear();
	}

	private void sendMatrixToSolver(Solver theSolver) {
		String matrix = matrixTextArea.getText();
		String[] rows = matrix.split("\n");
		int size = rows.length;
		Double[][] A = new Double[size][size];
		this.isInputError = false;
		if (size < 2) {
			isInputError = true;
			System.err.println("Input error : too small matrix (mini 2x2)");
		} else {
			int i = 0;
			for (String row : rows) {
				String[] lineValues = row.split(" ");
				if (lineValues.length != rows.length) {
					// tests if a matrix is square
					System.out.println("Input error : not square matrix");
					isInputError = true;
					break;
				} else {
					Double[] tempRow = new Double[size];
					int j = 0;
					for (String value : lineValues) {
						if (value.matches("\\d+")) {
							// tests if the input contains digits
							tempRow[j] = (Double) Double.parseDouble(value);
							j++;
						} else {
							isInputError = true;
							System.out.println("Input error : matrix containing non digital characters");
							break;
						}
					}
					if (!isInputError) {
						A[i] = tempRow;
					}
					i++;
				}
			}
		}
		if (!isInputError) {
			theSolver.setMatrix(A);
		}
	}

	private void sendVectorToSolver(Solver theSolver) {
		String vector = vectorTextArea.getText();
		String[] values = vector.split(" ");
		Double[] b = new Double[values.length];

		this.isInputError = false;
		if (vector.isEmpty()) {
			isInputError = true;
			System.out.println("Input error : no vector input");
		} else if (values.length != theSolver.getMatrixSize()) {
			isInputError = true;
			System.out.println("Input error : size of vector different from matrix size");
		} else {
			int i = 0;
			for (String val : values) {
				if (val.matches("\\d+")) {
					// tests if the input contains digits
					b[i] = (Double) Double.parseDouble(val);
				} else {
					isInputError = true;
					System.out.println("Input error : vector containing non digital characters");
					break;
				}
				i++;
			}
		}
		if (!isInputError) {
			theSolver.setVector(b);
		}
	}

	private String matrixToString(Double[][] matrix) {
		String stringMatrix = "";
		for (Double[] row : matrix) {
			for (Double val : row) {
				stringMatrix += val.toString() + " ";
			}
			stringMatrix += "\n";
		}

		return stringMatrix;
	}

	private String vectorToString(Double[] vect) {
		String stringVect = "";
		for (Double val : vect) {
			stringVect += val.toString() + " ";
		}
		return stringVect;
	}
}
