package controllers;

import com.example.swapper.SwapperApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import models.Account;
import observer.Observer;
import observer.Subject;
import services.common.FileHandler;
import services.db.ProductDbServices;
import services.navigation.SwitchScreen;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class AddProductController extends Subject {
    @FXML
    public Button btnUploadImage;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnMainPage;
    @FXML
    public Label lbCategory;
    @FXML
    public Label lMessage;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfDescription;
    @FXML
    private RadioButton rbTop;
    @FXML
    private ComboBox cbCategory;
    @FXML
    private ImageView imgView;

    private final FileChooser fileChooser = new FileChooser();
    private String imgPath;
    private String imgName;
    private File file;


    @FXML
    public void initialize() throws Exception {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Add product page loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnMainPage.setText(resourceBundle.getString("mainPage"));
        btnAdd.setText(resourceBundle.getString("add"));
        btnUploadImage.setText(resourceBundle.getString("uploadImage"));
        lbCategory.setText(resourceBundle.getString("category"));

        String categories[] = {"Shirt", "Pant", "Hoody", "Accessory", "Coat", "Boot"};
        for (String category : categories) {
            cbCategory.getItems().add(category);
        }
        cbCategory.getSelectionModel().selectFirst();
    }

    @FXML
    private void chooseImg() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        try {
            file = fileChooser.showOpenDialog(SwapperApplication.stage);
            imgPath = file.getAbsolutePath();
            imgName = file.getName();

        } catch (Exception e) {
            if (imgPath == null) {
                this.notifyObserver("Img not choosed do something", Observer.LEVEL.warning);
            }

            this.notifyObserver(e.toString(), Observer.LEVEL.severe);
        }

        imgView.setImage(new Image("file://" + imgPath));
        this.notifyObserver("New image was added", Observer.LEVEL.info);

    }

    @FXML
    private void addProduct() throws Exception {
        int loggedUserId = Account.getInstance().getLoggedUserId();
        if (imgPath == null || tfDescription.getText().isEmpty() || tfProductName.getText().isEmpty()) {
            this.notifyObserver("Not all field are filled in", Observer.LEVEL.severe);
            lMessage.setText("You have empty fields");
            return;
        }
        int newImageId = FileHandler.uploadFile(FileHandler.readImageToByteStream(imgPath, imgName));
        int result = ProductDbServices.insertProductDb(tfProductName.getText(), tfDescription.getText(), rbTop.isSelected(), loggedUserId, newImageId, cbCategory.getValue().toString());

        if (result > 0) {
            this.notifyObserver("New product was added", Observer.LEVEL.info);
            SwitchScreen.changeScreen("views/LandingPage.fxml");
        } else {
            this.notifyObserver("Failed adding new product to database.", Observer.LEVEL.warning);
        }
    }

    @FXML
    public void backToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/LandingPage.fxml");
    }
}
