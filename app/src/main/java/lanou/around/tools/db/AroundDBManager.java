package lanou.around.tools.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < bean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().size(); i++) {
            for (int j = 0; j < bean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(0).size(); j++) {
                HomeBeanHot homeBeanHot = new HomeBeanHot();
                homeBeanHot.setImageUrl(bean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getImageUrl());
//                homeBeanHot.setGoOperation(bean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getGoOperation());
                arrayList.add(homeBeanHot);
            }
        }


            db.delete("homegettopbanner",null,null);

        for (int j = 0; j < arrayList.size(); j++) {

            ContentValues content = new ContentValues();
            content.put("url",arrayList.get(j).getImageUrl());
            db.insert("homegettopbanner",null,content);
        }

        for (int i = 0; i <bean.getRespData().getTopBanners().size(); i++) {

            ContentValues content = new ContentValues();
            content.put("topbanners",bean.getRespData().getTopBanners().get(i).getImageUrl());
            db.insert("homegettopbanner",null,content);

        }


        for (int i = 0; i < bean.getRespData().getLowBanners().size(); i++) {


            ContentValues content = new ContentValues();
            content.put("lowbanners",bean.getRespData().getLowBanners().get(i).getImageUrl());
            db.insert("homegettopbanner",null,content);

        }


    }


     public HomeBean query(String key){

         SharedPreferences sharedPreferences = AroundAPP.getContext().getSharedPreferences("aroundfirst", Context.MODE_PRIVATE);
         SharedPreferences.Editor edit = sharedPreferences.edit();
         edit.commit();

         if(sharedPreferences.getBoolean("first",true)){
             edit.putBoolean("first",false);
             edit.commit();
             return null;
         }

         SQLiteDatabase db = aroundHelper.getWritableDatabase();
        if(key.equals("homegettopbanner")){
            ArrayList<HomeBeanHot> arraylist = new ArrayList<>();
            ArrayList<HomeBean.RespDataBean.TopBannersBean> topArrayList = new ArrayList<>();
            ArrayList<HomeBean.RespDataBean.LowBannersBean> lowArrayList = new ArrayList<>();



            Cursor cursor = db.query(key,null,null,null,null,null,null);
            if(cursor.getCount() != 0){
                while (cursor.moveToNext()){
                    String url = cursor.getString(cursor.getColumnIndex("url"));
                    HomeBeanHot home = new HomeBeanHot();
                    home.setImageUrl(url);
                    arraylist.add(home);
                    String topbanners =cursor.getString(cursor.getColumnIndex("topbanners"));
                    String lowbanners = cursor.getString(cursor.getColumnIndex("lowbanners"));

                    if(lowbanners != null){
                        HomeBean.RespDataBean.LowBannersBean low = new HomeBean.RespDataBean.LowBannersBean();
                        lowArrayList.add(low);

                    }
                    if(topbanners != null){
                        HomeBean.RespDataBean.TopBannersBean top = new HomeBean.RespDataBean.TopBannersBean();
                        top.setImageUrl(topbanners);
                        topArrayList.add(top);
                    }

                }

            }else {
                return null;
            }




            HomeBean homeBean = new HomeBean();
            homeBean.setRespCode("0");
            homeBean.setRespData(new HomeBean.RespDataBean());
            homeBean.getRespData().setActBanners(new ArrayList<HomeBean.RespDataBean.ActBannersBean>());

            homeBean.getRespData().setLowBanners(lowArrayList);



            homeBean.getRespData().setTopBanners(topArrayList);
            homeBean.getRespData().getActBanners().add(new HomeBean.RespDataBean.ActBannersBean());
            homeBean.getRespData().getActBanners().get(0).setMiddleBanner(new HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean());
            List<List<HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean>> arrayLists = new ArrayList<>();
            for (int i = 0; i < 3; i++) {

                ArrayList<HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean> arrayList = new ArrayList<>();
                arrayList.add(new HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean());
                arrayList.add(new HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean());
                arrayList.add(new HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean());
                arrayLists.add(arrayList);

            }



            homeBean.getRespData().getActBanners().get(0).getMiddleBanner().setBanners(arrayLists);

            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    for (int j = 0; j < 3; j++) {
                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setImageUrl(arraylist.get(j).getImageUrl());
//                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setGoOperation(result.get(j).getGoOperation());

                    }
                }

                if (i == 1) {

                    for (int j = 0; j < 3; j++) {
                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setImageUrl(arraylist.get(j + 3).getImageUrl());
//                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setGoOperation(result.get(j+3).getGoOperation());
                    }

                }


                if (i == 2) {
                    for (int k = 0; k < 3; k++) {
                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(k).setImageUrl(arraylist.get(k + 6).getImageUrl());
//                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(k).setGoOperation(result.get(k+6).getGoOperation());

                    }

                }

            }



            return homeBean;
        }


         return null;
    }
    
    
    
}
