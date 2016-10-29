package lanou.around.classification;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import lanou.around.bean.ClassifyBean;

/**
 * Created by dllo on 16/10/24.
 */
public class ClassifyGridAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    private ClassifyBean mClassifyBean;

    public void setClassifyBean(ClassifyBean classifyBean) {
        mClassifyBean = classifyBean;
    }

    public ClassifyGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mClassifyBean.getRespData().get(2).getSubCateArr().size();
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
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(15);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundColor(Color.WHITE);
            textView.setPadding(8, 4, 8, 4);//设置间距
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(mClassifyBean.getRespData().get(2).getSubCateArr().get(position).getSubCateName());
        return textView;
    }
}
