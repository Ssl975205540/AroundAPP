package lanou.around.classification.seek;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseAdapter;

/**
 * Created by dllo on 16/11/8.
 */
public class SeekSuggestAdapter extends BaseAdapter<SeekSuggestBean.RespDataBean> {

    public SeekSuggestAdapter(Context context, List<SeekSuggestBean.RespDataBean> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_seek;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView textView = get(view, R.id.tv1);
        textView.setText(list.get(position).getK());
    }
}
