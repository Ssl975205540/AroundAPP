package lanou.around.readphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.CanOnItemListener;
import lanou.around.base.BaseActivity;
import lanou.around.widget.MyRecyclerView;

import static lanou.around.app.AroundAPP.context;

public class ReleaseActivity extends BaseActivity {

    private RecyclerView recyclerview_release;
    private ReleaseAdapter galleryAdapter;
    private RelativeLayout release_toolbar;
    private List<String> list;
    private ImageAdapter imageAdapter;
    private ImageView img_release;
    private List<String> strings;
    private List<String> list1;

    @Override
    protected int setContentView() {

        return R.layout.activity_release;


    }

    @Override
    protected void initViews() {
        context = this;
        recyclerview_release = findView(R.id.recyclerview_release);
        release_toolbar = findView(R.id.release_toolbar);
        img_release = findView(R.id.img_release);
    }

    @Override
    protected void initListeners() {
        img_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReleaseActivity.this, SelectActivity.class);


                List<String> strings = new ArrayList<String>();

                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i).length() > 5) {
                        strings.add(list1.get(i));
                    }
                }
                intent.putStringArrayListExtra(SelectActivity.READ_PHOTO, (ArrayList<String>) strings);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void initData() {


        list = new ArrayList<>();

        strings = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            strings.add("");
        }
        galleryAdapter = new ReleaseAdapter(this, strings);

        recyclerview_release.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

        recyclerview_release.setAdapter(galleryAdapter);
        galleryAdapter.setOnItemClick(new MyRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {


                if (position == 0) {

                    Intent intent = new Intent(ReleaseActivity.this, SelectActivity.class);
                    intent.putStringArrayListExtra(SelectActivity.READ_PHOTO, (ArrayList<String>) list);

                    startActivityForResult(intent, 101);


                }


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100) {
            list1 = data.getStringArrayListExtra(SelectActivity.READ_PHOTO);
            if (list1.size() == 0) {

                Toast.makeText(this, "已取消", Toast.LENGTH_SHORT).show();
                galleryAdapter.setList(strings);
                recyclerview_release.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

                recyclerview_release.setAdapter(galleryAdapter);

            } else {

                imageAdapter = new ImageAdapter(recyclerview_release);

                int size = list1.size();
                if (size == 12) {

                    Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.mj);
                    img_release.setImageBitmap(bmp);
                    img_release.setVisibility(View.VISIBLE);
                    Log.d("ReleaseActivity", "img_release.getHeight():" + img_release.getWidth());
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerview_release.getLayoutParams();
                    layoutParams.setMargins(0, 0, img_release.getWidth(), 0);
                    recyclerview_release.setLayoutParams(layoutParams);


                } else {
                    for (int i = 0; i < 12 - size; i++) {
                        if (i == 0) {
                            if (size > 3) {
                                list1.add("");
                                Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.mj);
                                img_release.setImageBitmap(bmp);
                                img_release.setVisibility(View.VISIBLE);

                            } else {
                                img_release.setVisibility(View.GONE);

                                list1.add(".");
                            }

                        } else {
                            list1.add("");
                        }
                    }
                }

                imageAdapter.setList(list1);
                recyclerview_release.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerview_release.setAdapter(imageAdapter);
                imageAdapter.setOnItemListener(new CanOnItemListener() {
                    @Override
                    public void onItemChildClick(View view, int position) {

                    }

                    @Override
                    public boolean onItemChildLongClick(View view, int position) {
                        return false;
                    }

                    @Override
                    public void onItemChildCheckedChanged(CompoundButton view, int position, boolean isChecked) {

                    }

                    @Override
                    public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                    }

                    @Override
                    public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position) {
                        return false;
                    }
                });
                imageAdapter.setOnItem(new ImageAdapter.onItem() {
                    @Override
                    public void setOnItemListenner(int position) {

                        if (list1.get(position).equals(".")) {

                            Intent intent = new Intent(ReleaseActivity.this, SelectActivity.class);


                            List<String> strings = new ArrayList<String>();

                            for (int i = 0; i < list1.size(); i++) {
                                if (list1.get(i).length() > 5) {
                                    strings.add(list1.get(i));
                                }
                            }
                            intent.putStringArrayListExtra(SelectActivity.READ_PHOTO, (ArrayList<String>) strings);
                            startActivityForResult(intent, 101);
                        }

                    }
                });
            }

        }

    }


}