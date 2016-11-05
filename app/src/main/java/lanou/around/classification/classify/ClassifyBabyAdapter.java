package lanou.around.classification.classify;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;
import lanou.around.bean.ClassifyBean;

/**
 * Created by dllo on 16/10/26.
 */
public class ClassifyBabyAdapter extends BaseAdapter<ClassifyBean.RespDataBean.SubCateArrBean> {

    public ClassifyBabyAdapter(Context context, List<ClassifyBean.RespDataBean.SubCateArrBean> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_baby_grid;
    }

    @Override
    public void onInitView(View view, int position) {
        ImageView photo = get(view, R.id.iv_baby_photo);
        Picasso.with(mContext).load(list.get(position).getSubCateLogo()).into(photo);
        TextView title = get(view, R.id.tv_baby_title);
        title.setText(list.get(position).getSubCateName());
        TextView body = get(view, R.id.tv_baby_body);
        body.setText(list.get(position).getSubCateDescribe());

    }

}
