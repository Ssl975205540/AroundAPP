package lanou.around.classification.classifiview;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/10/25.
 */
public class RightViewFragment extends BaseFragment implements InterView {
    private Context context;
    private GridView gridView;


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
        presenter.startRequest(URLValues.CLASSIFY_CHILD_CATES_LOGIC,ClassifyViewBean.class);
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
        List<ClassifyViewBean.RespDataBean> dataBeanList = new ArrayList<>();
        for (int i = 16; i < 19 ; i++) {
            dataBeanList.add(classifyViewBean.getRespData().get(i));
        }
        GridViewPagerLeftAdapter gridViewAdapter = new GridViewPagerLeftAdapter(context);
        gridViewAdapter.setRespDataBeen(dataBeanList);
        gridView.setAdapter(gridViewAdapter);
    }



    @Override
    public void onError() {

    }

    class gridViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            // TODO Auto-generated method stub
            System.out.println("arg2 = " + arg2); // 打印出点击的位置
        }
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
