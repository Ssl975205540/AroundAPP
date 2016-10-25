package lanou.around.home;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseRcvAdapter;
import lanou.around.bean.VideoBean;

/**
 * Created by dllo on 16/10/22.
 */

public class HomeAdapter extends BaseRcvAdapter<HomeViewHolder,VideoBean> {

    public HomeAdapter(Context context, List<VideoBean> data) {
        super(context, data);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HomeViewHolder homeViewHolder =  new HomeViewHolder(inflater.inflate(R.layout.home_adapter_item,parent,false));

        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        holder.img.setBackgroundResource(data.get(position).getAnInt());

    }
}
