package lanou.around.aroundinterface;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

public interface CanOnItemListener {


    void onItemChildClick(View view, int position);


    boolean onItemChildLongClick(View view, int position);

    void onItemChildCheckedChanged(CompoundButton view, int position, boolean isChecked);

    void onRVItemClick(ViewGroup parent, View itemView, int position);

    boolean onRVItemLongClick(ViewGroup parent, View itemView, int position);
}
