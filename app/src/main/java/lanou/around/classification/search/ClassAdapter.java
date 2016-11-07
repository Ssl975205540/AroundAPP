package lanou.around.classification.search;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;

/**
 * Created by dllo on 16/11/7.
 */
public class ClassAdapter extends BaseAdapter<String> {
    public ClassAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_kind;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView name = get(view, R.id.tv_kind_name);
        name.setText(list.get(position));
    }
}
