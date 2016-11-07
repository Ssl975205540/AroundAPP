package lanou.around.main;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.widget.MyRecyclerView;

import static lanou.around.app.AroundAPP.context;

public class ReleaseActivity extends BaseActivity {

    private RecyclerView recyclerview_release;
    private int statusBarHeight;
    private boolean has = true;
    private ReleaseAdapter galleryAdapter;
    private RelativeLayout release_toolbar;
    private List<String> list;
    private ImageAdapter imageAdapter;

    @Override
    protected int setContentView() {

        return R.layout.activity_release;


    }

    @Override
    protected void initViews() {
        context = this;
        recyclerview_release = findView(R.id.recyclerview_release);
        release_toolbar = findView(R.id.release_toolbar);

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {


        list = new ArrayList<>();

        final List<String> strings = new ArrayList<>();
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
                    intent.putStringArrayListExtra("ddd", (ArrayList<String>) strings);

                    startActivityForResult(intent, 101);


                }


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100) {

            List<String> list1 = data.getStringArrayListExtra("ddd");

            imageAdapter = new ImageAdapter(recyclerview_release);
            if (list.size() == 0) {
                List<String> list2 = new ArrayList<>();

                if (list1 != null) {


                    int list1Size = list1.size();

                    if (list1Size >= 12) {
                        list2.addAll(list1);
                    }
                    if (list1Size < 12) {
                        list2.addAll(list1);
                        for (int i = 0; i < 12 - list1Size; i++) {

                            if (i == 0) {
                                list2.add(".");

                            } else {
                                list2.add("");

                            }
                        }
                    }


                    imageAdapter.setList(list2);

                    recyclerview_release.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    recyclerview_release.setAdapter(imageAdapter);
                }
                list = list2;

            } else {
                List<String> list2 = new ArrayList<>();

                for (String s :
                        list1) {
                    if(list.contains(s)){

                        list1.remove(s);
                    }

                }

                int listSize = list.size();
                for (int i = 0; i < listSize; i++) {
                    if (list.get(i).length() > 1) {
                        list2.add(list.get(i));
                    }

                }


                if (list1 != null) {
                    int list1Size = list1.size();

                    int listSize1 = list2.size();
                    if (list1Size >= 12 - listSize1) {
                        list2.addAll(list1.subList(0, 12 - listSize1));
                    }

                    if (list1Size < 12 - listSize1) {
                        list2.addAll(list1);
                        Log.d("ReleaseActivity", "list2.size():" + list2.size());
                       int s=  list2.size();
                        for (int j = 0; j < 12 - s; j++) {
                            if (j == 0) {
                                list2.add(".");
                            } else {
                                list2.add("");
                            }
                        }

                    }
                    Log.d("ReleaseActivity", "list2.size():" + list2.size());
                    imageAdapter.setList(list2);
                    recyclerview_release.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    recyclerview_release.setAdapter(imageAdapter);
                }

                list = list2;
            }


        }


        imageAdapter.setOnItem(new ImageAdapter.onItem() {
            @Override
            public void setOnItemListenner(int position) {

                if (list.get(position).equals(".")) {

                    Intent intent = new Intent(ReleaseActivity.this, SelectActivity.class);

                    Log.d("ReleaseActivity", "list.size():" + list.size());
                    intent.putStringArrayListExtra("ddd", (ArrayList<String>) list);
                    startActivityForResult(intent, 101);


                }
            }
        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (has) {
            Rect frame = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            // 状态栏高度
            statusBarHeight = frame.top;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) release_toolbar.getLayoutParams();
            params.setMargins(0, statusBarHeight, 0, 0);
            release_toolbar.setLayoutParams(params);
            has = false;
        }


    }
}