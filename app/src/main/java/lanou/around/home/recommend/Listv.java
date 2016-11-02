package lanou.around.home.recommend;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Created by dllo on 16/11/2.
 */

public class Listv extends BaseAdapter {


    private  Context context;
    private  List<RecommendBean.RespDataBean> list;

    public Listv(Context context, List<RecommendBean.RespDataBean> list) {

        this.context = context;
        this.list = list;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        switch (getItemViewType(position)) {

            case 0:

                RecommendViewHolder recommendViewHolder=null;
                if(convertView == null){

                    convertView = LayoutInflater.from(context).inflate(R.layout.recommend_adapter_item,null);
                           recommendViewHolder = new RecommendViewHolder(convertView);
                    convertView.setTag(recommendViewHolder);
                }else {
                   recommendViewHolder = (RecommendViewHolder)convertView.getTag();

                }

                Picasso.with(context).load(list.get(position).getSellerPhoto()).transform(new CircleTransform()).into(recommendViewHolder.imgSellerPhoto);
                recommendViewHolder.tvFriendTime.setText(list.get(position).getFriendTime());
                recommendViewHolder.tv_sellerNickname.setText(list.get(position).getSellerNickname());
                recommendViewHolder.infoPrice.setText("¥" + list.get(position).getInfoPrice());
                if(!list.get(position).getInfoOriginalPrice().equals("0")){
                    recommendViewHolder.infoOriginalPrice.setText("原价"+list.get(position).getInfoOriginalPrice());
                }

                recommendViewHolder.area.setText(list.get(position).getArea());
                recommendViewHolder.infoCityName.setText(list.get(position).getInfoCityName());
                recommendViewHolder.infoDesc.setText(list.get(position).getInfoDesc());
                String[] s = list.get(position).getInfoImageList().split("\\|");

                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0; i < s.length; i++) {
                    arrayList.add(URLValues.PIN_RECOMMEND + s[i]);
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recommendViewHolder.scrollview_item.setLayoutManager(linearLayoutManager);
                GalleryAdapter adapter = new GalleryAdapter(context, arrayList);
                recommendViewHolder.scrollview_item.setAdapter(adapter);
                recommendViewHolder.scrollview_item.setNestedScrollingEnabled(false);

                recommendViewHolder.number.setText(list.get(position).getMessageNum());
                recommendViewHolder.Leaving.setText(list.get(position).getScanNum());



                return convertView;



            case 5:



                return convertView;


        }
        return convertView;

    }

    @Override
    public int getItemViewType(int position) {

        return Integer.parseInt(list.get(position).getType());


    }
    public class RecommendViewHolder {

        private final ImageView imgSellerPhoto,uu;
        private final TextView tvFriendTime;
        private final RecyclerView scrollview_item;
        private final TextView infoCityName;
        private final TextView area;
        private final TextView Leaving;
        private final TextView number;
        private final TextView tv_sellerNickname;
        private final TextView infoPrice;
        private final TextView infoOriginalPrice;
        private final TextView infoDesc;

        public RecommendViewHolder(View itemView) {

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
            infoDesc = (TextView) itemView.findViewById(R.id.infoDesc);
            uu = (ImageView) itemView.findViewById(R.id.uu);

        }
    }

}
