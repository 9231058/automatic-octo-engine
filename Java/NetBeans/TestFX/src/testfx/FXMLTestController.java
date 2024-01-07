/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;

/**
 *
 * @author Parham Alvani
 */
public class FXMLTestController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField textField;
    @FXML
    private TableView tableView;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        textField.setText("Always Fuck");
    }

    @FXML
    private void handleTouchMoved(TouchEvent event) {
        event.consume();
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.Q && event.isControlDown()) {
            System.exit(0);
        }
        System.out.println("Fuck ...");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
