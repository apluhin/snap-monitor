import java.util.Map;

public interface VersionSnmp {
    String getEncryptionPassword();

    String getHashPassword();

    String getUsername();

    String typeHash();

    String typeEncript();

    String getCommunity();

    void setMap(Map<String, String> map);
}
