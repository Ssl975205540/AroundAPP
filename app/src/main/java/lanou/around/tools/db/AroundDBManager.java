package lanou.around.tools.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import lanou.around.app.AroundAPP;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;

/**
 * Created by dllo on 16/10/25.
 */

public class AroundDBManager {

    public  AroundHelper aroundHelper;


    public static AroundDBManager getInstance(){
        
        return Svvv.aroundDBManager;
    }
    
    private final static class Svvv{

        private final static AroundDBManager aroundDBManager = new AroundDBManager();

    }

    public AroundDBManager() {

        aroundHelper = new AroundHelper(AroundAPP.getContext());
    
    }
    
    
    public void insert(HomeBean bean){
       SQLiteDatabase db = aroundHelper.getWritableDatabase();
        ArrayList<HomeBeanHot> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                HomeBeanHot homeBeanHot = new HomeBeanHot();
                homeBeanHot.setImageUrl(bean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getImageUrl());
//                homeBeanHot.setGoOperation(bean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getGoOperation());
                arrayList.add(homeBeanHot);
            }
        }
        Log.d("AroundDBManager", "arrayList.size():" + arrayList.size());
        db.delete("homegettopbanner",null,null);

        for (int j = 0; j < arrayList.size(); j++) {

            ContentValues content = new ContentValues();
            content.put("url",arrayList.get(j).getImageUrl());
            db.insert("homegettopbanner",null,content);
        }


    }


     public Object query(String key){

        SQLiteDatabase db = aroundHelper.getWritableDatabase();

        if(key.equals("homegettopbanner")){
            ArrayList<HomeBeanHot> arraylist = new ArrayList<>();
          Cursor cursor = db.query(key,null,null,null,null,null,null);
            if(cursor.getCount() != 0){
                while (cursor.moveToNext()){
                    String url = cursor.getString(cursor.getColumnIndex("url"));
                HomeBeanHot home = new HomeBeanHot();
                    Log.d("AroundDBManager", "ddd");
                    home.setImageUrl(url);

                    arraylist.add(home);
                    Log.d("AroundDBManager", "arraylist.size():" + arraylist.size());
                }

            }

            return arraylist;
        }


         return null;
    }
    
    
    
}
