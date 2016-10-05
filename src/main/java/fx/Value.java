package fx;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public enum Value {
    v1 {
        @Override
        public void setVisible(ChoiceBox<String> typeEncrypt, ChoiceBox<String> typeHash, TextField password, TextField encryptPassword, TextField community, TextField username) {
            stateForOldVersion(typeEncrypt, typeHash, password, encryptPassword, community, username);

        }
    },
    v2 {
        @Override
        public void setVisible(ChoiceBox<String> typeEncrypt, ChoiceBox<String> typeHash, TextField password, TextField encryptPassword, TextField community, TextField username) {
            stateForOldVersion(typeEncrypt, typeHash, password, encryptPassword, community, username);
        }
    },
    v3 {
        @Override
        public void setVisible(ChoiceBox<String> typeEncrypt, ChoiceBox<String> typeHash, TextField password, TextField encryptPassword, TextField community, TextField username) {
            typeEncrypt.setDisable(false);
            typeHash.setDisable(false);
            community.setDisable(true);
            username.setDisable(false);
        }
    };

    private static void stateForOldVersion(ChoiceBox<String> typeEncrypt, ChoiceBox<String> typeHash, TextField password, TextField encryptPassword, TextField community, TextField username) {
        typeEncrypt.setDisable(true);
        typeHash.setDisable(true);
        community.setDisable(false);
        username.setDisable(true);
        encryptPassword.setDisable(true);
        password.setDisable(true);
    }

    public abstract void setVisible(ChoiceBox<String> typeEncrypt,
                                    ChoiceBox<String> typeHash,
                                    TextField password, TextField encryptPassword, TextField community, TextField username);
}
