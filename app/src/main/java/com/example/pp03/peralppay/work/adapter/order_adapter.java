package com.example.pp03.peralppay.work.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.utils.GlideUtils;
import com.example.pp03.peralppay.work.Bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp03 on 2017/12/1.
 */

public class order_adapter extends BaseAdapter{
    private ArrayList<MenuBean> alist = new ArrayList<>();
    private Context context;
    public order_adapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<MenuBean> list){
        this.alist.clear();
        this.alist.addAll(list);
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(context, R.layout.order_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.moneny = (TextView) convertView.findViewById(R.id.moneny);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.name.setText(alist.get(position).getRemark()+"x  "+alist.get(position).getName());
        holder.moneny.setText(alist.get(position).getMoneny()+"");
        return convertView;
    }
    class Holder{
        TextView name;
        TextView moneny;
    }
}
