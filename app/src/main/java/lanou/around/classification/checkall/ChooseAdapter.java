package lanou.around.classification.checkall;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;

/**
 * Created by dllo on 16/11/3.
 */
public class ChooseAdapter extends BaseAdapter<String> {

    public ChooseAdapter(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public int getContentView() {
        return R.layout.item_choose;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView name = get(view, R.id.tv_choose);
        name.setText(list.get(position));
        Log.d("ChooseAdapter", list.get(position));
    }


}
