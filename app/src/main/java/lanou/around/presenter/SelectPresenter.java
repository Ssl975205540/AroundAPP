package lanou.around.presenter;

import android.content.Intent;

import java.util.List;

import lanou.around.aroundinterface.Ireadphoto;
import lanou.around.bean.PhotoBean;
import lanou.around.bean.PictureBean;
import lanou.around.model.SelectModel;

/**
 * Created by dllo on 16/11/9.
 */

public class SelectPresenter {

    private Ireadphoto ireadphoto;
    private final SelectModel selectModel;

    public SelectPresenter(Ireadphoto ireadphoto) {
        this.ireadphoto = ireadphoto;
        selectModel = new SelectModel();
        readphoto();
    }

    private void readphoto() {

        selectModel.init(new Ireadphoto() {
            @Override
            public void setAllPhoto(List<PictureBean> list) {
                ireadphoto.setAllPhoto(list);
            }

            @Override
            public void setPhotoAlbum(List<PhotoBean> list) {
                ireadphoto.setPhotoAlbum(list);

            }

            @Override
            public void addPhotoPath(String path) {
                ireadphoto.addPhotoPath(path);
            }
        });


    }


    public void addPhoto(Intent data) {
        selectModel.addPhoto(data);


    }
}
