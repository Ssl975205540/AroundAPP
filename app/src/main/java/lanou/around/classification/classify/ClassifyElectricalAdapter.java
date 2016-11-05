package lanou.around.classification.classify;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;
import lanou.around.bean.ClassifyBean;

import static lanou.around.app.AroundAPP.context;

/**
 * Created by dllo on 16/10/26.
 */
public class ClassifyElectricalAdapter extends BaseAdapter<ClassifyBean.RespDataBean.SubCateArrBean> {
    public ClassifyElectricalAdapter(Context context, List<ClassifyBean.RespDataBean.SubCateArrBean> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_electrical_grid;
    }

    @Override
    public void onInitView(View view, int position) {
        ImageView photo = get(view, R.id.iv_electrical_photo);
        Picasso.with(context).load(list.get(position).getSubCateLogo()).into(photo);
        TextView title = get(view, R.id.tv_electrical_title);
        title.setText(list.get(position).getSubCateName());
    }

}
