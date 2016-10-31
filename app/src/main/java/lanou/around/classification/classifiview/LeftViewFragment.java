package lanou.around.classification.classifiview;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.classification.WebActivity;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/10/22.
 */
public class LeftViewFragment extends BaseFragment implements InterView {


    private GridView gridView;
    private List<ClassifyViewBean.RespDataBean> mRespDataBeen;


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
        ClassifyViewPresenter presenter = new ClassifyViewPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_CHILD_CATES_LOGIC, ClassifyViewBean.class);
    }

    @Override
    protected void initData() {

        // 为GridView设定监听器
        gridView.setOnItemClickListener(new gridViewListener());
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public <T> void onResponse(T t) {
        ClassifyViewBean classifyViewBean = (ClassifyViewBean) t;
        mRespDataBeen = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mRespDataBeen.add(classifyViewBean.getRespData().get(i));
        }
        GridViewPagerLeftAdapter gridViewAdapter = new GridViewPagerLeftAdapter(context);
        gridViewAdapter.setRespDataBeen(mRespDataBeen);
        gridView.setAdapter(gridViewAdapter);
    }


    @Override
    public void onError() {

    }

    public class gridViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            System.out.println("arg2 = " + arg2); // 打印出点击的位置
            if (1 == arg2) {
                if (mRespDataBeen.get(1).getCateUrl() != null){
                    Intent intent = new Intent(AroundAPP.getContext(), WebActivity.class);
                    intent.putExtra("url", mRespDataBeen.get(1).getMurl());
                    getActivity().startActivity(intent);
                }

            } else {
                Intent intent = new Intent(getContext(), WebActivity.class);
                getActivity().startActivity(intent);
            }


        }
    }


}
