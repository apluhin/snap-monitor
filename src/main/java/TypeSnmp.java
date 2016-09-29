import java.util.Map;

public interface TypeSnmp {
    String getEncryptionPassword();

    String getHashPassword();

    String getUsername();

    String typeHash();

    String typeEncript();

    String getCommunity();


}
