package lanou.around.home.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseRcvAdapter;
import lanou.around.bean.RecommendBean;

/**
 * Created by dllo on 16/10/28.
 */
public class RecommendAdapter extends BaseRcvAdapter<RecommendAdapter.RecommendViewHolder,RecommendBean>{





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



    }



    public class RecommendViewHolder extends RecyclerView.ViewHolder {

        public RecommendViewHolder(View itemView) {
            super(itemView);


        }
    }
}
