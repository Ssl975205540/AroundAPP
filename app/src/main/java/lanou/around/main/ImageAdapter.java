package lanou.around.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import lanou.around.R;
import lanou.around.base.CanRVAdapter;
import lanou.around.tools.db.CanHolderHelper;

class ImageAdapter extends CanRVAdapter<String> {




    private onItem onItem;

    public void setOnItem(ImageAdapter.onItem onItem) {
        this.onItem = onItem;
    }

    public ImageAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.rekease_item);
    }

    @Override
    protected void setView(final CanHolderHelper helper, final int position, String bean) {
        helper.getImageView(R.id.img_item).setImageBitmap(CanHolderHelper.convertToBitmap(mList.get(position), 200, 200));

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