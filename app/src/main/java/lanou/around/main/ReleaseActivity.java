package lanou.around.main;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.tools.db.CanHolderHelper;

import static lanou.around.app.AroundAPP.context;

public class ReleaseActivity extends BaseActivity {


    private int H;

    private File saveFile;

    private RecyclerView recyclerview_release;
    private int statusBarHeight;
    private boolean has = true;
    private ReleaseAdapter galleryAdapter;
    private RelativeLayout release_toolbar;
    private List<String> list;

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

        for (int i = 0; i < 12; i++) {
            list.add("");
        }
        galleryAdapter = new ReleaseAdapter(recyclerview_release, R.layout.rekease_item, list);
        recyclerview_release.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

        recyclerview_release.setAdapter(galleryAdapter);


        galleryAdapter.setOnItemClickLitener(new ReleaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(CanHolderHelper view, int position) {


                if (position == 0) {

                    Intent intent = new Intent(ReleaseActivity.this, SelectActivity.class);
                    startActivityForResult(intent, 101);


                }


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100) {

            if (data != null && data.hasExtra("ddd")) {
                List<String> list1 = data.getStringArrayListExtra("ddd");
                Log.d("list1", String.valueOf(list1.size()));

                List<String> list2 = galleryAdapter.getList();

                List<String> list3 = new ArrayList<>();
                Log.d("list2", String.valueOf(list2.size()));


                for (int i = 0; i < list2.size(); i++) {
                    if (list2.get(i).length() > 0) {
                        list3.add(list2.get(i));
                    }
                }

                int c = list3.size();

                if (list1.size() > 12 - c) {
                    for (int i = 0; i < 12 - c; i++) {
                        list3.add(list1.get(i));
                    }

                } else {
                    for (int i = 0; i < list1.size(); i++) {
                        list3.add(list1.get(i));
                    }
                }

                if (list1.size() < 12) {

                }


                galleryAdapter.setList(list1);
            }


        }
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