package com.example.pp03.peralppay.work.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.utils.AmountView;
import com.example.pp03.peralppay.utils.GlideUtils;
import com.example.pp03.peralppay.utils.LogUtils;
import com.example.pp03.peralppay.work.Bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp03 on 2017/12/1.
 */

public class order_adapter extends BaseAdapter{
    private ArrayList<MenuBean> alist = new ArrayList<>();
    private Context context;
    private Handler handler;
    public order_adapter(Context context, Handler handler){
        this.context = context;
        this.handler = handler;
    }
    public order_adapter(Context context){
        this.context = context;

    }

    public void setData(ArrayList<MenuBean> list){
        this.alist.clear();
        this.alist.addAll(list);
        notifyDataSetChanged();
    }
    public ArrayList<MenuBean> getdata(){
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
            convertView = View.inflate(context, R.layout.order_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.moneny = (TextView) convertView.findViewById(R.id.moneny);
            holder.size = (TextView)convertView.findViewById(R.id.size);
            holder.summoneny = (TextView)convertView.findViewById(R.id.summoneny);
//            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.remak = (TextView) convertView.findViewById(R.id.remak);
//            holder.amountView = (AmountView)convertView.findViewById(R.id.amount_view);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
//         holder.amountView.setGoods_storage(50);
//        holder.amountView.setAmount(alist.get(position).getSize());
        holder.name.setText(alist.get(position).getName());
        holder.remak.setText(alist.get(position).getRemark());
        holder.moneny.setText("$"+alist.get(position).getMoneny()+"");
        holder.summoneny.setText("$"+alist.get(position).getSummoneny());
        holder.size.setText("×"+alist.get(position).getSize());
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
        TextView name;
        TextView moneny;
//        ImageView img;
        TextView remak;
        TextView summoneny;
        TextView size;
//        AmountView amountView;
    }
}
