package lanou.around.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/10/20.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initJudge();
        setContentView(setContentView());
        initViews();
        initListeners();
        initData();


    }


    protected abstract int setContentView();

    protected abstract void initViews();

    protected abstract void initListeners();

    protected abstract void initData();

    protected <T extends View> T findView(int id) {

        return (T) findViewById(id);
    }

    protected <T extends View> T findView(View view, int id) {

        return (T) view.findViewById(id);
    }


    protected void initJudge() {

    }


}

