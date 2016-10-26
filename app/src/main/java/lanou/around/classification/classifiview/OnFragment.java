package lanou.around.classification.classifiview;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.tools.recycle.http.URLValues;

/**
 * Created by dllo on 16/10/22.
 */
public class OnFragment extends BaseFragment implements InterView<ClassifyViewBean> {

    private Context context;
    private GridView gridView;


    @Override
    protected int setContentView() {
        return R.layout.one;
    }

    @Override
    protected void initViews() {
        gridView = findView(R.id.gview_classify);
    }
    @Override
    protected void initListeners() {
        ClassifyViewPresenter presenter = new ClassifyViewPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_CHILD_CATES_LOGIC);
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
    public void onResponse(ClassifyViewBean classifyViewBean) {

        GridViewLeftAdapter gridViewAdapter = new GridViewLeftAdapter(context);
        gridViewAdapter.setRespDataBeen(classifyViewBean);
        gridView.setAdapter(gridViewAdapter);

    }


    @Override
    public void onError() {

    }

    public class gridViewListener implements AdapterView.OnItemClickListener {

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
