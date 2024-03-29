package com.chntu_team.eapi_lab_1;

import com.chntu_team.eapi_lab_1.util.FxUtil;
import com.chntu_team.eapi_lab_1.util.StringUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import lombok.SneakyThrows;

import java.io.File;

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
    private Label colNumberLabel;
    @FXML
    private Label rowNumberLabel;

    @FXML
    public void onEncrypt(MouseEvent event) {
        if (checkInputLength())
            return;

        resultInput.setText(encryptor.encryption(
                toEncryptInput.getText(),
                StringUtil.indexOrder(colKeyInput.getText()),
                StringUtil.indexOrder(rowKeyInput.getText())
        ));
    }

    @FXML
    public void onDecrypt(MouseEvent event) {
        if (checkInputLength())
            return;

        if (toEncryptInput.getText().length() < 25) {
            new Alert(Alert.AlertType.ERROR, "Текст для розшифрування повинно містити 25 символів!", ButtonType.OK)
                    .showAndWait();
            return;
        }

        resultInput.setText(encryptor.decryption(
                toEncryptInput.getText(),
                StringUtil.indexOrder(colKeyInput.getText()),
                StringUtil.indexOrder(rowKeyInput.getText())
        ));
    }

    @FXML
    public void onFileLoad(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open encrypted text");
        fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("Encrypted text", ".txt")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file == null) return;

        final String content = StringUtil.readFromFile(file).replaceAll("[\n\r]", "");
        toEncryptInput.setText(content);
    }

    public void onFileSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Encrypted text", ".txt")
        );
        fileChooser.setTitle("Save encrypted text");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            StringUtil.saveToFile(resultInput.getText(), file);
        }
    }

    private boolean checkInputLength() {
        if (colKeyInput.getText().length() < 5) {
            new Alert(Alert.AlertType.ERROR, "Ключ для стовбців має містити 5 символів!", ButtonType.OK)
                    .showAndWait();
            return true;
        }

        if (rowKeyInput.getText().length() < 5) {
            new Alert(Alert.AlertType.ERROR, "Ключ для рядків має містити 5 символів!", ButtonType.OK)
                    .showAndWait();
            return true;
        }

        return false;
    }

    @FXML
    public void initialize() {
        toEncryptInput.textProperty().addListener(FxUtil.makeFilter(toEncryptInput, false, 25));
        colKeyInput.textProperty().addListener(FxUtil.makeFilter(colKeyInput, false, 5, (o, n) -> {
            colNumberLabel.setText(StringUtil.indexOrder(n));
            return n;
        }));
        rowKeyInput.textProperty().addListener(FxUtil.makeFilter(rowKeyInput, false, 5, (o, n) -> {
            rowNumberLabel.setText(StringUtil.indexOrder(n));
            return n;
        }));
    }
}
