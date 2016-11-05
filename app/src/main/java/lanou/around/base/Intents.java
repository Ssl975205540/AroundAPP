package lanou.around.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dllo on 16/11/5.
 */

public class Intents {

    private static Intents intents;

    public static Intents getIntents(){
        if(intents == null)
            intents = new Intents();
        return intents;
    }
    // context this, cs跳转对象 bundle 传递参数
    public void Intent(Context context, Class<?> cs, Bundle bundle) {
        Intent i = new Intent(context, cs);
        if (bundle != null)
            i.putExtras(bundle);
        context.startActivity(i);
    }


}

