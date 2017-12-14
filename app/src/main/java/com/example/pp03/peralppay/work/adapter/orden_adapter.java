package com.example.pp03.peralppay.work.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.work.Bean.MenuBean;
import com.example.pp03.peralppay.work.Bean.OrdenBean;

import java.util.ArrayList;

/**
 * Created by pp03 on 2017/12/1.
 */

public class orden_adapter extends BaseAdapter{
    private ArrayList<OrdenBean> alist = new ArrayList<>();
    private Context context;
    private Handler handler;
    public orden_adapter(Context context, Handler handler){
        this.context = context;
        this.handler = handler;
    }
    public orden_adapter(Context context){
        this.context = context;

    }

    public void setData(ArrayList<OrdenBean> list){
        this.alist.clear();
        this.alist.addAll(list);
        notifyDataSetChanged();
    }
    public ArrayList<OrdenBean> getdata(){
        return  alist;
    }

    @Override
    public int getCount() {
        return alist.size();
    }

    @Override
    public Object getItem(int position) {
        return alist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      final   Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(context, R.layout.orden_item,null);
            holder.sum = (TextView) convertView.findViewById(R.id.sum);
            holder.model = (TextView) convertView.findViewById(R.id.mode);
            holder.size = (TextView)convertView.findViewById(R.id.size);
            holder.state = (TextView)convertView.findViewById(R.id.state);
            holder.time = (TextView)convertView.findViewById(R.id.time);
            holder.num = (TextView) convertView.findViewById(R.id.num);
//            holder.amountView = (AmountView)convertView.findViewById(R.id.amount_view);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
//         holder.amountView.setGoods_storage(50);
//        holder.amountView.setAmount(alist.get(position).getSize());
        holder.state.setText(alist.get(position).getState());
        holder.size.setText(alist.get(position).getSize());
        holder.sum.setText(alist.get(position).getSum());
        holder.time.setText(alist.get(position).getTime());
        holder.model.setText(alist.get(position).getModel());
        holder.num.setText(alist.get(position).getNum());
//        GlideUtils.showImageView(context,R.mipmap.xx,alist.get(position).getImg(),holder.img);
//        holder.amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
//
//            @Override
//            public void onAdd(View view, int amount) {
//                LogUtils.e("onAdd","=================");
//                alist.get(position).setSize(amount);
//                LogUtils.e("onAdd",alist.get(position).getMoneny()+"");
//                alist.get(position).setSummoneny(alist.get(position).getMoneny()*amount);
//                holder.moneny.setText(alist.get(position).getSummoneny()+"");
//                Message message = new Message();
//                message.what=3;
//                handler.sendMessage(message);
//            }
//
//            @Override
//            public void onDel(View view, int amount) {
//                LogUtils.e("onDel","=================");
//                if(amount==0){
//                    alist.remove(position);
//                    Message message = new Message();
//                    message.what=4;
//                    handler.sendMessage(message);
//                }else{
//                alist.get(position).setSize(amount);
//                LogUtils.e("onDel",Double.valueOf(holder.moneny.getText().toString())+"");
//                alist.get(position).setSummoneny(alist.get(position).getMoneny()*amount);
//                holder.moneny.setText(alist.get(position).getSummoneny()+"");
//                    Message message = new Message();
//                    message.what=3;
//                    handler.sendMessage(message);
//                }
//
//            }
//
//
//        });

        return convertView;
    }
    class Holder{
        TextView sum;
        TextView state;
//        ImageView img;
        TextView time;
        TextView num;
        TextView size;
        TextView model;
    }
}
