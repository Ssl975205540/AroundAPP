package lanou.around.video;

import android.widget.ImageButton;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.base.BaseFragment;

/**
 * Created by dllo on 16/10/22.
 */

public class VideoFragment extends BaseFragment {

    private TextView video_title;
    private ImageButton video_code;
    @Override
    protected int setContentView() {
        return R.layout.video_fragment;
        //设置标题栏

    }

    @Override
    protected void initViews() {
        video_title = findView(R.id.video_title_tv);
        video_code = findView(R.id.video_code);


    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
       video_title.setText("视频");
        video_code.setImageResource(R.mipmap.ic_launcher);

    }

}
