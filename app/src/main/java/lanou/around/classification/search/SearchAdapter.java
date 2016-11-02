package lanou.around.classification.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseRcvAdapter;
import lanou.around.bean.Bean;

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
        Glide.with(context).load("http://pic2.58cdn.com.cn/zhuanzh/" + data.get(position).getInfoImage()).into(holder.img);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_phone);
        }
    }
}
