package lanou.around.model;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;
import lanou.around.home.HomeTabBean;
import lanou.around.tools.db.AroundDBManager;
import lanou.around.tools.http.HtttpManger;
import lanou.around.tools.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/25.
 */

public class HomeModel implements InterModel {


    @Override
    public <T> void StartRequest(String url, final Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {

        HtttpManger.getInstance().getRequest(url, tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(Object tClass1) {


                if(tClass1 instanceof HomeBean){
                    if (((HomeBean) tClass1).getRespCode().equals("0")) {

                        onFinishedListener.onFinished((T) tClass1);
                        return;
                    } else {
                        onFailed();
                    }
                }

                if (tClass1 instanceof HomeTabBean){


                    Log.d("HomeModel", "ni你妈的");
                    onFinishedListener.onFinished((T) tClass1);

                }



            }


            @Override
            public void onFailed() {

                QuerySQ(new OnCompleted<ArrayList<HomeBeanHot>>() {

                    @Override
                    public void onCompleted(ArrayList<HomeBeanHot> result) {
                        if (result.size() != 0) {
                            HomeBean homeBean = new HomeBean();
                            homeBean.setRespCode("0");
                            homeBean.setRespData(new HomeBean.RespDataBean());
                            homeBean.getRespData().setActBanners(new ArrayList<HomeBean.RespDataBean.ActBannersBean>());
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
                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setImageUrl(result.get(j).getImageUrl());
//                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setGoOperation(result.get(j).getGoOperation());

                                    }
                                }

                                if (i == 1) {

                                    for (int j = 0; j < 3; j++) {
                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setImageUrl(result.get(j + 3).getImageUrl());
//                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).setGoOperation(result.get(j+3).getGoOperation());
                                    }

                                }


                                if (i == 2) {
                                    for (int k = 0; k < 3; k++) {
                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(k).setImageUrl(result.get(k + 6).getImageUrl());
//                                        homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(k).setGoOperation(result.get(k+6).getGoOperation());

                                    }

                                }

                            }


                            onFinishedListener.onFinished((T) homeBean);

                        } else {
                            onFailed();
                        }

                    }

                    @Override
                    public void onFailed() {
                        onFinishedListener.onError();


                    }
                });
            }


        });
    }

    @Override
    public <F> void InsertSQ(F t) {
        if(t instanceof HomeBean){
            AroundDBManager.getInstance().insert((HomeBean) t);

        }
    }

    @Override
    public <E> void QuerySQ(OnCompleted<E> onCompletedListener) {
        myAsyncTask myAsyncTask = new myAsyncTask();
        myAsyncTask.setArrayListOnCompletedListener((OnCompleted<ArrayList<HomeBeanHot>>) onCompletedListener);
        myAsyncTask.execute();
    }


    class myAsyncTask extends AsyncTask<Void, Void, ArrayList<HomeBeanHot>> {


        private OnCompleted<ArrayList<HomeBeanHot>> arrayListOnCompletedListener;

        public void setArrayListOnCompletedListener(OnCompleted<ArrayList<HomeBeanHot>> arrayListOnCompletedListener) {
            this.arrayListOnCompletedListener = arrayListOnCompletedListener;
        }

        @Override
        protected ArrayList<HomeBeanHot> doInBackground(Void... params) {

            return (ArrayList<HomeBeanHot>) AroundDBManager.getInstance().query("homegettopbanner");

        }

        @Override
        protected void onPostExecute(ArrayList<HomeBeanHot> o) {
            super.onPostExecute(o);

            arrayListOnCompletedListener.onCompleted(o);

            arrayListOnCompletedListener.onFailed();


        }


    }

}
