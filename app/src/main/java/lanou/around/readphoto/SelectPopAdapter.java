package lanou.around.readphoto;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;
import lanou.around.bean.PhotoBean;
import lanou.around.tools.db.CanHolderHelper;

/**
 * Created by dllo on 16/11/8.
 */

public class SelectPopAdapter extends BaseAdapter<PhotoBean> {


    public SelectPopAdapter(Context context, List<PhotoBean> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.select_pop_item;
    }

    @Override
    public void onInitView(View view, int position) {

        ImageView img = get(view,R.id.img);
        img.setImageBitmap(CanHolderHelper.convertToBitmap(list.get(position).bitList.get(0).path,150,150));
        TextView text = get(view,R.id.text);
        text.setText(list.get(position).name);
    }
}
