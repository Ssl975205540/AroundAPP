package lanou.around.classification.checkall;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;
import lanou.around.bean.ClassifyViewBean;


/**
 * Created by dllo on 16/10/31.
 */
public class CheckAllAdapter extends BaseAdapter<ClassifyViewBean.RespDataBean> {

    public CheckAllAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_check_all;
    }

    @Override
    public void onInitView(View view, int position) {
        ImageView imageView = get(view, R.id.iv_button);
        Picasso.with(mContext).load(list.get(position).getCateLogo()).into(imageView);
        TextView name = get(view, R.id.tv_name);
        name.setText(list.get(position).getCateName());
        TextView body = get(view, R.id.tv_body);
        body.setText(list.get(position).getCateDesc());

    }
}
