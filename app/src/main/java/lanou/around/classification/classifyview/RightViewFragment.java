package lanou.around.classification.classifyview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.classification.search.SearchActivity;
import lanou.around.presenter.ClassifyViewPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.recycle.IntentUtils;
import static lanou.around.classification.search.SearchActivity.CATE_ID_RIGHT;
import static lanou.around.classification.search.SearchActivity.CATE_NAME_RIGHT;
import static lanou.around.classification.search.SearchActivity.RIGHT;

/**
 * Created by dllo on 16/10/25.
 */
public class RightViewFragment extends BaseFragment implements InterView {

    private GridView gridView;
    private List<ClassifyViewBean.RespDataBean> mDataBeanList;


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
        ClassifyViewPresenter presenter = new ClassifyViewPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_CHILD_CATES_LOGIC, ClassifyViewBean.class);
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public void onResponse(Object t) {
        ClassifyViewBean classifyViewBean = (ClassifyViewBean) t;
        mDataBeanList = new ArrayList<>();
        for (int i = 16; i < 19; i++) {
            mDataBeanList.add(classifyViewBean.getRespData().get(i));
        }
        GridViewPagerAdapter gridViewAdapter = new GridViewPagerAdapter(context,mDataBeanList);
        gridView.setAdapter(gridViewAdapter);
    }


    @Override
    public void onError() {

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



}
