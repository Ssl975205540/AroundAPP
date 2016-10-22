package lanou.around.base;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by dllo on 16/10/22.
 */

public abstract class BaseRcvAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected OnItemClickListener onItemClickListener;          //单击
    protected View.OnLongClickListener onLongClickListener;     //长按
    protected boolean isEdit = false;                           //是否进入编辑模式

    protected Context context;
    protected Fragment fragment;
    protected LayoutInflater mLayoutInflater;
    protected List<Object> mList;

    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

    public boolean getEdit() {
        return isEdit;
    }

    public BaseRcvAdapter(Context context, List<Object> mList) {
        this.mList = mList;
        this.context = fragment.getActivity();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public BaseRcvAdapter(Fragment fragment, List<Object> mList) {
        this.fragment = fragment;
        this.mList = mList;
        this.context = fragment.getActivity();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }

        if (onLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongClickListener.onLongClick(v);
                    return true;
                }
            });
        }
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }
    public void add(Integer position, Object item) {
        mList.add(position, item);
        notifyItemInserted(position);
    }
    public void add(Object item) {
        mList.add(item);
        notifyDataSetChanged();
    }
}