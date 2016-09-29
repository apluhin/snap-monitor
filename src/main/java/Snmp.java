import java.util.Map;

public class Snmp {

    private final VersionSnmp versionSnmp;


    public Snmp(Map<String, String> map) {
        versionSnmp = Version.valueOf(map.get("version"));
        versionSnmp.setMap(map);
    }

    public String getEncryptionPassword() {
        return versionSnmp.getEncryptionPassword();
    }

    public String getHashPassword() {
        return versionSnmp.getHashPassword();
    }

    public String getUsername() {
        return versionSnmp.getUsername();
    }

    public String getTypeHash() {
        return versionSnmp.typeHash();
    }

    public String getTypeEncript() {
        return versionSnmp.typeEncript();
    }

    public String getCommunity() {
        return versionSnmp.getCommunity();
    }

    public void setMap(Map<String, String> map) {
        versionSnmp.setMap(map);
    }
}
