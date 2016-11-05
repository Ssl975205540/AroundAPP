package lanou.around.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/21.
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    public List<T> list;
    public Context mContext;
    public Object object;
    private LayoutInflater inflater;

    public BaseAdapter(Context context) {
        init(context, new ArrayList<T>());
    }

    public BaseAdapter(Context context, List<T> list) {
        init(context, list);
    }

    private void init(Context context, List<T> list) {
        this.mContext = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (null != list) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        if (null != list) {
            this.list.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflate(getContentView());
        }

        onInitView(convertView, position);


        return convertView;
    }

    /**
     * 加载布局
     */
    private View inflate(int layoutResID) {
        View view = inflater.inflate(layoutResID, null);
        return view;
    }

    public abstract int getContentView();

    public abstract void onInitView(View view, int position);

    /**
     * @param view converView
     * @param id   控件的id
     * @return 返回<T extends View>
     */
    @SuppressWarnings("unchecked")
    protected <E extends View> E get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (null == viewHolder) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (null == childView) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);

        }
        return (E) childView;
    }

}