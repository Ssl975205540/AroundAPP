package lanou.around.classification;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/22.
 */

public class ClassifyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ClassifyBean> strings;


    public void setStrings(List<ClassifyBean> strings) {
        this.strings = strings;
    }

    public ClassifyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return strings.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false);
                OneViewHolder viewHolder = new OneViewHolder(view1);
                return viewHolder;
            case 2:
                View view2 = LayoutInflater.from(context).inflate(R.layout.simple_list_item_2, parent, false);
                TwoViewHolder viewHolder2 = new TwoViewHolder(view2);
                return viewHolder2;
            case 3:
                View view3 = LayoutInflater.from(context).inflate(R.layout.simple_list_item_3, parent, false);
                ThreeViewHolder viewHolder3 = new ThreeViewHolder(view3);
                return viewHolder3;
            case 4:
                View view4 = LayoutInflater.from(context).inflate(R.layout.simple_list_item_4, parent, false);
                FourViewHolder viewHolder4 = new FourViewHolder(view4);
                return viewHolder4;
            case 5:
                View view5 = LayoutInflater.from(context).inflate(R.layout.simple_list_item_5, parent, false);
                FiveViewHolder viewHolder5 = new FiveViewHolder(view5);
                return viewHolder5;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case 1:
                OneViewHolder viewHolder = (OneViewHolder) holder;
                viewHolder.tv_left_name.setText(strings.get(position).getName());
                viewHolder.tv_left_message.setText(strings.get(position).getMessage());
                break;
            case 2:
                TwoViewHolder viewHolder2 = (TwoViewHolder) holder;
                viewHolder2.tv_left_name2.setText(strings.get(position).getName());
                viewHolder2.tv_left_message2.setText(strings.get(position).getMessage());

                LayoutInflater lf = LayoutInflater.from(context);
                View view1 = lf.inflate(R.layout.layout1, null);
                View view2 = lf.inflate(R.layout.layout2, null);
                ArrayList<View> viewList = new ArrayList<>();
                viewList.add(view1);
                viewList.add(view2);
                ClassifyDigitAdapter adapter = new ClassifyDigitAdapter(context);
                adapter.setViewList(viewList);
                viewHolder2.viewPager.setAdapter(adapter);
                break;
            case 3:
                ThreeViewHolder viewHolder3 = (ThreeViewHolder) holder;
                viewHolder3.tv_left_name3.setText(strings.get(position).getName());
                viewHolder3.tv_left_message3.setText(strings.get(position).getMessage());
                break;
            case 4:
                FourViewHolder viewHolder4 = (FourViewHolder) holder;
                viewHolder4.tv_left_name4.setText(strings.get(position).getName());
                viewHolder4.tv_left_message4.setText(strings.get(position).getMessage());
                break;
            case 5:
                FiveViewHolder viewHolder5 = (FiveViewHolder) holder;
                viewHolder5.tv_left_name5.setText(strings.get(position).getName());
                viewHolder5.tv_left_message5.setText(strings.get(position).getMessage());
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv_left_message2;
        public final TextView tv_left_name2;
        private final ViewPager viewPager;

        public TwoViewHolder(View itemView) {
            super(itemView);

            tv_left_message2 = (TextView) itemView.findViewById(R.id.tv_digit_name);
            tv_left_name2 = (TextView) itemView.findViewById(R.id.tv_digit_message);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager_digit);
        }
    }

    class ThreeViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv_left_message3;
        public final TextView tv_left_name3;

        public ThreeViewHolder(View itemView) {
            super(itemView);

            tv_left_message3 = (TextView) itemView.findViewById(R.id.tv_left_message3);
            tv_left_name3 = (TextView) itemView.findViewById(R.id.tv_left_name3);
        }
    }

    class FourViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv_left_message4;
        public final TextView tv_left_name4;

        public FourViewHolder(View itemView) {
            super(itemView);
            tv_left_message4 = (TextView) itemView.findViewById(R.id.tv_left_message4);
            tv_left_name4 = (TextView) itemView.findViewById(R.id.tv_left_name4);
        }
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv_left_message;
        public final TextView tv_left_name;

        public OneViewHolder(View itemView) {
            super(itemView);

            tv_left_message = (TextView) itemView.findViewById(R.id.tv_phone_message);
            tv_left_name = (TextView) itemView.findViewById(R.id.tv_phone_name);
        }
    }

    class FiveViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv_left_message5;
        public final TextView tv_left_name5;

        public FiveViewHolder(View itemView) {
            super(itemView);

            tv_left_message5 = (TextView) itemView.findViewById(R.id.tv_left_message5);
            tv_left_name5 = (TextView) itemView.findViewById(R.id.tv_left_name5);
        }
    }
}
