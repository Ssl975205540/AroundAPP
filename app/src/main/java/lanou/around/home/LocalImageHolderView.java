package lanou.around.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import lanou.around.R;

/**
 * Created by dllo on 16/10/29.
 */

public class LocalImageHolderView implements Holder<String> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {

        Glide.with(context).load(data).diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.home01_bg_card).placeholder(R.mipmap.x3)
                .into(imageView);
    }
}
