package lanou.around.home.nearby;

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
import lanou.around.bean.HomeTabItemBean;
import lanou.around.tools.http.URLValues;


/**
 * Created by dllo on 16/11/3.
 */

public class HomeTabItemAdapter extends BaseRcvAdapter<HomeTabItemAdapter.MyViewHolder , HomeTabItemBean.RespDataBean>{


    private OnRcvItemClickListener mClickListener;

    public void setClickListener(OnRcvItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public HomeTabItemAdapter(Context context, List<HomeTabItemBean.RespDataBean> data) {
        super(context, data);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_item , parent , false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        HomeTabItemBean.RespDataBean respDataBean = data.get(position);
        Glide.with(context).load(URLValues.PIN_RECOMMEND + respDataBean.getInfoImage()).into(holder.image);
        holder.title.setText(respDataBean.getTitle() + "" + respDataBean.getDesc());
        holder.price.setText("¥" + respDataBean.getPrice());
        holder.minlin.setText(respDataBean.getDistance());
        holder.time.setText(respDataBean.getFriendTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mClickListener.onRcvClickListener(holder.itemView, position);
                        }
                    });
                }
            }
        });



    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private  ImageView image;
        private TextView title, price , minlin , time;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.tab_item_image);
            title = (TextView) itemView.findViewById(R.id.tab_item_title);
            price = (TextView) itemView.findViewById(R.id.tab_item_price);
            minlin = (TextView) itemView.findViewById(R.id.tab_item_minlin);
            time = (TextView) itemView.findViewById(R.id.tab_item_time);
        }
    }
    public interface OnRcvItemClickListener{
        void onRcvClickListener(View view, int position);
    }
}
