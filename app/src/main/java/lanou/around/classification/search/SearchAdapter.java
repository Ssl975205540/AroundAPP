package lanou.around.classification.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseRcvAdapter;
import lanou.around.bean.Bean;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/11/2.
 */

public class SearchAdapter extends BaseRcvAdapter<SearchAdapter.MyViewHolder, Bean.RespDataBean> {

    public SearchAdapter(Context context, List<Bean.RespDataBean> data) {
        super(context, data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(context).load(URLValues.PIN_RECOMMEND + data.get(position).getInfoImage()).into(holder.image);
        holder.mNews.setText(data.get(position).getTitle());
        holder.mCityName.setText(data.get(position).getCityName());
        holder.mBusinessName.setText(data.get(position).getBusinessName());
        holder.mPrice.setText(String.valueOf(data.get(position).getPrice()));
        holder.mFriendTime.setText(data.get(position).getFriendTime());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView mNews;
        private final TextView mCityName;
        private final TextView mBusinessName;
        private final TextView mPrice;
        private final TextView mFriendTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_phone);
            mNews = (TextView) itemView.findViewById(R.id.tv_phone_news);
            mCityName = (TextView) itemView.findViewById(R.id.tv_phone_cityName);
            mBusinessName = (TextView) itemView.findViewById(R.id.tv_phone_businessName);
            mPrice = (TextView) itemView.findViewById(R.id.tv_phone_price);
            mFriendTime = (TextView) itemView.findViewById(R.id.tv_phone_friendTime);
        }
    }
}
