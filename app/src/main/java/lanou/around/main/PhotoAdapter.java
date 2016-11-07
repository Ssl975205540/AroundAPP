package lanou.around.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.base.CanRVAdapter;
import lanou.around.bean.PictureBean;
import lanou.around.tools.db.CanHolderHelper;

import static lanou.around.tools.db.CanHolderHelper.convertToBitmap;

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



    public List<PictureBean> getCheckPhoto() {
        List<PictureBean> list = new ArrayList<>();

        if(mList.size()>12){
            for (int i = 0; i < 12; i++) {

                if (mList.get(i).check == true) {
                    list.add(mList.get(i));
                }
            }
        }else {
            for (int i = 0; i < mList.size(); i++) {

                if (mList.get(i).check == true) {
                    list.add(mList.get(i));
                }

            }
        }




        return list;
    }
}
