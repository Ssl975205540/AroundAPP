package lanou.around.classification.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;

/**
 * Created by dllo on 16/11/1.
 */
public class AreaAdapter extends BaseAdapter {
    List<String> provinceBeanList;
    Context mContext;

    public AreaAdapter(Context context) {
        mContext = context;
    }

    public void setProvinceBeanList(List<String> provinceBeanList) {
        this.provinceBeanList = provinceBeanList;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return provinceBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTv.setText(provinceBeanList.get(position));

        return convertView;
    }

    private class ViewHolder {

        private final TextView mTv;

        public ViewHolder(View convertView) {
            mTv = (TextView) convertView.findViewById(R.id.tv);
        }

    }
}
