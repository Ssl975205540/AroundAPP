package lanou.around.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/25.
 */

public class AroundAPP extends Application{

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    public static Context getContext(){



        return context;
    }
}
