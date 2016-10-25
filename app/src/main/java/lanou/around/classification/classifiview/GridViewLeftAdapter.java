package lanou.around.classification.classifiview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.around.R;
import lanou.around.bean.ClassifyViewBean;

/**
 * Created by dllo on 16/10/25.
 */
public class GridViewLeftAdapter extends BaseAdapter {


    private ClassifyViewBean mRespDataBeen;

    public void setRespDataBeen(ClassifyViewBean respDataBeen) {
        mRespDataBeen = respDataBeen;
    }

    private View mView;


    private Context mContext;
    private ImageView mImageView;
    private TextView mTextView;

    public GridViewLeftAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 8;
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

        if (convertView == null) {
            mView = LayoutInflater.from(mContext).inflate(R.layout.item_left_gridview, parent, false);
            mImageView = (ImageView) mView.findViewById(R.id.image_left);
            mTextView = (TextView) mView.findViewById(R.id.text_left);


            Picasso.with(mContext).load(mRespDataBeen.getRespData().get(position).getCateUrl()).into(mImageView);
            mTextView.setText(mRespDataBeen.getRespData().get(position).getCateName());


        }
        return mView;
    }

}
