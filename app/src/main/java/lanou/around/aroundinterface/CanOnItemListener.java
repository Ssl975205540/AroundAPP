package lanou.around.aroundinterface;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

public class CanOnItemListener {


    public void onItemChildClick(View view, int position) {
    }

    public boolean onItemChildLongClick(View view, int position) {
        return false;
    }

    public void onItemChildCheckedChanged(CompoundButton view, int position, boolean isChecked) {
    }

    public void onRVItemClick(ViewGroup parent, View itemView, int position){}

    public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position){return false;}
}
