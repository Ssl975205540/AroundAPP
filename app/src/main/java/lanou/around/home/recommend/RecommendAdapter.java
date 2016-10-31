package lanou.around.home.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseRcvAdapter;
import lanou.around.bean.RecommendBean;
import lanou.around.widget.CircleTransform;

/**
 * Created by dllo on 16/10/28.
 */
public class RecommendAdapter extends BaseRcvAdapter<RecommendAdapter.RecommendViewHolder,RecommendBean.RespDataBean>{



    public RecommendAdapter(Context context, List data) {
        super(context, data);
    }




    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_adapter_item,parent,false);
        RecommendViewHolder recommendViewHolder = new RecommendViewHolder(view);
        return recommendViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {


        Picasso.with(context).load(data.get(position).getSellerPhoto()).transform(new CircleTransform()).into(holder.imgSellerPhoto);
        holder.tvFriendTime.setText(data.get(position).getFriendTime());
        holder.tv_sellerNickname.setText(data.get(position).getSellerNickname());

        holder.infoPrice.setText(data.get(position).getInfoPrice());
        holder.infoOriginalPrice.setText(data.get(position).getInfoOriginalPrice());


    }



    public class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgSellerPhoto;
        private final TextView tvFriendTime;
        private final HorizontalScrollView scrollview_item;
        private final TextView infoCityName;
        private final TextView area;
        private final TextView Leaving;
        private final TextView number;
        private final TextView tv_sellerNickname;
        private final TextView infoPrice;
        private final TextView infoOriginalPrice;

        public RecommendViewHolder(View itemView) {
            super(itemView);

            imgSellerPhoto = (ImageView) itemView.findViewById(R.id.img_sellerPhoto);
            tvFriendTime = (TextView) itemView.findViewById(R.id.tv_friendTime);
            scrollview_item = (HorizontalScrollView) itemView.findViewById(R.id.scrollview_item);
            tv_sellerNickname = (TextView)itemView.findViewById(R.id.tv_sellerNickname);
            infoCityName = (TextView) itemView.findViewById(R.id.infoCityName);
            area = (TextView) itemView.findViewById(R.id.area);
            Leaving = (TextView) itemView.findViewById(R.id.Leaving);
            number = (TextView) itemView.findViewById(R.id.number);
            infoPrice = (TextView) itemView.findViewById(R.id.infoPrice);
            infoOriginalPrice = (TextView) itemView.findViewById(R.id.infoOriginalPrice);


        }
    }
}
