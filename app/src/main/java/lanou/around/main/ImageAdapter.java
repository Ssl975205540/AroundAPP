package lanou.around.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import lanou.around.R;
import lanou.around.base.CanRVAdapter;
import lanou.around.tools.db.CanHolderHelper;


class ImageAdapter extends CanRVAdapter<String> {






    private onItem onItem;

    @Override
    public void setList(List<String> datas) {
        super.setList(datas);

    }

    public void setOnItem(ImageAdapter.onItem onItem) {
        this.onItem = onItem;
    }

    public ImageAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.rekease_item);
    }

    @Override
    protected void setView(final CanHolderHelper helper, final int position, String bean) {

        if(bean.equals("")){


                Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.addnull);

                helper.getImageView(R.id.img_item).setImageBitmap(bmp);

        }


        if(bean.length()>2){
            helper.getImageView(R.id.img_item).setImageBitmap(CanHolderHelper.convertToBitmap(mList.get(position), 200, 200));

        }

        if(bean.equals(".")){

                Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.mj);

                helper.getImageView(R.id.img_item).setImageBitmap(bmp);

        }

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                onItem.setOnItemListenner(position);

            }
        });


    }

    @Override
    protected void setItemListener(CanHolderHelper viewHelper, int position) {

    }



    interface onItem{
        void setOnItemListenner(int position);
    }

}