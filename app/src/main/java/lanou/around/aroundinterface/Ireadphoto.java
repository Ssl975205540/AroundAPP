package lanou.around.aroundinterface;

import java.util.List;

import lanou.around.bean.PhotoBean;
import lanou.around.bean.PictureBean;

/**
 * Created by dllo on 16/11/9.
 */

public interface Ireadphoto {

    void setAllPhoto(List<PictureBean> list);

    void setPhotoAlbum(List<PhotoBean> list);

    void addPhotoPath(String path);
}
