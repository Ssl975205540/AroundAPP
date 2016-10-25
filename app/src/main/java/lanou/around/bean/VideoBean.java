package lanou.around.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dllo on 16/10/24.
 */

public class VideoBean {

    private int anInt;

    private String url;

    public int getAnInt() {
        return anInt;
    }

    public String getUrl() {
        return url;
    }

    public void setAnInt(int anInt) {

        this.anInt = anInt;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
