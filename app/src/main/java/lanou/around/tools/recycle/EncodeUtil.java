package lanou.around.tools.recycle;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by dllo on 16/10/31.
 */

public class EncodeUtil {

    // 将文字转为%%%%
    public String encode(String str){
        try {
            Log.d("EncodeUtil", URLEncoder.encode(str, "utf-8"));
            return URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;


    }
    // 将%% 转为文字
    public static String decode(String utf){
        try {
            return URLDecoder.decode(utf,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf;
    }
}
