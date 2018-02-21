package sysolver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

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

	private Solver mySolver = new Solver();

	@FXML
	protected void handleLUButtonAction(ActionEvent event) {
		actiontarget.setText("LU Fact button pressed");
		sendMatrixToSolver();
		sendVectorToSolver();
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
		// mySolver.clear();
	}

	private void sendMatrixToSolver() {
		String matrix = matrixTextArea.getText();
		String[] rows = matrix.split("\n");

		for (String row : rows) {
			mySolver.addVectToMatrix(row.split(" "));
		}

	}

	private void sendVectorToSolver() {
		String vector = vectorTextArea.getText();
		// TESTS HERE
		mySolver.setCols(vector.length());
		mySolver.setVector(vector.split(" "));
	}
}
