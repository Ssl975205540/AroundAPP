package lanou.around.classification;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/24.
 */
public class ClassifyGridAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    //图片数组
    private String[] imgs = {
            "00", "11", "22", "33", "44", "55"
    };

    public ClassifyGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(context);
            textView.setLayoutParams(new GridView.LayoutParams(75, 75));//设置ImageView对象布局
            textView.setPadding(8, 8, 8, 8);//设置间距
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(imgs[position]);//为ImageView设置图片资源
        return textView;
    }
}
