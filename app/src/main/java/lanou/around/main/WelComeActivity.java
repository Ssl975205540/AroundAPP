package lanou.around.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.around.R;
import lanou.around.app.AroundAPP;
import lanou.around.base.BaseActivity;
import lanou.around.tools.http.URLValues;
import lanou.around.widget.RoundProgressBar;

public class WelComeActivity extends BaseActivity {


    private int progress = 0;
    private ImageView img1Welcome, img2Welcome;
    private TextView textWelcome;
    private RoundProgressBar roundProgressBar;
    private Thread thread;

    @Override
    protected int setContentView() {
        return R.layout.activity_wel_come;
    }

    @Override
    protected void initJudge() {

        if (AroundAPP.isNetworkAvailable() == false) {
            Intent i = new Intent(WelComeActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }


    }

    @Override
    protected void initViews() {
        img1Welcome = findView(R.id.img1_welcome);
        img2Welcome = findView(R.id.img2_welcome);
        textWelcome = findView(R.id.text_welcome);
        roundProgressBar = findView(R.id.roundProgressBar1);
    }

    @Override
    protected void initListeners() {

        roundProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thread.interrupt();
                Intent i = new Intent(WelComeActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

    @Override
    protected void initData() {
        img2Welcome.setBackgroundResource(R.mipmap.ks);
        Picasso.with(this).load(URLValues.WELCOME).into(img1Welcome);
        WelAsyncTask welAsyncTask = new WelAsyncTask();
        welAsyncTask.execute();
    }


    public class WelAsyncTask extends AsyncTask<Void, String, Void> {


        @Override
        protected Void doInBackground(Void... params) {


            thread = new Thread(new Runnable() {

                private int c = 5;

                @Override
                public void run() {
                    try {
                        for (int j = 0; j <= 100; j++) {
                            if (progress == 0 || progress == 20 || progress == 40 || progress == 60 || progress == 80 || progress == 100) {
                                publishProgress(String.valueOf(c));
                                c--;
                            }
                            roundProgressBar.setProgress(progress);
                            progress += 1;
                            Thread.sleep(50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            thread.start();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textWelcome.setText(values[0]);
            if (AroundAPP.isNetworkAvailable() == true) {
                if (values[0].equals("0")) {

                    Intent i = new Intent(WelComeActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        thread.interrupt();

    }
}
