import java.util.Map;

public enum Version implements VersionSnmp {

    v1 {
        private Map<String, String> map = null;

        public String getEncryptionPassword() {
            throw new RuntimeException("Use communitt");
        }

        public String getHashPassword() {
            throw new RuntimeException("Use communitt");
        }

        public String getCommunity() {
            return map.get("community");
        }

        public void setMap(Map<String, String> map) {
            if(map == null) return;
            this.map = map;
        }

        public String getUsername() {
            throw new RuntimeException("Use communitt");
        }

        public String typeHash() {
            return "None";
        }

        public String typeEncript() {
            return "None";
        }
    },
    v2 {
        private Map<String, String> map;

        public void setMap(Map<String, String> map) {
            if(map == null) return;
            this.map = map;
        }

        public String getCommunity() {
            return null;
        }

        public String getEncryptionPassword() {
            return null;
        }

        public String getHashPassword() {
            return null;
        }

        public String getUsername() {
            return null;
        }

        public String typeHash() {
            return null;
        }

        public String typeEncript() {
            return null;
        }
    },
    v3 {
        private Map<String, String> map;

        public void setMap(Map<String, String> map) {
            if(map == null) return;
            this.map = map;
        }

        public String getEncryptionPassword() {
           return map.get("passwordEncryt");
        }

        public String getHashPassword() {
            return map.get("passwordHash");
        }

        public String getUsername() {
            return map.get("username");
        }

        public String getCommunity() {
            throw new UnsupportedOperationException("SNMP V3 use authenticate");
        }

        public String typeHash() {
            return map.get("typeHash");
        }

        public String typeEncript() {
            return map.get("typeEncript");
        }
    };


}
