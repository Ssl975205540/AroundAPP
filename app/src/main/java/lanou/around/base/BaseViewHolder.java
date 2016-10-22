package lanou.around.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/22.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    protected SparseArray<View> views;
    protected Context context;
    protected Resources resources;
    protected View convertView;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.convertView = itemView;
        resources = context.getResources();
        this.views = new SparseArray<View>();
    }

    /**
     * 设置TextView内容
     *
     * @param viewId
     * @param value
     * @return
     */
    public BaseViewHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        if (!TextUtils.isEmpty(value)) {
            view.setText(value);
        }
        return this;
    }

    public BaseViewHolder setText(int viewId, int value) {
        TextView view = getView(viewId);
        view.setText(resources.getString(value));
        return this;
    }

//    /**
//     * 设置SimpleDraweeView的图片
//     *
//     * @param viewId
//     * @param url
//     * @return
//     */
//    public BaseViewHolder setSimpleDraweeView(int viewId, String url) {
//
//        SimpleDraweeView view = getView(viewId);
//        if (!TextUtils.isEmpty(url)) {
//            view.setImageURI(Uri.parse(url));
//        }
//        return this;
//    }

    /**
     * 设置开/关两种状态的按钮
     *
     * @param viewId
     * @param checked
     * @return
     */
    public BaseViewHolder setCheck(int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }

    /**
     * 设置adapter(ListView/GridView都继承AdapterView)
     *
     * @param viewId
     * @param adapter
     * @return
     */
    public BaseViewHolder setAdapter(int viewId, RecyclerView.Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter((Adapter) adapter);
        return this;
    }

    /**
     * 设置Rcv的adapter
     *
     * @param viewId
     * @param adapter
     * @return
     */
    public BaseViewHolder setRcvAdapter(int viewId, RecyclerView.Adapter adapter) {
        RecyclerView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * 得到相信的view
     *
     * @param viewId view的id
     * @param <T>    view本身
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}