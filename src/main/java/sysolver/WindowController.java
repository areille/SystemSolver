package sysolver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class WindowController
{
	@FXML
    private Text actiontarget;

    @FXML
    protected void handleLUButtonAction(ActionEvent event) {
        actiontarget.setText("LU Fact button pressed");
    }

	@FXML
	protected void handleInvertButtonAction(ActionEvent event){
		actiontarget.setText("Invert button pressed");
	}

	@FXML
	protected void handleClearButtonAction(ActionEvent event){
		actiontarget.setText("Clear button pressed");
	}
}

