package lanou.around.home;

import android.view.View;
import android.widget.ImageView;

import lanou.around.R;
import lanou.around.base.RecyclerHolder;

/**
 * Created by dllo on 16/10/22.
 */
public class HomeViewHolder extends RecyclerHolder {

    public final ImageView img;

    public HomeViewHolder(View itemView) {
        super(itemView);


        img = (ImageView) itemView.findViewById(R.id.iv_home_dapter_item);
    }
}
