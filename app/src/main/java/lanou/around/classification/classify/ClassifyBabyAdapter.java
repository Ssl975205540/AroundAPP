package lanou.around.classification.classify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.around.R;
import lanou.around.bean.ClassifyBean;

import static lanou.around.app.AroundAPP.context;

/**
 * Created by dllo on 16/10/26.
 */
public class ClassifyBabyAdapter extends BaseAdapter {
    private Context mContext;
    private ClassifyBean mClassifyBean;

    public void setClassifyBean(ClassifyBean classifyBean) {
        mClassifyBean = classifyBean;
        notifyDataSetChanged();
    }

    public ClassifyBabyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mClassifyBean.getRespData().get(3).getSubCateArr().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_baby_grid, parent, false);
        ImageView photo = (ImageView) view.findViewById(R.id.iv_baby_photo);
        TextView title = (TextView) view.findViewById(R.id.tv_baby_title);
        TextView body = (TextView) view.findViewById(R.id.tv_baby_body);
        Picasso.with(context).load(mClassifyBean.getRespData().get(3)
                .getSubCateArr().get(position).getSubCateLogo()).into(photo);
        title.setText(mClassifyBean.getRespData().get(3)
                .getSubCateArr().get(position).getSubCateName());
        body.setText(mClassifyBean.getRespData().get(3)
                .getSubCateArr().get(position).getSubCateDescribe());

        return view;
    }

}
