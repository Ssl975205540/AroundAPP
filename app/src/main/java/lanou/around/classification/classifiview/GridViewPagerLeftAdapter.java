package lanou.around.classification.classifiview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.around.R;
import lanou.around.bean.ClassifyViewBean;

/**
 * Created by dllo on 16/10/25.
 */
public class GridViewPagerLeftAdapter extends BaseAdapter {

    private View mView;
    private Context mContext;
    private ImageView mImageView;
    private TextView mTextView;
    private List<ClassifyViewBean.RespDataBean> mRespDataBeen;


    public void setRespDataBeen(List<ClassifyViewBean.RespDataBean> respDataBeen) {
        mRespDataBeen = respDataBeen;
        notifyDataSetChanged();
    }



    public GridViewPagerLeftAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mRespDataBeen == null ? 0 : mRespDataBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mRespDataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            mView = LayoutInflater.from(mContext).inflate(R.layout.item_left_gridview, parent, false);
            mImageView = (ImageView) mView.findViewById(R.id.image_left);
            mTextView = (TextView) mView.findViewById(R.id.text_left);
            Picasso.with(mContext).load(mRespDataBeen.get(position).getCateUrl()).into(mImageView);
            mTextView.setText(mRespDataBeen.get(position).getCateName());
        }
        return mView;
    }

}
