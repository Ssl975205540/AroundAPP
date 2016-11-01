package lanou.around.home.recommend;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.bean.RecommendBean;
import lanou.around.tools.http.URLValues;
import lanou.around.widget.CircleTransform;

/**
 * Created by dllo on 16/10/28.
 */
public class RecommendAdapter extends RecyclerView.Adapter {


    private Context context;

    private List<RecommendBean.RespDataBean> data;

    public void setData(List<RecommendBean.RespDataBean> data) {
        this.data = data;
    }

    public RecommendAdapter(Context context) {

        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {

        if (position < 0 || data.size() == position  ) {
            return super.getItemViewType(position);
        }

        return Integer.parseInt(data.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case 0:

                View view = LayoutInflater.from(context).inflate(R.layout.recommend_adapter_item, parent, false);
                RecommendViewHolder recommendViewHolder = new RecommendViewHolder(view);
                return recommendViewHolder;

            case 5:


                break;


        }

        return null;


    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        switch (getItemViewType(position)) {

            case 0:

                RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;

                Picasso.with(context).load(data.get(position).getSellerPhoto()).transform(new CircleTransform()).into(recommendViewHolder.imgSellerPhoto);
                recommendViewHolder.tvFriendTime.setText(data.get(position).getFriendTime());
                recommendViewHolder.tv_sellerNickname.setText(data.get(position).getSellerNickname());
                recommendViewHolder.infoPrice.setText(data.get(position).getInfoPrice());
                recommendViewHolder.infoOriginalPrice.setText(data.get(position).getInfoOriginalPrice());

                String[] s = data.get(position).getInfoImageList().split("\\|");

                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0; i <s.length; i++) {
                    arrayList.add(URLValues.PIN_RECOMMEND + s[i]);
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recommendViewHolder.scrollview_item.setLayoutManager(linearLayoutManager);
                GalleryAdapter adapter = new GalleryAdapter(context,arrayList);
                recommendViewHolder.scrollview_item.setAdapter(adapter);

                break;


            case 5:


                break;

        }


    }


    public class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgSellerPhoto;
        private final TextView tvFriendTime;
        private final RecyclerView scrollview_item;
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
            scrollview_item = (RecyclerView) itemView.findViewById(R.id.scrollview_item);
            tv_sellerNickname = (TextView) itemView.findViewById(R.id.tv_sellerNickname);
            infoCityName = (TextView) itemView.findViewById(R.id.infoCityName);
            area = (TextView) itemView.findViewById(R.id.area);
            Leaving = (TextView) itemView.findViewById(R.id.Leaving);
            number = (TextView) itemView.findViewById(R.id.number);
            infoPrice = (TextView) itemView.findViewById(R.id.infoPrice);
            infoOriginalPrice = (TextView) itemView.findViewById(R.id.infoOriginalPrice);


        }
    }
}
