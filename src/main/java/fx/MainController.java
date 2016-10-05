package fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MainController {

    private Main main;

    @FXML public ChoiceBox<String> version;
    @FXML public ChoiceBox<String> typeEncrypt;
    @FXML public ChoiceBox<String> typeHash;
    @FXML public TextField username;
    @FXML public TextField password;
    @FXML public TextField encryptPassword;
    @FXML public TextField community;


    private final ObservableList<String> versionSnmp = FXCollections.observableArrayList("v1", "v2", "v3");
    private final ObservableList<String> typeHashList = FXCollections.observableArrayList("MD5", "SHA", "No");
    private final ObservableList<String> typeEncryptList = FXCollections.observableArrayList("DES", "No");

    public MainController() {

    }

    public void initialize() {
        version.setItems(versionSnmp);
        typeEncrypt.setItems(typeEncryptList);
        typeHash.setItems(typeHashList);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void defineVisible() {
        Value.valueOf(version.getValue()).setVisible(
                typeEncrypt,
                typeHash,
                password,
                encryptPassword,
                community,
                username);
        tryInvoke();
    }



    public void visibleHash() {
        password.setDisable(typeHash.getValue().equals("No"));
    }

    public void visibleEncr() {
        encryptPassword.setDisable(typeEncrypt.getValue().equals("No"));    }

    private void tryInvoke() {
        try {
            if(version.getValue().equals("v3")) {
                visibleHash();
                visibleEncr();
            }
        } catch (NullPointerException e) {
            //Not initialize yet
        }
    }


}
