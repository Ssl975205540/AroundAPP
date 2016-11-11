package lanou.around.readphoto;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.around.R;
import lanou.around.base.CanRVAdapter;
import lanou.around.bean.PictureBean;
import lanou.around.tools.db.CanHolderHelper;

/**
 * Created by dllo on 16/11/5.
 */

public class PhotoAdapter extends CanRVAdapter<PictureBean> {


    public PhotoAdapter(RecyclerView mRecyclerView, int itemLayoutId) {
        super(mRecyclerView, itemLayoutId);
    }

    public void setInterPhoto(onInterPhoto interPhoto) {
        this.interPhoto = interPhoto;
    }

    private onInterPhoto interPhoto;
    private List<String> list;



    public void setCheckPhoto(List<String> list) {
        this.list = list;
    }

    @Override
    protected void setView(CanHolderHelper viewHelper, final int position, PictureBean model) {

      RelativeLayout rela = viewHelper.getView(R.id.rela);
        if(position == 0){
            rela.setVisibility(View.VISIBLE);
        }else {
            rela.setVisibility(View.GONE);

        }
        Log.d("PhotoAdapter", model.path);

        CheckBox checkBox = viewHelper.getView(R.id.checkbox);
        final ImageView imageView = viewHelper.getImageView(R.id.img);
//        Glide.with(mContext).load(mList.get(position).path).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                imageView.setImageBitmap(resource);
//            }
//        });
        Glide.with(mContext).load(mList.get(position).path).asBitmap().into(imageView);

        checkBox.setChecked(mList.get(position).check);

    }

    @Override
    protected void setItemListener(final CanHolderHelper viewHelper, final int position) {
        final CheckBox checkBox = viewHelper.getView(R.id.checkbox);
        RelativeLayout rela = viewHelper.getView(R.id.rela);
        rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interPhoto.intentCarture();

            }
        });



        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (list.size() == 12) {
                    checkBox.setChecked(false);
                    Toast.makeText(mContext, "最多只能加12张图片", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkBox.isChecked()) {
                        interPhoto.addphoto(mList.get(position).path,list.size());
                        list.add(mList.get(position).path);
                        mList.get(position).check = true;

                    } else {
                        list.remove(mList.get(position).path);
                        interPhoto.removephoto(mList.get(position).path,list.size());
                        mList.get(position).check = false;

                    }
                }


            }
        });

        final ImageView imageView = viewHelper.getImageView(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interPhoto.onImgListener(position);
            }
        });

    }


    public List<String> getCheckPhoto() {

        return list;
    }

    public void removeCheck(int position, String str) {

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).path.equals(str)) {
                mList.get(i).check = false;
            }

        }
        list.remove(position);
        notifyDataSetChanged();

    }


    interface onInterPhoto {

        void removephoto(String string,int age);

        void addphoto(String string,int age);

        void intentCarture();

        void onImgListener(int position);

    }


}
