package lanou.around.main;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.bean.PhotoBean;
import lanou.around.bean.PictureBean;

public class SelectActivity extends BaseActivity implements View.OnClickListener {


    private RecyclerView photoRecyclerview;
    private boolean has = true;
    private int statusBarHeight;
    private RelativeLayout selectToolbar;
    private PhotoAdapter photoAdapter;
    private RecyclerView recyclerViewBottom;
    private RelativeLayout finish;

    @Override
    protected int setContentView() {
        return R.layout.activity_select2;
    }

    @Override
    protected void initViews() {
        photoRecyclerview = findView(R.id.photo_recyclerview);
        selectToolbar = findView(R.id.select_toolbar);
        recyclerViewBottom = findView(R.id.recyclerView_bottom);
        finish = findView(R.id.finish);

    }

    @Override
    protected void initListeners() {

        finish.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        List<PhotoBean> list = getPhotoAlbum(this);
        List<PictureBean> list1 = getAllPhoto(this);
        for (int i = 0; i < list1.size(); i++) {
            Log.d("sss", list1.get(i).path);
        }
        Log.d("sss", String.valueOf(list.size()));

        photoAdapter = new PhotoAdapter(photoRecyclerview, R.layout.photo_item, list1);
        photoRecyclerview.setLayoutManager(new GridLayoutManager(this,3));
        photoRecyclerview.setAdapter(photoAdapter);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (has) {
            Rect frame = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            // 状态栏高度
            statusBarHeight = frame.top;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) selectToolbar.getLayoutParams();
            params.setMargins(0, statusBarHeight, 0, 0);
            selectToolbar.setLayoutParams(params);
            has = false;
        }


    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish:

                List<PictureBean> list = photoAdapter.getCheckPhoto();
                ArrayList<String> arraylist = new ArrayList<>();
                for (int i = 0; i < photoAdapter.getCheckPhoto().size(); i++) {
                    arraylist.add(list.get(i).path);
                }

                Intent intent = getIntent();
                intent.putStringArrayListExtra("ddd", arraylist);
                setResult(100, intent);
                onBackPressed();


                break;


        }
    }
}

