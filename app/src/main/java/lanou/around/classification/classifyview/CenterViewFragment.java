package lanou.around.classification.classifyview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.classification.search.SearchActivity;
import lanou.around.tools.util.IntentUtils;

import static lanou.around.R.id.gridView;
import static lanou.around.app.AroundAPP.context;
import static lanou.around.classification.search.SearchActivity.CATE_ID_CENTER;
import static lanou.around.classification.search.SearchActivity.CATE_NAME_CENTER;
import static lanou.around.classification.search.SearchActivity.CENTER;

/**
 * Created by dllo on 16/10/25.
 */
public class CenterViewFragment extends BaseFragment {

    private GridView gridView;
    private List<ClassifyViewBean.RespDataBean> mRespDataBeanList;
    private ClassifyViewBean mDatas;
    private boolean mBoolean = true;

    @Override
    protected int setContentView() {
        return R.layout.grid_classify_view;
    }

    @Override
    protected void initViews() {
        gridView = findView(R.id.gview_classify);
    }

    @Override
    protected void initListeners() {
        // 为GridView设定监听器
        gridView.setOnItemClickListener(new gridViewListener());
    }


    @Override
    protected void initData() {
        if (mDatas != null) {
            DatasAll();
        }
    }


    public void setDatas(ClassifyViewBean datas) {
        mDatas = datas;
        DatasAll();
    }

    private class gridViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(context, "arg2:" + arg2, Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString(CATE_ID_CENTER, mRespDataBeanList.get(arg2).getCateId());
            bundle.putString(CATE_NAME_CENTER, mRespDataBeanList.get(arg2).getCateName());
            bundle.putInt(CENTER, 2);
            IntentUtils.getIntents().Intent(context, SearchActivity.class, bundle);

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mBoolean){
            if (isVisibleToUser ) {
                DatasAll();
            }
        }
        mBoolean = false;
    }

    private void DatasAll() {
        mRespDataBeanList = new ArrayList<>();
        for (int i = 8; i < 16; i++) {
            mRespDataBeanList.add(mDatas.getRespData().get(i));
        }
        GridViewPagerAdapter gridViewAdapter = new GridViewPagerAdapter(context, mRespDataBeanList);
        gridView.setAdapter(gridViewAdapter);
    }
}
