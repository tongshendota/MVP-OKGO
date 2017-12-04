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

/**
 * Created by pp03 on 2017/12/1.
 */

public class menu_adapter extends BaseAdapter{
    private ArrayList<MenuBean> list = new ArrayList<>();
    private Context context;
    public menu_adapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<MenuBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            convertView = View.inflate(context, R.layout.menu_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        GlideUtils.showImageView(context,R.mipmap.xx,list.get(position).getImg(),holder.img);
        holder.name.setText(list.get(position).getName());
        return convertView;
    }
    class Holder{
        TextView name;
        ImageView img;
    }
}
