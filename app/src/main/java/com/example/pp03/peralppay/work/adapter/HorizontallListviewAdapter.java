package com.example.pp03.peralppay.work.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pp03.peralppay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp03 on 2017/12/1.
 */

public class HorizontallListviewAdapter extends BaseAdapter{
    private List<String> alist = new ArrayList<>();
    private Context context;
    public HorizontallListviewAdapter(Context context){
        this.context = context;
    }

    public void setData(List<String> list){
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
            convertView = View.inflate(context, R.layout.horzin_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.name.setText(alist.get(position));
        return convertView;
    }
    class Holder{
        TextView name;
    }
}
