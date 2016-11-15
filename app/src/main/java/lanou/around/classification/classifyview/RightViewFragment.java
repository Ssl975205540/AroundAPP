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

import static lanou.around.classification.search.SearchActivity.CATE_ID_RIGHT;
import static lanou.around.classification.search.SearchActivity.CATE_NAME_RIGHT;
import static lanou.around.classification.search.SearchActivity.RIGHT;

/**
 * Created by dllo on 16/10/25.
 */
public class RightViewFragment extends BaseFragment {

    private GridView gridView;
    private List<ClassifyViewBean.RespDataBean> mDataBeanList;
    private ClassifyViewBean mDatas;


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

    }


    public void setDatas(ClassifyViewBean datas) {
        mDatas = datas;

    }

    class gridViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(context, "arg2:" + arg2, Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString(CATE_ID_RIGHT, mDataBeanList.get(arg2).getCateId());
            bundle.putString(CATE_NAME_RIGHT, mDataBeanList.get(arg2).getCateName());
            bundle.putInt(RIGHT, 3);
            IntentUtils.getIntents().Intent(context, SearchActivity.class, bundle);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()){
            mDataBeanList = new ArrayList<>();
            for (int i = 16; i < 19; i++) {
                mDataBeanList.add(mDatas.getRespData().get(i));
            }
            GridViewPagerAdapter gridViewAdapter = new GridViewPagerAdapter(context, mDataBeanList);
            gridView.setAdapter(gridViewAdapter);
        }

    }
}
