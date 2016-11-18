package lanou.around.model;

import android.os.AsyncTask;

import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeTabBean;
import lanou.around.tools.db.AroundDBManager;
import lanou.around.tools.http.HttpManger;
import lanou.around.aroundinterface.OnCompletedListener;

/**
 * Created by dllo on 16/10/25.
 */

public class HomeModel implements InterModel {


    @Override
    public <T> void StartRequest(String url, final Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {

        HttpManger.getInstance().getRequest(url, tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(Object tClass1) {

                if (tClass1 instanceof HomeBean) {
                    if (AroundAPP.isNetworkAvailable()) {


                        onFinishedListener.onFinished((T) tClass1);
                    } else {
                        onFailed();
                    }
                }
                if (tClass1 instanceof HomeTabBean) {
                    onFinishedListener.onFinished((T) tClass1);
                }
            }

            @Override
            public void onFailed() {
                QuerySQ(new OnFinishedListener<HomeBean>() {
                    @Override
                    public void onFinished(HomeBean result) {
                        if (result != null) {
                            onFinishedListener.onFinished((T) result);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        });
    }

    @Override
    public <F> void InsertSQ(F t) {
        if (t instanceof HomeBean) {

            InsertSqAsyncTask insertSqAsyncTask = new InsertSqAsyncTask();
            insertSqAsyncTask.execute((HomeBean) t);



        }
    }

    @Override
    public <E> void QuerySQ(OnFinishedListener<E> onCompletedListener) {
        myAsyncTask myAsyncTask = new myAsyncTask();
        myAsyncTask.setArrayListOnCompletedListener((OnFinishedListener<HomeBean>) onCompletedListener);
        myAsyncTask.execute();
    }


    class InsertSqAsyncTask extends AsyncTask<HomeBean, Void, HomeBean> {


        @Override
        protected HomeBean doInBackground(HomeBean... params) {

            AroundDBManager.getInstance().insert(params[0]);

            return null;
        }
    }


    class myAsyncTask extends AsyncTask<Void, Void, HomeBean> {


        private OnFinishedListener<HomeBean> arrayListOnCompletedListener;

        public void setArrayListOnCompletedListener(OnFinishedListener<HomeBean> arrayListOnCompletedListener) {
            this.arrayListOnCompletedListener = arrayListOnCompletedListener;
        }

        @Override
        protected HomeBean doInBackground(Void... params) {

            return AroundDBManager.getInstance().query("homegettopbanner");

        }

        @Override
        protected void onPostExecute(HomeBean o) {
            super.onPostExecute(o);

            arrayListOnCompletedListener.onFinished(o);

            arrayListOnCompletedListener.onError();


        }


    }


}
