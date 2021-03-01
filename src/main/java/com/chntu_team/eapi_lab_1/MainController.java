package com.chntu_team.eapi_lab_1;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {
    private final DoubleTranspositionEncryptor encryptor = new DoubleTranspositionEncryptor();

    @FXML
    private TextField colKeyInput;
    @FXML
    private TextField rowKeyInput;
    @FXML
    private TextField toEncryptInput;
    @FXML
    private TextField resultInput;

    @FXML
    public void onEncrypt(MouseEvent event) {
        resultInput.setText(encryptor.encryption(toEncryptInput.getText(), colKeyInput.getText(), rowKeyInput.getText()));
    }

    @FXML
    public void onDecrypt(MouseEvent event) {
        resultInput.setText(encryptor.decryption(toEncryptInput.getText(), colKeyInput.getText(), rowKeyInput.getText()));
    }

    @FXML
    public void initialize() {
        toEncryptInput.textProperty().addListener(makeFilter(toEncryptInput,  false,25));
        colKeyInput.textProperty().addListener(makeFilter(colKeyInput,  true,5));
        rowKeyInput.textProperty().addListener(makeFilter(rowKeyInput, true,5));
    }

    private ChangeListener<String> makeFilter(TextField field, boolean numbersOnly, int maxLength) {
        return (observable, oldValue, newValue) -> {
            if (numbersOnly && !newValue.matches("\\d*"))
                field.setText(newValue.replaceAll("[^\\d]", ""));

            if (maxLength > 0 && newValue.length() > maxLength)
                field.setText(newValue.substring(0, maxLength));
        };
    }
}
