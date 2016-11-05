package lanou.around.classification.classify;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;
import lanou.around.bean.ClassifyBean;

/**
 * Created by dllo on 16/10/26.
 */
public class ClassifyFurnitureAdapter extends BaseAdapter<ClassifyBean.RespDataBean.SubCateArrBean> {

    public ClassifyFurnitureAdapter(Context context, List<ClassifyBean.RespDataBean.SubCateArrBean> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_computer;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView textView = get(view, R.id.tv_computer_title);
        textView.setText(list.get(position).getSubCateName());

    }
}