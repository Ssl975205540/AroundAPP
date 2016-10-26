package lanou.around.model;

import android.os.AsyncTask;

import java.util.ArrayList;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;
import lanou.around.tools.recycle.db.AroundDBManager;
import lanou.around.tools.recycle.http.HtttpManger;
import lanou.around.tools.recycle.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/25.
 */

public class HomeModel implements InterModel<HomeBean>{


    @Override
    public void StartRequest(String url , final OnFinishedListener onFinishedListener) {

        HtttpManger.getInstance().getRequest(url, HomeBean.class, new OnCompletedListener<HomeBean>() {
            @Override
            public void onCompleted(HomeBean result) {

                if(result.getRespCode().equals("0")){
                    onFinishedListener.onFinished(result);

                }else {
                    onFailed();
                }



            }

            @Override
            public void onFailed() {

                QuerySQ(new OCompleted<ArrayList<HomeBeanHot>>() {


                    @Override
                    public void onCompleted(ArrayList<HomeBeanHot> result) {

                        if(result.size() != 0){
                            onFinishedListener.onError();

                        }else {
                            onFailed(result);
                        }


                    }

                    @Override
                    public void onFailed(ArrayList<HomeBeanHot> result) {
                        onFinishedListener.onError();


                    }
                });
            }


        });

    }

    @Override
    public void InsertSQ(HomeBean homeBean) {

        AroundDBManager.getInstance().insert(homeBean);


    }

    @Override
    public <E> void QuerySQ(OCompleted<E> onCompletedListener) {
        myAsyncTask myAsyncTask = new myAsyncTask();
        myAsyncTask.setArrayListOnCompletedListener((OCompleted<ArrayList<HomeBeanHot>>) onCompletedListener);
        myAsyncTask.execute();
    }








    class myAsyncTask extends AsyncTask<Void, Void, ArrayList<HomeBeanHot>> {


        private OCompleted<ArrayList<HomeBeanHot>> arrayListOnCompletedListener;

        public void setArrayListOnCompletedListener(OCompleted<ArrayList<HomeBeanHot>> arrayListOnCompletedListener) {
            this.arrayListOnCompletedListener = arrayListOnCompletedListener;
        }

        @Override
        protected ArrayList<HomeBeanHot> doInBackground(Void... params) {

            return (ArrayList<HomeBeanHot>) AroundDBManager.getInstance().query("homegettopbanner");

        }

        @Override
        protected void onPostExecute(ArrayList<HomeBeanHot> o) {
            super.onPostExecute(o);
            if(o.size()!= 0){
                arrayListOnCompletedListener.onCompleted(o);
            }else {
                arrayListOnCompletedListener.onFailed(o);
            }


        }


    }

}
