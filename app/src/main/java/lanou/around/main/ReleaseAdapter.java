package lanou.around.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseRcvAdapter;

/**
 * Created by dllo on 16/11/4.
 */

public class ReleaseAdapter extends BaseRcvAdapter<ReleaseAdapter.ViewHolder, String> {


    public ReleaseAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.rekease_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position == 0) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.addimg);
            holder.imgItem.setImageBitmap(bmp);
        }else {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.addnull);
            holder.imgItem.setImageBitmap(bmp);
        }

        holder.imgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClick.onItemClick(holder, position);
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgItem;

        public ViewHolder(View itemView) {
            super(itemView);

            imgItem = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }


}


