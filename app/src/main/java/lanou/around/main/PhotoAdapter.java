package lanou.around.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/5.
 */

public class PhotoAdapter extends CanRVAdapter<PictureBean> {


    public PhotoAdapter(RecyclerView mRecyclerView, int itemLayoutId, List<PictureBean> mList) {
        super(mRecyclerView, itemLayoutId, mList);
    }

    @Override
    protected void setView(CanHolderHelper viewHelper, int position, PictureBean model) {


        viewHelper.getImageView(R.id.img).setImageBitmap(convertToBitmap(mList.get(position).path, 300, 300));

    }

    @Override
    protected void setItemListener(final CanHolderHelper viewHelper, final int position) {
        final CheckBox checkBox = viewHelper.getView(R.id.checkbox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBox.isChecked()){

                    mList.get(position).check = true;
                }else {

                    mList.get(position).check = false;

                }

            }
        });
    }

    public Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }


    public List<PictureBean> getCheckPhoto() {
        List<PictureBean> list = new ArrayList<>();

        for (int i = 0; i < mList.size(); i++) {

            if (mList.get(i).check == true) {
                list.add(mList.get(i));
            }

        }


        return list;
    }
}
