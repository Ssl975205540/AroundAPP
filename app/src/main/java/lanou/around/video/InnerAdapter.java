package lanou.around.video;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tedcoder.wkvideoplayer.view.SuperVideoPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lanou.around.R;
import lanou.around.bean.VideoBean;

/**
 * Created by dllo on 16/10/24.
 */

public class InnerAdapter extends BaseAdapter{
    List<VideoFragment.Talent> objs;
    Context content;

    private ddd ddd;

    public void setDdd(InnerAdapter.ddd ddd) {
        this.ddd = ddd;
    }

    public void setArrayList(ArrayList<VideoBean> arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<VideoBean> arrayList;

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public void setCardHight(int cardHight) {
        this.cardHight = cardHight;
    }

    int cardWidth;
    int cardHight;




    public InnerAdapter(Context context) {
        this.content = context;
        objs = new ArrayList<>();

    }

    public void addAll(Collection<VideoFragment.Talent> collection) {
        if (isEmpty()) {
            objs.addAll(collection);
            notifyDataSetChanged();
        } else {
            objs.addAll(collection);
        }
    }

    public void clear() {
        objs.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return objs.isEmpty();
    }

    public void remove(int index) {
        if (index > -1 && index < objs.size()) {
            objs.remove(index);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        Log.d("InnerAdapter", "objs.size():" + objs.size());
        return objs.size();



    }

    @Override
    public VideoFragment.Talent getItem(int position) {
        if(objs==null ||objs.size()==0) return null;
        return objs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        VideoFragment.Talent talent = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(content).inflate(R.layout.card_new_item, parent, false);
            holder  = new ViewHolder();
            convertView.setTag(holder);
            convertView.getLayoutParams().width = cardWidth;

            holder.titleView = (ImageView) convertView.findViewById(R.id.iv_title);

            holder.nameView = (TextView) convertView.findViewById(R.id.tv_title);

            holder.superVideoPlayer = (SuperVideoPlayer)convertView.findViewById(R.id.video_player_item_1);
            holder.imageView = (ImageView)convertView.findViewById(R.id.play_btn);

            holder.cityView = (TextView) convertView.findViewById(R.id.tv_brief);
            holder.eduView = (TextView) convertView.findViewById(R.id.video_title);
            holder.workView = (TextView) convertView.findViewById(R.id.video_tag);




        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.titleView.setImageResource(R.mipmap.ic_launcher);

        holder.nameView.setText(String.format("%s", talent.nickname));



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageView.setVisibility(View.GONE);
//                holder.portraitView.setVisibility(View.GONE);
//                holder.superVideoPlayer.setVisibility(View.VISIBLE);

                ddd.dad(holder.superVideoPlayer,"http://bobolive.nosdn.127.net/aac_bobo_1471873939069_14732651.mp4");

            }
        });



        final CharSequence no = "暂无";

        holder.cityView.setHint(no);
        holder.cityView.setText(talent.cityName);


        holder.eduView.setHint(no);
        holder.eduView.setText(talent.educationName);


        holder.workView.setHint(no);
        holder.workView.setText(talent.workYearName);



        return convertView;
    }




    private static class ViewHolder {
        ImageView portraitView , titleView;
        TextView nameView;
        TextView cityView;
        TextView eduView;
        TextView workView;

        SuperVideoPlayer superVideoPlayer;

        ImageView imageView;


    }



    interface ddd{
        void dad(SuperVideoPlayer superVideoPlayer, String url);

    }
}
