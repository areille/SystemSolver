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
		myLuSolver.reinit();
		sendMatrixToSolver(myLuSolver);
		sendVectorToSolver(myLuSolver);
		if (isInputError) {
			outputTextArea.setText("");
		} else {
			String mat = matrixToString(myLuSolver.getMatrix());
			String vect = vectorToString(myLuSolver.getVector());
			String det = myLuSolver.calculateDet().toString();
			String output = "";
			output += "LU Decomposition with scaled partial pivoting\n";
			output += "Original matrix :\n";
			output += mat;
			output += "\n";
			output += "Original vector :\n";
			output += vect;
			output += "\n\n";
			output += "Determinant = " + det;
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
		ArrayList<ArrayList<Double>> A = new ArrayList<ArrayList<Double>>();
		String matrix = matrixTextArea.getText();
		String[] rows = matrix.split("\n");
		this.isInputError = false;
		if (rows.length < 2) {
			isInputError = true;
			System.err.println("Input error : too small matrix (mini 2x2)");
		} else {
			for (String row : rows) {
				String[] lineValues = row.split(" ");
				if (lineValues.length != rows.length) {
					// tests if a matrix is square
					System.out.println("Input error : not square matrix");
					isInputError = true;
					break;
				} else {
					ArrayList<Double> tempRow = new ArrayList<Double>();
					for (String value : lineValues) {
						if (value.matches("\\d+")) {
							// tests if the input contains digits
							tempRow.add((Double) Double.parseDouble(value));
						} else {
							isInputError = true;
							System.out.println("Input error : matrix containing non digital characters");
							break;
						}
					}
					if (!isInputError) {
						A.add(tempRow);
					}
				}
			}
		}
		if (!isInputError) {
			theSolver.setMatrix(A);
		}
	}

	private void sendVectorToSolver(Solver theSolver) {
		ArrayList<Double> b = new ArrayList<Double>();
		String vector = vectorTextArea.getText();
		String[] values = vector.split(" ");

		this.isInputError = false;
		if (vector.isEmpty()) {
			isInputError = true;
			System.out.println("Input error : no vector input");
		} else if (values.length != theSolver.getMatrixSize()) {
			isInputError = true;
			System.out.println("Input error : size of vector different from matrix size");
		} else {
			for (String val : values) {
				if (val.matches("\\d+")) {
					// tests if the input contains digits
					b.add((Double) Double.parseDouble(val));
				} else {
					isInputError = true;
					System.out.println("Input error : vector containing non digital characters");
					break;
				}
			}
		}
		if (!isInputError) {
			theSolver.setVector(b);
		}
	}

	private String matrixToString(ArrayList<ArrayList<Double>> matrix) {
		String stringMatrix = "";
		for (ArrayList<Double> row : matrix) {
			for (Double val : row) {
				stringMatrix += val.toString() + " ";
			}
			stringMatrix += "\n";
		}

		return stringMatrix;
	}

	private String vectorToString(ArrayList<Double> vect) {
		String stringVect = "";
		for (Double val : vect) {
			stringVect += val.toString() + " ";
		}
		return stringVect;
	}
}
