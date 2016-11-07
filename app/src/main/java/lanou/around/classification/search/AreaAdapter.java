package lanou.around.classification.search;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import lanou.around.R;
import lanou.around.base.BaseAdapter;

/**
 * Created by dllo on 16/11/1.
 */
public class AreaAdapter extends BaseAdapter<String> {
    public AreaAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_lv;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView textView = get(view, R.id.tv);
        textView.setText(list.get(position));
    }
}
