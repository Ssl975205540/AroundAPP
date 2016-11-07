package lanou.around.classification.classifyview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;
import lanou.around.bean.ClassifyViewBean;

/**
 * Created by dllo on 16/10/25.
 */
public class GridViewPagerAdapter extends BaseAdapter<ClassifyViewBean.RespDataBean> {

    public GridViewPagerAdapter(Context context, List<ClassifyViewBean.RespDataBean> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_left_gridview;
    }

    @Override
    public void onInitView(View view, int position) {
        ImageView imageView = (ImageView) view.findViewById(R.id.image_left);
        Picasso.with(mContext).load(list.get(position).getCateUrl()).into(imageView);
        TextView textView = (TextView) view.findViewById(R.id.text_left);
        textView.setText(list.get(position).getCateName());
    }
}
