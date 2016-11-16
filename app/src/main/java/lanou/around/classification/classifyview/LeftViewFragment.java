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
import lanou.around.presenter.ClassifyViewPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.util.IntentUtils;

import static lanou.around.app.AroundAPP.context;
import static lanou.around.classification.search.SearchActivity.CATE_ID_LEFT;
import static lanou.around.classification.search.SearchActivity.CATE_NAME_LEFT;
import static lanou.around.classification.search.SearchActivity.LEFT;
import static org.cybergarage.http.HTTP.HEAD;

/**
 * Created by dllo on 16/10/22.
 */
public class LeftViewFragment extends BaseFragment {


    private GridView gridView;
    private List<ClassifyViewBean.RespDataBean> mRespDataBeen;
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

        if(mDatas != null){
            DataAdd();
        }
    }

    public void setDatas(ClassifyViewBean datas) {
        mDatas = datas;
        DataAdd();
    }

    public class gridViewListener implements AdapterView.OnItemClickListener {
        Bundle bundle = new Bundle();
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(context, "arg2:" + arg2, Toast.LENGTH_SHORT).show();
            if (1 == arg2) {
                if (mRespDataBeen.get(1).getMurl() != null){
                    bundle.putString("url", mRespDataBeen.get(1).getMurl());
                    IntentUtils.getIntents().Intent(context,DigitWebActivity.class, bundle);
                }
            } else {
                bundle.putString(CATE_ID_LEFT,mRespDataBeen.get(arg2).getCateId());
                bundle.putString(CATE_NAME_LEFT,mRespDataBeen.get(arg2).getCateName());
                bundle.putInt(LEFT,1);
                IntentUtils.getIntents().Intent(context,  SearchActivity.class,bundle);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(mBoolean){
            if (isVisibleToUser && mDatas != null) {
                DataAdd();
            }
            mBoolean = false;
        }
    }

    private void DataAdd() {
        mRespDataBeen = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mRespDataBeen.add(mDatas.getRespData().get(i));
        }
        GridViewPagerAdapter gridViewAdapter = new GridViewPagerAdapter(context,mRespDataBeen);
        gridView.setAdapter(gridViewAdapter);
    }
}
