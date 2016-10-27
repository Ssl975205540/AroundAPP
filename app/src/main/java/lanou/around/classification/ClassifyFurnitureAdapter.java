package lanou.around.classification;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import lanou.around.bean.ClassifyBean;

/**
 * Created by dllo on 16/10/26.
 */
public class ClassifyFurnitureAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    private ClassifyBean mClassifyBean;

    public void setClassifyBean(ClassifyBean classifyBean) {
        mClassifyBean = classifyBean;
    }

    public ClassifyFurnitureAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mClassifyBean.getRespData().get(6).getSubCateArr().size();
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
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(15);
            textView.setPadding(8, 4, 8, 4);//设置间距
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(mClassifyBean.getRespData().get(6).getSubCateArr().get(position).getSubCateName());
        return textView;
    }
}