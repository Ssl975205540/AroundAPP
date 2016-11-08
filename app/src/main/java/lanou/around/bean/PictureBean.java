package lanou.around.bean;

import java.io.Serializable;

public class PictureBean implements Serializable {

    public int photoID;

    public boolean check = false;
    public String path;

    public PictureBean(int id, String path) {
        photoID = id;

        this.path = path;
    }

}