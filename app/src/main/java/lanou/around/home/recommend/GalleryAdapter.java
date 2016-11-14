package lanou.around.home.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import lanou.around.R;

import static org.cybergarage.http.HTTP.HEAD;

/**
 * Created by dllo on 16/11/1.
 */

public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context mContext;

    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater mInflater;
    private List<String> mDatas;

    public GalleryAdapter(Context context, List<String> datats) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recommend_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.img_item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {


        Glide.with(mContext).load(mDatas.get(i)).diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.home01_bg_card).placeholder(R.mipmap.rx)
                .into(viewHolder.mImg);

//        //如果设置了回调，则设置点击事件
//        if (mOnItemClickLitener != null)
//        {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
//                }
//            });

//        }

    }

}

