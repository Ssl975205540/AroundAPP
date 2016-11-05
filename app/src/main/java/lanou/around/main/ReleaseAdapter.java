package lanou.around.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.CanRVAdapter;
import lanou.around.tools.db.CanHolderHelper;

/**
 * Created by dllo on 16/11/4.
 */

public class ReleaseAdapter extends CanRVAdapter<String>{
    public ReleaseAdapter(RecyclerView mRecyclerView, int itemLayoutId, List<String> mList) {
        super(mRecyclerView, itemLayoutId, mList);
    }

    public interface OnItemClickLitener
    {
        void onItemClick(CanHolderHelper view, int position);
    }

    private ReleaseAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(ReleaseAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    protected void setView(CanHolderHelper viewHelper, int position, String model) {
        ImageView imageView = viewHelper.getView(R.id.img_item);
        if(mList.get(position).equals("")){
            imageView.setBackgroundResource(R.mipmap.ic_launcher);

//                viewHelper.getView(R.id.relayout_rekease).setVisibility(View.GONE);

        }else {


            imageView.setImageBitmap(CanHolderHelper.convertToBitmap(model,200,200));
//            viewHelper.getView(R.id.relayout_rekease).setVisibility(View.GONE);

        }


    }

    @Override
    protected void setItemListener(final CanHolderHelper viewHelper, final int position) {

        viewHelper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickLitener.onItemClick(viewHelper,position);
            }
        });



    }










}


