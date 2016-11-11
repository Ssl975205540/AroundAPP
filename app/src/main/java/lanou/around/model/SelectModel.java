package lanou.around.model;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.Ireadphoto;
import lanou.around.bean.PhotoBean;
import lanou.around.bean.PictureBean;

/**
 * Created by dllo on 16/11/9.
 */

public class SelectModel {


    private Ireadphoto ireadphoto;
    private Bitmap photo;

    public void init(Ireadphoto ireadphoto) {
        this.ireadphoto = ireadphoto;
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
        YouAsyncTask youAsyncTask = new YouAsyncTask();
        youAsyncTask.execute();


    }


    public List<PictureBean> getAllPhoto(Context context) {
        List<PictureBean> list = new ArrayList<>();

        Cursor cursor = MediaStore.Images.Media.query(
                context.getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES);

        while (cursor.moveToNext()) {

            String path = cursor.getString(1);
            String id = cursor.getString(3);
            String dir_id = cursor.getString(4);
            String dir = cursor.getString(5);
            PictureBean pictureBean = new PictureBean(Integer.parseInt(id), path);
            list.add(pictureBean);

        }


        return list;
    }


    static final String[] STORE_IMAGES = {
            MediaStore.Images.Media.DISPLAY_NAME, // 名称
            MediaStore.Images.Media.DATA, MediaStore.Images.Media.LONGITUDE, // 经度
            MediaStore.Images.Media._ID, // id
            MediaStore.Images.Media.BUCKET_ID, // dir id 目录
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME // dir name 目录名字
    };


    public List<PhotoBean> getPhotoAlbum(Context context) {
        List<PhotoBean> albumList = new ArrayList<PhotoBean>();
        Cursor cursor = MediaStore.Images.Media.query(
                context.getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES);
        Map<String, PhotoBean> countMap = new HashMap<String, PhotoBean>();
        PhotoBean pa = null;
        while (cursor.moveToNext()) {
            String path = cursor.getString(1);
            Log.d("path", path);
            String id = cursor.getString(3);
            String dir_id = cursor.getString(4);
            String dir = cursor.getString(5);
            if (!new File(path).exists()) {
                continue;
            }

            if (!countMap.containsKey(dir_id)) {
                pa = new PhotoBean();
                pa.name = dir;
                pa.bitmap = (Integer.parseInt(id));
                pa.count = "1";
                pa.bitList.add(new PictureBean(Integer.valueOf(id), path));
                countMap.put(dir_id, pa);
            } else {
                pa = countMap.get(dir_id);
                pa.count = (String.valueOf(Integer.parseInt(pa.count) + 1));
                pa.bitList.add(new PictureBean(Integer.valueOf(id), path));
            }
        }
        cursor.close();
        Iterable<String> it = countMap.keySet();
        for (String key : it) {
            albumList.add(countMap.get(key));
        }
        return albumList;
    }

    public void addPhoto(Intent data) {
        destoryBimap();
        Uri uri = data.getData();

        if (uri != null) {
            this.photo = BitmapFactory.decodeFile(uri.getPath());
        }
        if (this.photo == null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                this.photo = (Bitmap) bundle.get("data");
            } else {

                return;
            }
        }

        Transformation();


    }


    class MyAsyncTask extends AsyncTask<Void, Void, List<PictureBean>> {


        @Override
        protected List<PictureBean> doInBackground(Void... params) {


            return getAllPhoto(AroundAPP.context);
        }


        @Override
        protected void onPostExecute(List<PictureBean> pictureBeen) {
            super.onPostExecute(pictureBeen);
            ireadphoto.setAllPhoto(pictureBeen);

        }
    }

    class YouAsyncTask extends AsyncTask<Void, Void, List<PhotoBean>> {


        @Override
        protected List<PhotoBean> doInBackground(Void... params) {


            return getPhotoAlbum(AroundAPP.context);
        }


        @Override
        protected void onPostExecute(List<PhotoBean> pictureBeen) {
            super.onPostExecute(pictureBeen);
            ireadphoto.setPhotoAlbum(pictureBeen);

        }
    }

    public void Transformation() {


        FileOutputStream fileOutputStream = null;
        try {
            // 获取 SD 卡根目录
            String saveDir = Environment.getExternalStorageDirectory() + "/meitian_photos";
            // 新建目录
            File dir = new File(saveDir);
            if (!dir.exists()) dir.mkdir();
            // 生成文件名
            SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            String filename = "MT" + (t.format(new Date())) + ".jpg";
            // 新建文件
            File file = new File(saveDir, filename);
            // 打开文件输出流
            fileOutputStream = new FileOutputStream(file);
            // 生成图片文件
            this.photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            // 相片的完整路径
            String picPath = file.getPath();


            ireadphoto.addPhotoPath(picPath);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void destoryBimap() {
        if (photo != null && !photo.isRecycled()) {
            photo.recycle();
            photo = null;
        }
    }
}
