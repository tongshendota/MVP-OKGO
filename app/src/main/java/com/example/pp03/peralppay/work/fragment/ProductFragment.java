package com.example.pp03.peralppay.work.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.login.LoginActivity;
import com.example.pp03.peralppay.utils.Url;
import com.example.pp03.peralppay.utils.view.HorizontalListView;
import com.example.pp03.peralppay.work.Bean.MenuBean;
import com.example.pp03.peralppay.work.MainActivity;
import com.example.pp03.peralppay.work.adapter.HorizontallListviewAdapter;
import com.example.pp03.peralppay.work.adapter.menu_adapter;
import com.example.pp03.peralppay.work.adapter.order_adapter;
import com.example.pp03.peralppay.work.presenter.IGetDataPresenterCompl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by pp03 on 2017/11/30.
 */

public class ProductFragment extends BaseFragment implements IProductView{

    ListView listView;
    IGetDataPresenterCompl iGetDataPresenterCompl;
    GridView grid_test;
    private Button btn;
      private menu_adapter menuAdapter;
      private order_adapter orderAdapter;
     private ArrayList<MenuBean> list;
    private List<String> Hor_list;
    private ArrayList<MenuBean> gridlist;
//    private ImageView clear;
    private HorizontalListView horizontall;
    private HorizontallListviewAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
          if(msg.what==1){
              menuAdapter = new menu_adapter(getContext());
              grid_test.setAdapter(menuAdapter);
              menuAdapter.setData(gridlist);
          }
          if(msg.what==2){
              adapter = new HorizontallListviewAdapter(getContext());
              horizontall.setAdapter(adapter);
              adapter.setData(Hor_list);
          }
          if(msg.what==3){
              list.clear();
              list.addAll(orderAdapter.getdata());
           if(btn!=null){
               Double sum=0.00;
               for(int i =0;i<list.size();i++){
                   sum=sum+list.get(i).getSummoneny();
               }
              btn.setText(sum+"");
           }
          }
          if(msg.what==4){
              list.clear();
              list.addAll(orderAdapter.getdata());
              if(btn!=null){
                  Double sum=0.00;
                  for(int i =0;i<list.size();i++){
                      sum=sum+list.get(i).getSummoneny();
                  }
                  btn.setText(sum+"");
              }
              orderAdapter.setData(list);
          }

        }
    };
    @Override
    public void initData() {
        list = new ArrayList<>();

        iGetDataPresenterCompl.getHoData("");
        iGetDataPresenterCompl.getData("");
        orderAdapter = new order_adapter(getContext(),handler);
        listView.setAdapter(orderAdapter);
        orderAdapter.setData(list);
        grid_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.size()<1){
                    list.add(gridlist.get(position));
                }else{
                for(int i =0;i<list.size();i++){
                    if(list.get(i).getName().equals(gridlist.get(position).getName())){
                        MenuBean menuBean = new MenuBean();
                        menuBean.setMoneny(gridlist.get(position).getMoneny());
                        menuBean.setImg(gridlist.get(position).getImg());
                        menuBean.setName(gridlist.get(position).getName());
                        menuBean.setSize(list.get(i).getSize()+gridlist.get(position).getSize());
                        menuBean.setRemark(gridlist.get(position).getRemark());
                        menuBean.setSummoneny(list.get(i).getSummoneny()+gridlist.get(position).getMoneny());
                        list.remove(i);
                        list.add(i,menuBean);
                        orderAdapter.setData(list);
                        Message message = new Message();
                        message.what=3;
                        handler.sendMessage(message);
                        return;
                    }
                }
                       list.add(gridlist.get(position));
                }
                orderAdapter.setData(list);

                Message message = new Message();
                message.what=3;
                handler.sendMessage(message);
            }
        });
        horizontall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iGetDataPresenterCompl.getData("");
            }
        });
//        clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list.clear();
//                orderAdapter.setData(list);
//            }
//        });
    }

    @Override
    public int getFragmentLayoutResousId() {
        return R.layout.productfragment;
    }

    @Override
    public void initUI() {
        btn = (Button)rootView.findViewById(R.id.btn);
        listView = (ListView)rootView.findViewById(R.id.list);
        grid_test = (GridView)rootView.findViewById(R.id.grid_test);
        horizontall = (HorizontalListView)rootView.findViewById(R.id.horizontall);
        iGetDataPresenterCompl = new IGetDataPresenterCompl(ProductFragment.this,getActivity());
    }
//   public String getdata(){
//       gridlist = new ArrayList<>();
//       Random random = new Random();
//       int a=random.nextInt(10);
//       for(int i =0;i<20;i++){
//           MenuBean  menuBean = new MenuBean();
//           menuBean.setRemark(1);
//           menuBean.setName("辣椒炒肉"+a);
//           menuBean.setImg("http%3A%2F%2Fali.xinshipu.cn%2F20120703%2Foriginal%2F1341323816178.jpg");
//           menuBean.setMoneny(20.00+a);
//           gridlist.add(menuBean);
//       }
//       return gridlist.toString();
//   }
//   public String gethordata(){
//
//       Hor_list = new ArrayList<>();
//       for(int i = 0;i<10;i++){
//           Random random = new Random();
//           int a=random.nextInt(10);
//           Hor_list.add("湘菜"+a);
//       }
//       return Hor_list.toString();
//   }
    @Override
    public void onClick(View v) {

    }

    public void refreshData(){

    }
    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 6;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }

    @Override
    public void onGetDataResult(Boolean result, int code, List<MenuBean> pare) {
        if (result) {
            Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_SHORT).show();
            gridlist = new ArrayList<>();
            gridlist.addAll(pare);
            Message message = new Message() ;
            message.what=1;
            handler.sendMessage(message);
        } else {
            Toast.makeText(getActivity(), "数据获取失败" + code, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetHoDataResult(Boolean result, int code, List<String> pare) {
        if (result) {
            Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_SHORT).show();
            Hor_list = new ArrayList<>();
            Hor_list.addAll(pare);
            Message message = new Message() ;
            message.what=2;
            handler.sendMessage(message);
        } else {
            Toast.makeText(getActivity(), "数据获取失败" + code, Toast.LENGTH_SHORT).show();
        }
    }
}
