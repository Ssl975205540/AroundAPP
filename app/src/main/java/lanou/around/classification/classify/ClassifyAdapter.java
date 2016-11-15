package lanou.around.classification.classify;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.classification.classifyview.DigitWebActivity;
import lanou.around.tools.util.IntentUtils;
import lanou.around.bean.ClassifyBean;
import lanou.around.classification.search.SearchActivity;

import static lanou.around.classification.classifyview.DigitWebActivity.BANNER_URL;
import static lanou.around.classification.search.SearchActivity.NAME;


/**
 * Created by dllo on 16/10/22.
 */

public class ClassifyAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<ImageView> dots = new ArrayList<>();
    private List<ClassifyBean.RespDataBean> mRespDataBeanList;

    public void setClassifyBean(ClassifyBean classifyBean) {
        mRespDataBeanList = classifyBean.getRespData();
    }

    public ClassifyAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return mRespDataBeanList.get(position).getType();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View phoneview = mInflater.inflate(R.layout.simple_item_phone, parent, false);
                PhoneViewHolder phoneviewHolder = new PhoneViewHolder(phoneview);
                return phoneviewHolder;
            case 1:
                View digitView = mInflater.inflate(R.layout.simple_item_digit, parent, false);
                DigitViewHolder digitViewHolder = new DigitViewHolder(digitView);
                return digitViewHolder;
            case 2:
                View computerView = mInflater.inflate(R.layout.simple_item_computer, parent, false);
                ComputerViewHolder computerViewHolder = new ComputerViewHolder(computerView);
                return computerViewHolder;
            case 3:
                View babyView = mInflater.inflate(R.layout.simple_item_baby, parent, false);
                BabyViewHolder babyViewHolder = new BabyViewHolder(babyView);
                return babyViewHolder;
            case 4:
                View equipmentView = mInflater.inflate(R.layout.simple_item_equipment, parent, false);
                EquipmentViewHolder equipmentViewHolder = new EquipmentViewHolder(equipmentView);
                return equipmentViewHolder;
            case 5:
                View electricalView = mInflater.inflate(R.layout.simple_item_electrical, parent, false);
                ElectricalViewHolder electricalViewHolder = new ElectricalViewHolder(electricalView);
                return electricalViewHolder;
            case 6:
                View furnitureView = mInflater.inflate(R.layout.simple_item_furniture, parent, false);
                FurnitureViewHolder furnitureViewHolder = new FurnitureViewHolder(furnitureView);
                return furnitureViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        List<ClassifyBean.RespDataBean.SubCateArrBean> subCateArr = mRespDataBeanList.get(position).getSubCateArr();

        switch (type) {
            case 0:
                PhoneViewHolder phoneViewHolder = (PhoneViewHolder) holder;
                phoneViewHolder.phone_name.setText(mRespDataBeanList.get(position).getCateName());
                phoneViewHolder.phone_message.setText(mRespDataBeanList.get(position).getCateDescribe());
                phoneViewHolder.mIPhone.setText(subCateArr.get(0).getSubCateName());
                phoneViewHolder.mMic.setText(subCateArr.get(1).getSubCateName());
                phoneViewHolder.mSanXing.setText(subCateArr.get(2).getSubCateName());
                phoneViewHolder.mHuawei.setText(subCateArr.get(3).getSubCateName());
                Picasso.with(context).load(mRespDataBeanList.get(0).getCateUrl())
                        .into(phoneViewHolder.mPhoto);

                phoneViewHolder.mLinear.setOnClickListener(new View.OnClickListener() {
                    Bundle bundle = new Bundle();
                    @Override
                    public void onClick(View v) {
                        bundle.putString(BANNER_URL, mRespDataBeanList.get(0).getBannerUrl());
                        IntentUtils.getIntents().Intent(context, DigitWebActivity.class, bundle);
                    }
                });
                break;
            case 1:
                DigitViewHolder digitViewHolder = (DigitViewHolder) holder;
                digitViewHolder.digit_name.setText(mRespDataBeanList.get(position).getCateName());
                digitViewHolder.digit_message.setText(mRespDataBeanList.get(position).getCateDescribe());
                digitViewHolder.mDigit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString(NAME, mRespDataBeanList.get(position).getCateName());
                        IntentUtils.getIntents().Intent(context,SearchActivity.class,bundle);
                    }
                });
                View viewLeft = mInflater.inflate(R.layout.layout_grid_left, null);
                viewLeftRequest(viewLeft,subCateArr);

                View viewRight = mInflater.inflate(R.layout.layout_grid_right, null);
                viewRightRequest(viewRight,subCateArr);

                ArrayList<View> viewList = new ArrayList<>();
                viewList.add(viewLeft);
                viewList.add(viewRight);
                ClassifyDigitAdapter adapter = new ClassifyDigitAdapter();
                adapter.setViewList(viewList);
                digitViewHolder.viewPager.setAdapter(adapter);
                //给ViewPager加小圆点
                viewPagerScall(digitViewHolder);
                break;
            case 2:
                ComputerViewHolder computerViewHolder = (ComputerViewHolder) holder;
                computerViewHolder.computerTitle.setText(mRespDataBeanList.get(position).getCateName());
                computerViewHolder.computerMessage.setText(mRespDataBeanList.get(position).getCateDescribe());

                ClassifyComputerAdapter gridAdapter = new ClassifyComputerAdapter(context,mRespDataBeanList.get(2).getSubCateArr());
                computerViewHolder.mGridView.setAdapter(gridAdapter);
                break;
            case 3:
                BabyViewHolder babyViewHolder = (BabyViewHolder) holder;
                babyViewHolder.babyName.setText(mRespDataBeanList.get(position).getCateName());
                babyViewHolder.babyMessage.setText(mRespDataBeanList.get(position).getCateDescribe());

                ClassifyBabyAdapter babyAdapter = new ClassifyBabyAdapter(context,
                        mRespDataBeanList.get(3).getSubCateArr());
                babyViewHolder.mGridView.setAdapter(babyAdapter);
                break;
            case 4:
                EquipmentViewHolder equipmentViewHolder = (EquipmentViewHolder) holder;
                equipmentViewHolder.equipmentName.setText(mRespDataBeanList.get(position).getCateName());
                equipmentViewHolder.equipmentMessage.setText(mRespDataBeanList.get(position).getCateDescribe());
                Picasso.with(context).load(subCateArr.get(0).getSubCateLogo()).into(equipmentViewHolder.mBike);
                equipmentViewHolder.bike.setText(subCateArr.get(0).getSubCateName());
                Picasso.with(context).load(subCateArr.get(1).getSubCateLogo()).into(equipmentViewHolder.mElectric);
                equipmentViewHolder.electric.setText(subCateArr.get(1).getSubCateName());
                Picasso.with(context).load(subCateArr.get(2).getSubCateLogo()).into(equipmentViewHolder.mMotor);
                equipmentViewHolder.motor.setText(subCateArr.get(2).getSubCateName());
                break;
            case 5:
                ElectricalViewHolder electricalViewHolder = (ElectricalViewHolder) holder;
                electricalViewHolder.electricalMessage.setText(mRespDataBeanList.get(position).getCateDescribe());
                electricalViewHolder.electricalTitle.setText(mRespDataBeanList.get(position).getCateName());
                ClassifyElectricalAdapter electricalAdapter = new ClassifyElectricalAdapter(context,
                        mRespDataBeanList.get(5).getSubCateArr());
                electricalViewHolder.mGridView.setAdapter(electricalAdapter);
                break;
            case 6:
                FurnitureViewHolder furnitureViewHolder = (FurnitureViewHolder) holder;
                furnitureViewHolder.furnitureTitle.setText(mRespDataBeanList.get(position).getCateName());
                furnitureViewHolder.furnitureMessage.setText(mRespDataBeanList.get(position).getCateDescribe());

                ClassifyFurnitureAdapter furnitureAdapter = new ClassifyFurnitureAdapter(context,
                        mRespDataBeanList.get(6).getSubCateArr());
                furnitureViewHolder.mGridView.setAdapter(furnitureAdapter);
                break;

        }
    }

    private void viewPagerScall(DigitViewHolder digitViewHolder) {
        for (int i = 0; i < 2; i++) {
            ImageView imageView = new ImageView(context);
            if (0 == i) {
                imageView.setImageResource(R.drawable.dot_normal);
            } else {
                imageView.setImageResource(R.drawable.dot_focus);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(params);
            dots.add(imageView);
            digitViewHolder.mDotsLayout.addView(dots.get(i));
        }
        digitViewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int a;
                if (dots.size() > 0) {
                    a = position % dots.size();
                    for (int i = 0; i < dots.size(); i++) {
                        if (i == a) {
                            dots.get(i).setImageResource(R.drawable.dot_normal);
                        } else {
                            dots.get(i).setImageResource(R.drawable.dot_focus);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void viewRightRequest(View viewRight, List<ClassifyBean.RespDataBean.SubCateArrBean> subCateArr) {
        TextView gameName = (TextView) viewRight.findViewById(R.id.tv_game);
        ImageView game = (ImageView) viewRight.findViewById(R.id.iv_game);
        TextView kindleName = (TextView) viewRight.findViewById(R.id.tv_kindle);
        ImageView kindle = (ImageView) viewRight.findViewById(R.id.iv_kindle);
        TextView mobilePowerName = (TextView) viewRight.findViewById(R.id.tv_mobile_power);
        ImageView mobilePower = (ImageView) viewRight.findViewById(R.id.iv_mobile_power);
        gameName.setText(subCateArr.get(3).getSubCateName());
        Picasso.with(context).load(subCateArr.get(3).getSubCateLogo()).into(game);
        kindleName.setText(subCateArr.get(4).getSubCateName());
        Picasso.with(context).load(subCateArr.get(4).getSubCateLogo()).into(kindle);
        mobilePowerName.setText(subCateArr.get(5).getSubCateName());
        Picasso.with(context).load(subCateArr.get(5).getSubCateLogo()).into(mobilePower);
    }

    private void viewLeftRequest(View viewLeft, List<ClassifyBean.RespDataBean.SubCateArrBean> subCateArr) {
        TextView photographName = (TextView) viewLeft.findViewById(R.id.tv_photograph);
        ImageView photograph = (ImageView) viewLeft.findViewById(R.id.iv_photograph);
        TextView mp4Name = (TextView) viewLeft.findViewById(R.id.tv_mp4);
        ImageView mp4 = (ImageView) viewLeft.findViewById(R.id.iv_mp4);
        TextView earphoneName = (TextView) viewLeft.findViewById(R.id.tv_earphone);
        ImageView earphone = (ImageView) viewLeft.findViewById(R.id.iv_earphone);
        photographName.setText(subCateArr.get(0).getSubCateName());
        Picasso.with(context).load(subCateArr.get(0).getSubCateLogo()).into(photograph);
        mp4Name.setText(subCateArr.get(1).getSubCateName());
        Picasso.with(context).load(subCateArr.get(1).getSubCateLogo()).into(mp4);
        earphoneName.setText(subCateArr.get(2).getSubCateName());
        Picasso.with(context).load(subCateArr.get(2).getSubCateLogo()).into(earphone);
    }

    @Override
    public int getItemCount() {
        return mRespDataBeanList.size();
    }

    class PhoneViewHolder extends RecyclerView.ViewHolder {
        public final TextView phone_message;
        public final TextView phone_name;
        public final TextView mIPhone;
        public final TextView mMic;
        public final TextView mSanXing;
        public final TextView mHuawei;
        public final ImageView mPhoto;
        private final LinearLayout mLinear;


        public PhoneViewHolder(View itemView) {
            super(itemView);
            phone_message = (TextView) itemView.findViewById(R.id.tv_phone_message);
            phone_name = (TextView) itemView.findViewById(R.id.tv_phone_name);
            mIPhone = (TextView) itemView.findViewById(R.id.tv_iphone);
            mMic = (TextView) itemView.findViewById(R.id.tv_mic);
            mSanXing = (TextView) itemView.findViewById(R.id.tv_sanxing);
            mHuawei = (TextView) itemView.findViewById(R.id.tv_huawei);
            mPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            mLinear = (LinearLayout) itemView.findViewById(R.id.ll_phone_photo);

        }
    }

    class DigitViewHolder extends RecyclerView.ViewHolder {

        public final TextView digit_name;
        public final TextView digit_message;
        public final ViewPager viewPager;
        public final LinearLayout mDotsLayout;
        private final RelativeLayout mDigit;

        public DigitViewHolder(View itemView) {
            super(itemView);

            digit_name = (TextView) itemView.findViewById(R.id.tv_digit_name);
            digit_message = (TextView) itemView.findViewById(R.id.tv_digit_message);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager_digit);
            mDotsLayout = (LinearLayout) itemView.findViewById(R.id.ll_viewpager);
            mDigit = (RelativeLayout) itemView.findViewById(R.id.relative_digit);
        }
    }

    class ComputerViewHolder extends RecyclerView.ViewHolder {

        public final TextView computerMessage;
        public final TextView computerTitle;
        public final GridView mGridView;

        public ComputerViewHolder(View itemView) {
            super(itemView);
            computerMessage = (TextView) itemView.findViewById(R.id.tv_gview_message);
            computerTitle = (TextView) itemView.findViewById(R.id.tv_gview_name);
            mGridView = (GridView) itemView.findViewById(R.id.gridView);
        }
    }

    class BabyViewHolder extends RecyclerView.ViewHolder {

        public final TextView babyMessage;
        public final TextView babyName;
        public final GridView mGridView;

        public BabyViewHolder(View itemView) {
            super(itemView);
            babyMessage = (TextView) itemView.findViewById(R.id.tv_baby_message);
            babyName = (TextView) itemView.findViewById(R.id.tv_baby_name);
            mGridView = (GridView) itemView.findViewById(R.id.gridView_electrical);
        }
    }

    class EquipmentViewHolder extends RecyclerView.ViewHolder {

        public final TextView equipmentMessage;
        public final TextView equipmentName;
        public final TextView bike;
        public final TextView electric;
        public final TextView motor;
        public final ImageView mBike;
        public final ImageView mElectric;
        public final ImageView mMotor;

        public EquipmentViewHolder(View itemView) {
            super(itemView);

            equipmentMessage = (TextView) itemView.findViewById(R.id.tv_equipment_message);
            equipmentName = (TextView) itemView.findViewById(R.id.tv_equipment_name);
            bike = (TextView) itemView.findViewById(R.id.tv_equipment_bike);
            electric = (TextView) itemView.findViewById(R.id.tv_equipment_electric);
            motor = (TextView) itemView.findViewById(R.id.tv_equipment_motor);
            mBike = (ImageView) itemView.findViewById(R.id.iv_equipment_bike);
            mElectric = (ImageView) itemView.findViewById(R.id.iv_equipment_electric);
            mMotor = (ImageView) itemView.findViewById(R.id.iv_equipment_motor);
        }
    }

    class ElectricalViewHolder extends RecyclerView.ViewHolder {
        public final TextView electricalMessage;
        public final TextView electricalTitle;
        public final GridView mGridView;

        public ElectricalViewHolder(View itemView) {
            super(itemView);
            electricalMessage = (TextView) itemView.findViewById(R.id.tv_electrical_message);
            electricalTitle = (TextView) itemView.findViewById(R.id.tv_electrical_name);
            mGridView = (GridView) itemView.findViewById(R.id.gridView_electrical);

        }
    }

    class FurnitureViewHolder extends RecyclerView.ViewHolder {
        public final TextView furnitureMessage;
        public final TextView furnitureTitle;
        public final GridView mGridView;

        public FurnitureViewHolder(View itemView) {
            super(itemView);
            furnitureMessage = (TextView) itemView.findViewById(R.id.tv_furniture_message);
            furnitureTitle = (TextView) itemView.findViewById(R.id.tv_furniture_name);
            mGridView = (GridView) itemView.findViewById(R.id.gridView_furniture);
        }
    }
}