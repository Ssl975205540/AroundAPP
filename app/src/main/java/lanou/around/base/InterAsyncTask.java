package lanou.around.base;

import android.os.AsyncTask;

/**
 * Created by dllo on 16/10/25.
 */

public abstract class InterAsyncTask<T, E> extends AsyncTask<T, Void, E> {

//    protected DatabaseManager manager;

    private OnCompletedListener<E> eOnCompletedListener;



    public void seteOnCompletedListener(OnCompletedListener<E> eOnCompletedListener) {
        this.eOnCompletedListener = eOnCompletedListener;
    }

//    public InterAsyncTask(DatabaseManager manager) {
//        this.manager = manager;
//
//    }

    @Override
    protected E doInBackground(T... params) {
        return doSomething(params[0]);


    }

    protected abstract E doSomething(T param);



    @Override
    protected void onPostExecute(E e) {
        super.onPostExecute(e);

        if(eOnCompletedListener != null){

            eOnCompletedListener.onComleted(e);

        }

    }


    public interface OnCompletedListener<T> {

        void onComleted(T result);

    }

}
