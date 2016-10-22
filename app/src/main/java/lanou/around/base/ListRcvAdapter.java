package lanou.around.base;


/**
 * Created by dllo on 16/10/22.
 */
//
//public class ListRcvAdapter extends BaseRcvAdapter<BaseViewHolder> {
//
//    public ListRcvAdapter(Context context, List<Object> mList) {
//        super(context, mList);
//    }
//
//    public ListRcvAdapter(Fragment fragment, List<Object> mList) {
//        super(fragment, mList);
//    }
//
//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mLayoutInflater.inflate(R.layout.item_list, parent, false);
//        return new BaseViewHolder(context, view);
//    }
//
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, final int position) {
//        super.onBindViewHolder(holder, position);
//        holder.setText(R.id.tv, mList.get(position).toString())
//                .setText(R.id.delete, "");
//
//        编辑模式
//        holder.views.get(R.id.delete).setVisibility(isEdit ? View.VISIBLE : View.GONE);
//        点击事件
//        holder.views.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
//}
