package app.controllers;

import app.GlobalData;
import app.dao.BaseUserDAOList;
import app.model.BaseUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML private TextField nameInput;
    @FXML private TextField usernameInput;
    @FXML private PasswordField passwordInput;
    @FXML private Label statusLabel;
    private BaseUserDAOList users = new BaseUserDAOList();
    private BaseUser loggedUser = GlobalData.getLoggedUser();

    @FXML
    protected void onSaveButton() throws IOException, ClassNotFoundException{
        users.loadDatFile();
        BaseUser oldUserData = users.findByUsername(loggedUser.getUsername());
        String fullName = nameInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        loggedUser.setName(fullName);
        loggedUser.setUsername(username);
        loggedUser.setPassword(password);
        users.update(oldUserData, loggedUser);
        users.saveDatFile();
        statusLabel.setText("Alterações salvas com sucesso!");
    }

    @FXML
    protected void nameField(){
        nameInput.setText(loggedUser.getName());
        nameInput.setFocusTraversable(false);
    }

    @FXML
    protected void usernameField(){
        usernameInput.setText(loggedUser.getUsername());
        usernameInput.setFocusTraversable(false);
    }

    @FXML
    protected void passwordField(){
        passwordInput.setText(loggedUser.getPassword());
        passwordInput.setFocusTraversable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameField();
        usernameField();
        passwordField();
    }
}
