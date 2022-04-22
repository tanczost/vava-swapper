package controllers;

import com.example.swapper.SwapperApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Account;
import models.User;
import observer.Observer;
import observer.Subject;
import service.db.UserDbServices;
import service.navigation.SwitchScreen;
import service.validation.Validator;

import java.io.IOException;
import java.util.ResourceBundle;

public class EditPersonalInfoController extends Subject {
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfNickName;
    @FXML
    private TextField tfTown;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfSchool;
    @FXML
    private Label lbFirstName;
    @FXML
    private Label lbLastName;
    @FXML
    private Label lbPassword;
    @FXML
    private Label lbNickName;
    @FXML
    private Label lbTown;
    @FXML
    private Label lbStreet;
    @FXML
    private Label lbSchool;
    @FXML
    private Label lMessage;
    @FXML
    private Button btnUpdate;
    @FXML
    public Button btnBack;
    private Account account = Account.getInstance();

    @FXML
    public void initialize() {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("User personal settings page loaded.", Observer.LEVEL.info);

        User currentUser = account.getCurrentUser();
        tfFirstName.setText(currentUser.getFirstName());
        tfLastName.setText(currentUser.getLastName());
        tfNickName.setText(currentUser.getNickName());
        tfTown.setText(currentUser.getTown());
        tfEmail.setText(currentUser.getEmail());
        tfStreet.setText(currentUser.getStreet());
        tfSchool.setText(currentUser.getSchool());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbFirstName.setText(resourceBundle.getString("firstname"));
        lbLastName.setText(resourceBundle.getString("lastname"));
        lbPassword.setText(resourceBundle.getString("password"));
        lbNickName.setText(resourceBundle.getString("nickname"));
        lbTown.setText(resourceBundle.getString("town"));
        lbStreet.setText(resourceBundle.getString("street"));
        lbSchool.setText(resourceBundle.getString("school"));
        btnUpdate.setText(resourceBundle.getString("update"));
        btnBack.setText(resourceBundle.getString("back"));
    }

    @FXML
    private void updateInfo() throws Exception {
        if (!tfFirstName.getText().isEmpty() && !tfNickName.getText().isEmpty()
                && !tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty()
                && !tfLastName.getText().isEmpty() && !tfTown.getText().isEmpty()
                && !tfStreet.getText().isEmpty() && !tfSchool.getText().isEmpty()
        ) {
            if (Validator.validDbInputEmail(tfEmail.getText(), lMessage)
                    && Validator.validRegisterPassword(tfPassword.getText(), lMessage)
            ) {

                int success = UserDbServices.updateUserDb(tfNickName.getText(),
                        tfFirstName.getText(), tfLastName.getText(),
                        tfEmail.getText(), tfTown.getText(),
                        tfStreet.getText(), tfSchool.getText()
                );

                if (success > 0) {
                    account.setCurrentUserDetails(tfNickName.getText(),
                            tfFirstName.getText(), tfLastName.getText(),
                            tfEmail.getText(), tfTown.getText(),
                            tfStreet.getText(), tfSchool.getText());

                    System.out.println("Successfully updated");
                    SwitchScreen.changeScreen("views/ProposalPage.fxml");

                    this.notifyObserver("User updated its info.", Observer.LEVEL.info);
                } else {
                    System.out.println("Update failed. :(");

                    this.notifyObserver("User failed to update its info.", Observer.LEVEL.warning);
                }
            }
        } else {
            lMessage.setText("You have empty fields");
        }
    }

    public void backToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
