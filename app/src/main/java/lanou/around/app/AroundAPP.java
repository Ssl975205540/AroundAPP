package lanou.around.app;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.uuzuche.lib_zxing.DisplayUtil;

/**
 * Created by dllo on 16/10/25.
 */

public class AroundAPP extends Application{

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        initDisplayOpinion();
    }

    public static Context getContext(){



        return context;
    }


    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }
}
