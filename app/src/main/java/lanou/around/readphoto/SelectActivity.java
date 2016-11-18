package lanou.around.readphoto;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.Ireadphoto;
import lanou.around.base.BaseActivity;
import lanou.around.bean.PhotoBean;
import lanou.around.bean.PictureBean;
import lanou.around.presenter.SelectPresenter;

import static lanou.around.readphoto.CanViewPagerActivity.ARRAY_LIST;
import static lanou.around.readphoto.CanViewPagerActivity.ARRAY_STRING;
import static lanou.around.readphoto.CanViewPagerActivity.POSITION_KEY;

public class SelectActivity extends BaseActivity implements View.OnClickListener, Ireadphoto {


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
    public static final int TAKE_PHOTO = 80;
    private List<PhotoBean> mPhotoBean;
    private boolean istrue = false;
    private SelectPresenter selectPresenter;
    private Button btnBack;


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
        btnBack = findView(R.id.btn_back);
    }

    @Override
    protected void initListeners() {
        btn.setOnClickListener(this);
        finish.setOnClickListener(this);
        btnBack.setOnClickListener(this);

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
            public void intentCarture() {


                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, TAKE_PHOTO);
                }
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



        horizontalAdapter.setOnItem(new HorizontalAdapter.onItem() {
            @Override
            public void setOnItemListenner(int position, String str) {
                photoAdapter.removeCheck(position, str);
                horizontalAdapter.removeItem(position);
                text_select.setText(photoAdapter.getList().size() + "/12");
            }
        });
    }

    @Override
    protected void initData() {

        selectPresenter = new SelectPresenter(this);


        newAdapter();

        setLayoutManager();


        strings = getIntent().getStringArrayListExtra(READ_PHOTO);

        horizontalAdapter.setList(getIntent().getStringArrayListExtra(READ_PHOTO));


        photoRecyclerview.setAdapter(photoAdapter);

        recyclerViewBottom.setAdapter(horizontalAdapter);


    }



    private void newAdapter() {
        photoAdapter = new PhotoAdapter(photoRecyclerview, R.layout.photo_item);

        horizontalAdapter = new HorizontalAdapter(recyclerViewBottom);



    }

    private void setLayoutManager() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 3 : 1;
            }
        });

        photoRecyclerview.setLayoutManager(layoutManager);


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewBottom.setLayoutManager(linearLayoutManager);




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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish:


                List<String> list = horizontalAdapter.getList();
                Intent intent = getIntent();
                intent.putStringArrayListExtra(READ_PHOTO, (ArrayList<String>) list);
                setResult(100, intent);
                finish();


                break;


            case R.id.btn_back:


                finish();

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


                if (istrue == false) {
                    mPhotoBean.add(0, p);
                    istrue = true;
                }

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
            notyfi(pictureBeen, stringArrayListExtra, stringArrayListExtra, stringArrayListExtra.size() + "/12");
        }

        if (data != null) {
            switch (requestCode) {
                case TAKE_PHOTO:


                    selectPresenter.addPhoto(data);

                    break;

            }
        }
    }



    private void notyfi(List<PictureBean> pictureBeen, ArrayList<String> stringArrayListExtra, ArrayList<String> stringArrayListExtra1, String s) {

            photoAdapter.setList(pictureBeen);
            photoAdapter.setCheckPhoto(stringArrayListExtra);
            horizontalAdapter.setList(stringArrayListExtra1);
            text_select.setText(s);


    }


    @Override
    public void setAllPhoto(List<PictureBean> list) {

        list1 = list;
        setAdapter(list);
    }

    @Override
    public void setPhotoAlbum(List<PhotoBean> list) {

        mPhotoBean = list;
    }

    @Override
    public void addPhotoPath(String picPath) {
        PictureBean pictureBean = new PictureBean(1, picPath);
        pictureBean.check = true;

        List<String> list = photoAdapter.getCheckPhoto();
        photoAdapter.addFirstItem(pictureBean);
        horizontalAdapter.addLastItem(picPath);
        list.add(0, picPath);
        text_select.setText(list.size() + "/12");
        photoAdapter.setCheckPhoto(list);
    }



}

