package lanou.around.classification;

/**
 * Created by dllo on 16/8/31.
 */
public class ClassifyBean {
    private String name;
    private String message;
    private int type;

    public ClassifyBean(String name, String message, int type) {
        this.name = name;
        this.message = message;
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
