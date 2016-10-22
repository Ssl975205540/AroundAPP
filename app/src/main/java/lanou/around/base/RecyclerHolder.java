package lanou.around.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dllo on 16/10/22.
 */

public class RecyclerHolder extends RecyclerView.ViewHolder {

    public View rootView;

    public RecyclerHolder(View itemView) {
        super(itemView);
        rootView = itemView;
    }
}