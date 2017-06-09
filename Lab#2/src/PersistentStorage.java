import java.io.Serializable;

class PersistentStorage implements Serializable {
    private static final long serialVersionUID = 123L;
    private String s1;
    private String s2;

    public PersistentStorage(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getInstanceSecret() {
        return s2 + s1;
    }

    public String getComputedSecret() {
        return System.getProperty("user.dir");
    }
}
