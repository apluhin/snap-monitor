package fx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.Device;
import snmp.SnmpDevice;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    private Main main;

    @FXML public TableView<Device> deviceTableView;
    @FXML public TableColumn<Device, String> nameColumn;
    @FXML public ChoiceBox<String> version;
    @FXML public ChoiceBox<String> typeEncrypt;
    @FXML public ChoiceBox<String> typeHash;
    @FXML public TextField username;
    @FXML public TextField password;
    @FXML public TextField encryptPassword;
    @FXML public TextField community;
    @FXML public TextField name;
    @FXML public TextField vendor;
    @FXML public TextField address;


    private final ObservableList<String> versionSnmp = FXCollections.observableArrayList("v1", "v2", "v3");
    private final ObservableList<String> typeHashList = FXCollections.observableArrayList("MD5", "SHA", "No");
    private final ObservableList<String> typeEncryptList = FXCollections.observableArrayList("DES", "No");

    private final ObservableList<Device> devices = FXCollections.observableArrayList();

    public MainController() {

    }

    public void initialize() {
        version.setItems(versionSnmp);
        typeEncrypt.setItems(typeEncryptList);
        typeHash.setItems(typeHashList);
        deviceTableView.setItems(devices);
        nameColumn.setCellValueFactory(s -> new SimpleStringProperty((s.getValue().getName())));
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void defineVisible() {
        clearAll();
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
        boolean no = typeHash.getValue().equals("No");
        password.setDisable(no);
        typeEncrypt.setDisable(no);
        if(no) typeEncrypt.setValue("No");

    }

    public void visibleEncr() {
        encryptPassword.setDisable(typeEncrypt.getValue().equals("No"));
    }


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


    public void addDevice() {
        Map<String,String> map = new HashMap<>();
        String value = typeEncrypt.getValue();
        String value1 = typeHash.getValue();
        map.put("typeEncript", value.equals("No") ? null : value);
        map.put("typeHash", value1.equals("No") ? null : value1);
        map.put("hashPassword", getNotNull(password));
        map.put("encryptionPassword", getNotNull(encryptPassword));
        map.put("username", getNotNull(username));
        map.put("version", version.getValue());
        map.put("community", getNotNull(community));
        devices.add(new Device(vendor.getText(),
                address.getText(),
                name.getText(),
                new SnmpDevice(map)));
        System.out.println(devices.get(devices.size() - 1).toString());
    }

    public void clearAll() {
        typeEncrypt.setValue("No");
        typeHash.setValue("No");
        password.setText("");
        encryptPassword.setText("");
        username.setText("");
        community.setText("");
    }

    private String getNotNull(TextField textField) {
        if(textField.getText().equals("")) {
            return null;
        } else {
            return textField.getText();
        }
    }
}
