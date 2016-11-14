package lanou.around.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tedcoder.wkvideoplayer.view.SuperVideoPlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lanou.around.R;
import lanou.around.bean.VideoBean;
import lanou.around.bean.VideoDetailsBean;
import lanou.around.widget.CircleTransform;

/**
 * Created by dllo on 16/10/24.
 */

public class InnerAdapter extends lanou.around.base.BaseAdapter{

    private Context context;
    private videoSuper videoSuper;
    int cardWidth;
    int cardHight;
    private ArrayList<VideoBean> arrayList;
    private List<VideoDetailsBean> videoDetailsBeans;

    public InnerAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public void setVideoSuper(videoSuper videoSuper) {
        this.videoSuper = videoSuper;
    }

    public void setArrayList(ArrayList<VideoBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public void setCardHight(int cardHight) {
        this.cardHight = cardHight;
    }

    public void setVideoDetailsBeans(List<VideoDetailsBean> videoDetailsBeans) {
        this.videoDetailsBeans = videoDetailsBeans;
        notifyDataSetChanged();
    }




    public void addAll(Collection<VideoDetailsBean> collection) {
        if (isEmpty()) {
            videoDetailsBeans.addAll(collection);
            notifyDataSetChanged();
        } else {
            videoDetailsBeans.addAll(collection);
        }
    }

    @Override
    public void clear() {
        videoDetailsBeans.clear();
        notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return videoDetailsBeans.isEmpty();
    }



    @Override
    public void remove(int position) {
        if (position > -1 && position < Integer.MAX_VALUE) {
            videoDetailsBeans.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return videoDetailsBeans.size();
    }


    @Override
    public VideoDetailsBean getItem(int position) {
        if (videoDetailsBeans == null || videoDetailsBeans.size() == 0) return null;
        return videoDetailsBeans.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final VideoDetailsBean videoDetailsBean = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_new_item, parent, false);
            holder  = new ViewHolder(convertView);
            convertView.setTag(holder);
            convertView.getLayoutParams().width = cardWidth;
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(videoDetailsBean.getAvatar()).transform(new CircleTransform()).into(holder.avatar);
        holder.channelName.setText(videoDetailsBean.getChannelName());
        holder.channelIntro.setText(videoDetailsBean.getChannelIntro());
        holder.tag.setText(videoDetailsBean.getTag());

        holder.intro.setText(videoDetailsBean.getTitle());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageView.setVisibility(View.GONE);
                videoSuper.superVideo(holder.superVideoPlayer,videoDetailsBean.getLinkMp4());
            }
        });
        return convertView;
    }



    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void onInitView(View view, int position) {

    }


    class ViewHolder {
        SuperVideoPlayer superVideoPlayer;
         ImageView imageView , avatar;
         TextView channelName , channelIntro , intro , tag;

         public ViewHolder(View view) {
             superVideoPlayer = (SuperVideoPlayer) view.findViewById(R.id.video_player_item_1);
             imageView = (ImageView) view.findViewById(R.id.play_btn);
             avatar =(ImageView) view.findViewById(R.id.iv_title);
             channelName = (TextView) view.findViewById(R.id.tv_title);
             channelIntro = (TextView) view.findViewById(R.id.tv_brief);
             intro = (TextView) view.findViewById(R.id.video_title);
             tag = (TextView) view.findViewById(R.id.video_tag);


         }
     }



    interface videoSuper {
        void superVideo(SuperVideoPlayer superVideoPlayer, String url);

    }


}
