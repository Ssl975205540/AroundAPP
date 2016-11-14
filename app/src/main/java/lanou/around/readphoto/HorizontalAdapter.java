package lanou.around.readphoto;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import lanou.around.R;
import lanou.around.base.CanRVAdapter;
import lanou.around.tools.db.CanHolderHelper;

/**
 * Created by dllo on 16/11/7.
 */
public class HorizontalAdapter extends CanRVAdapter<String> {

    private onItem onItem;

    @Override
    public void setList(List<String> datas) {
        super.setList(datas);

    }

    public void setOnItem(HorizontalAdapter.onItem onItem) {
        this.onItem = onItem;
    }

    public HorizontalAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.horizontal_item);
    }

    @Override
    protected void setView(final CanHolderHelper helper, final int position, final String bean) {


        if (mList.size() == 0) {

            return;
        } else {
            helper.getImageView(R.id.hori_item).setImageBitmap(CanHolderHelper.convertToBitmap(bean, 300, 300));

        }

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                onItem.setOnItemListenner(position,bean);

            }
        });


    }

    @Override
    protected void setItemListener(CanHolderHelper viewHelper, final int position) {


    }


    interface onItem {
        void setOnItemListenner(int position,String str);
    }

}