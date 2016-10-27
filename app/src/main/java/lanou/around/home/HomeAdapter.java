package lanou.around.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import lanou.around.R;
import lanou.around.base.BaseRcvAdapter;
import lanou.around.bean.HomeBeanHot;

/**
 * Created by dllo on 16/10/22.
 */

public class HomeAdapter extends BaseRcvAdapter<HomeViewHolder, HomeBeanHot> {

    public HomeAdapter(Context context, ArrayList<HomeBeanHot> data) {
        super(context, data);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HomeViewHolder homeViewHolder = new HomeViewHolder(inflater.inflate(R.layout.home_adapter_item, parent, false));

        return homeViewHolder;


    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {

        Glide.with(getContext()).load(data.get(position).getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.home01_bg_card).placeholder(R.mipmap.rx)
                .into(holder.img);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder, position);
            }
        });


    }
}
