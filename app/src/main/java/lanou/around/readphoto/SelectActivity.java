package lanou.around.readphoto;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.bean.PhotoBean;
import lanou.around.bean.PictureBean;

import static lanou.around.readphoto.CanViewPagerActivity.ARRAY_LIST;
import static lanou.around.readphoto.CanViewPagerActivity.ARRAY_STRING;
import static lanou.around.readphoto.CanViewPagerActivity.POSITION_KEY;

public class SelectActivity extends BaseActivity implements View.OnClickListener {


    public static String READ_PHOTO = "READ_PHOTO";
    private RecyclerView photoRecyclerview;
    private RelativeLayout selectToolbar;
    private PhotoAdapter photoAdapter;
    private RecyclerView recyclerViewBottom;
    private RelativeLayout finish;
    private HorizontalAdapter horizontalAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView text_select;
    private Button btn;
    private ListView listview;
    private View selectPop;
    private List<PictureBean> list1;
    private List<String> strings;


    @Override
    protected int setContentView() {
        return R.layout.activity_select2;
    }

    @Override
    protected void initViews() {
        btn = findView(R.id.btn_selectalbum);
        photoRecyclerview = findView(R.id.photo_recyclerview);
        selectToolbar = findView(R.id.select_toolbar);
        recyclerViewBottom = findView(R.id.recyclerView_bottom);
        finish = findView(R.id.finish);
        text_select = findView(R.id.text_select);

        selectPop = LayoutInflater.from(this).inflate(R.layout.select_pop, null);

        listview = findView(selectPop, R.id.listview_select);

    }

    @Override
    protected void initListeners() {
        btn.setOnClickListener(this);
        finish.setOnClickListener(this);


    }

    @Override
    protected void initData() {

        list1 = getAllPhoto(this);
        photoAdapter = new PhotoAdapter(photoRecyclerview, R.layout.photo_item);

        photoRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));

        photoRecyclerview.setAdapter(photoAdapter);
        strings = getIntent().getStringArrayListExtra(READ_PHOTO);

        setAdapter(list1);


        photoAdapter.setInterPhoto(new PhotoAdapter.onInterPhoto() {
            @Override
            public void removephoto(String string, int age) {

                horizontalAdapter.removeItem(string);

                text_select.setText(age + "/12");

            }

            @Override
            public void addphoto(String string, int age) {

                horizontalAdapter.addItem(horizontalAdapter.getList().size(), string);
                text_select.setText(age + 1 + "/12");
            }

            @Override
            public void onImgListener(int position) {

                Intent intent = new Intent(SelectActivity.this, CanViewPagerActivity.class);
                List<PictureBean> pictureBeen = photoAdapter.getList();

                intent.putStringArrayListExtra(ARRAY_STRING, (ArrayList<String>) strings);
                intent.putExtra(ARRAY_LIST, (Serializable) pictureBeen);
                intent.putExtra(POSITION_KEY, position);
                startActivityForResult(intent, 200);
            }
        });

        horizontalAdapter = new HorizontalAdapter(recyclerViewBottom);

        horizontalAdapter.setOnItem(new HorizontalAdapter.onItem() {
            @Override
            public void setOnItemListenner(int position, String str) {
                photoAdapter.removeCheck(position, str);
                horizontalAdapter.removeItem(position);
            }
        });
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewBottom.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < getIntent().getStringArrayListExtra(READ_PHOTO).size(); i++) {
            Log.d("SelectActivity", getIntent().getStringArrayListExtra(READ_PHOTO).get(i));
        }

        horizontalAdapter.setList(getIntent().getStringArrayListExtra(READ_PHOTO));
        recyclerViewBottom.setAdapter(horizontalAdapter);


    }

    private void setAdapter(List<PictureBean> list1) {


        for (int i = 0; i < list1.size(); i++) {
            if (strings.contains(list1.get(i).path)) {
                list1.get(i).check = true;
            }
        }

        text_select.setText(strings.size() + "/12");
        photoAdapter.setList(list1);
        photoAdapter.setCheckPhoto(strings);

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


                List<String> list = photoAdapter.getCheckPhoto();
                Log.d("SelectActivity", "list.size():" + list.size());
                Intent intent = getIntent();
                intent.putStringArrayListExtra(READ_PHOTO, (ArrayList<String>) list);
                setResult(100, intent);
                onBackPressed();


                break;


            case R.id.btn_selectalbum:

                final PopupWindow popupWindow = new PopupWindow(selectPop, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
                // 设置动画效果
                popupWindow.setAnimationStyle(R.style.j8);

                popupWindow.setBackgroundDrawable(new BitmapDrawable());

                popupWindow.showAsDropDown(btn);

//
                btn.setFocusable(true);//comment by danielinbiti,设置view能够接听事件，标注1
                btn.setFocusableInTouchMode(true);


                btn.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            if (popupWindow != null) {
                                popupWindow.dismiss();
                            }
                        }
                        return false;
                    }
                });

                final PhotoBean p = new PhotoBean();

                p.bitList.addAll(list1);
                p.name = "所有照片";

                final List<PhotoBean> mPhotoBean = getPhotoAlbum(this);

                mPhotoBean.add(0, p);

                SelectPopAdapter selectadapter = new SelectPopAdapter(this, mPhotoBean);

                listview.setAdapter(selectadapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        popupWindow.dismiss();


                        setAdapter(mPhotoBean.get(position).bitList);


                    }
                });
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (201 == resultCode) {

            ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(ARRAY_STRING);
            List<PictureBean> pictureBeen = (List<PictureBean>) data.getSerializableExtra(ARRAY_LIST);
            photoAdapter.setList(pictureBeen);
            photoAdapter.setCheckPhoto(stringArrayListExtra);
            horizontalAdapter.setList(stringArrayListExtra);
            text_select.setText(stringArrayListExtra.size() + "/12");

        }


    }
}

