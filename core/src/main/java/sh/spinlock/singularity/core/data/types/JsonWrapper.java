package sh.spinlock.singularity.core.data.types;

public class JsonWrapper {
    private final String json;

    public JsonWrapper(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }
}
