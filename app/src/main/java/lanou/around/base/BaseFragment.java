package lanou.around.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/10/20.
 */

public abstract class BaseFragment extends Fragment {

    public Context context;
    private int statusBarHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(setContentView(), container,false);


    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
        initData();
        initLoad();
    }

    protected abstract int setContentView();

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void initListeners();

    protected void initLoad() {

    }

    protected <T extends View> T findView(int id) {

        return (T) getView().findViewById(id);
    }

    protected <T extends View> T findView(View view, int id) {

        return (T) view.findViewById(id);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }



}
