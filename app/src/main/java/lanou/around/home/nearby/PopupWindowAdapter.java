package lanou.around.home.nearby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;

/**
 * Created by dllo on 16/10/29.
 */
public class PopupWindowAdapter extends RecyclerView.Adapter<PopupWindowAdapter.ViewHolder> {
    private List<String> arrayList;
    private List<String> mStringList;
    private Context context;
    private OnItemClickListener popupOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClickListener(View itemView, int layoutPosition);
    }

    public void setPopupOnItemClickListener(OnItemClickListener popupOnItemClickListener) {
        this.popupOnItemClickListener = popupOnItemClickListener;
    }

    public void setArrayList(List<String> arrayList,List<String> mStringList) {
        this.arrayList = arrayList;
        this.mStringList = mStringList;
        notifyDataSetChanged();
    }

    public PopupWindowAdapter(Context context) {

        this.context = context;
    }

    @Override
    public PopupWindowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popup, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mName.setText(arrayList.get(position));
        holder.mBody.setText(mStringList.get(position));

        if (popupOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupOnItemClickListener.OnItemClickListener
                            (holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView mName;
        private final TextView mBody;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_popup_name);
            mBody = (TextView) itemView.findViewById(R.id.tv_popup_body);

        }
    }
}
