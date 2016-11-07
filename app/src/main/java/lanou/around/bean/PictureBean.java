package lanou.around.bean;

import java.io.Serializable;

public class PictureBean implements Serializable {

    public int photoID;
    public boolean select;
    public boolean check;
    public String path;

    public PictureBean(int id, String path) {
        photoID = id;
        select = false;
        this.path = path;
    }

}