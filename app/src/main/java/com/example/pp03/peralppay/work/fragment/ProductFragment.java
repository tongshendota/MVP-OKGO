package com.example.pp03.peralppay.work.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.login.LoginActivity;
import com.example.pp03.peralppay.utils.ToastUtil;
import com.example.pp03.peralppay.utils.Url;
import com.example.pp03.peralppay.utils.view.HorizontalListView;
import com.example.pp03.peralppay.work.Bean.MenuBean;
import com.example.pp03.peralppay.work.MainActivity;
import com.example.pp03.peralppay.work.Pay_Order_Activity;
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
    private TextView btn;
    private ImageView setup;
      private menu_adapter menuAdapter;
      private order_adapter orderAdapter;
     private ArrayList<MenuBean> list;
    private List<String> Hor_list;
    private ArrayList<MenuBean> gridlist;
    private LinearLayout btn_Linear;
//    private View layout;
//    private PopupWindow pop;
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
              btn.setText("$"+sum+"");
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
                  btn.setText("$"+sum+"");
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
    }

    @Override
    public int getFragmentLayoutResousId() {
        return R.layout.productfragment;
    }

    @Override
    public void initUI() {
        setup = (ImageView)rootView.findViewById(R.id.setup);
        btn = (TextView) rootView.findViewById(R.id.moneny);
        listView = (ListView)rootView.findViewById(R.id.list);
        btn_Linear = (LinearLayout)rootView.findViewById(R.id.btn_Linear);
        btn_Linear.setOnClickListener(this);
        setup.setOnClickListener(this);
        grid_test = (GridView)rootView.findViewById(R.id.grid_test);
        horizontall = (HorizontalListView)rootView.findViewById(R.id.horizontall);
        iGetDataPresenterCompl = new IGetDataPresenterCompl(ProductFragment.this,getActivity());
        IntentFilter filter = new IntentFilter("order");
        getActivity().registerReceiver(broadcastreceiver, filter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Linear:
                showPasswordSetDailog();
                break;
            case R.id.btn_0:
                add("0");
                changeAnim(v,1);
                break;
            case R.id.btn_1:
                add("1");
                changeAnim(v,1);
                break;
            case R.id.btn_2:
                add("2");
                changeAnim(v,1);
                break;
            case R.id.btn_3:
                add("3");
                changeAnim(v,1);
                break;
            case R.id.btn_4:
                add("4");
                changeAnim(v,1);
                break;
            case R.id.btn_5:
                add("5");
                changeAnim(v,1);
                break;
            case R.id.btn_6:
                add("6");
                changeAnim(v,1);
                break;
            case R.id.btn_7:
                add("7");
                changeAnim(v,1);
                break;
            case R.id.btn_8:
                add("8");
                changeAnim(v,1);
                break;
            case R.id.btn_9:
                add("9");
                changeAnim(v,1);
                break;
            case R.id.btn_set:
                set();
                changeAnim(v,1);
                break;
            case R.id.btn_commit:
                commit();
                break;
            case R.id.setup:
                ToastUtil.showCustomToast("This id set UP");
            break;
        }
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
    private int state;
    TextView number;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn_set,btn_commit;
    ImageView black;

    private void showPasswordSetDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
           dialog.setCancelable(false);
        View view = View.inflate(getContext(), R.layout.pop_number, null);
        // dialog.setView(view);// 将自定义的布局文件设置给dialog
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
        btn0 = (Button)view.findViewById(R.id.btn_0);
        btn1 = (Button)view.findViewById(R.id.btn_1);
        btn2 = (Button)view.findViewById(R.id.btn_2);
        btn3 = (Button)view.findViewById(R.id.btn_3);
        btn4 = (Button)view.findViewById(R.id.btn_4);
        btn5 = (Button)view.findViewById(R.id.btn_5);
        btn6 = (Button)view.findViewById(R.id.btn_6);
        btn7 = (Button)view.findViewById(R.id.btn_7);
        btn8 = (Button)view.findViewById(R.id.btn_8);
        btn9 = (Button)view.findViewById(R.id.btn_9);
        btn_set = (Button)view.findViewById(R.id.btn_set);
        btn_commit = (Button)view.findViewById(R.id.btn_commit);
        number = (TextView)view.findViewById(R.id.sumnumber);
        black = (ImageView)view.findViewById(R.id.black);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_set.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private String sum="";
    private void add(String i){
        Log.e("pop=====",i);
        sum=sum+i;
        number.setText(sum);
    }
    private void set(){
        sum = "";
        number.setText(sum);
    }
    private void commit(){
        sum = "";
        Double summoeny=0.00;
        for(int i =0;i<list.size();i++){
            summoeny=summoeny +list.get(i).getSummoneny();
        }
    Intent intent = new Intent(getActivity(),Pay_Order_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", list);
        intent.putExtras(bundle);
        intent.putExtra("summoneny",summoeny+"");
        startActivity(intent);
    }
    public void changeAnim(final View view, final int count){
        final long during = 100;
        final float start = 1f;
        final float end = 1f;
        view.setBackgroundColor(getActivity().getResources().getColor(R.color.key_color));
        final ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "alpha", start,end);
        anim1.setDuration(during);// 动画持续时间
        anim1.addListener(new MyListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                final ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "alpha", start,end);
                anim2.setDuration(during);// 动画持续时间
                anim2.addListener(new MyListener(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (count<1) {
                            changeAnim(view, count + 1);
                        }
                    }
                });
                anim2.start();
            }
        });
        anim1.start();
    }
    class MyListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Broadcast","   ======");
            // TODO Auto-generated method stub
            list.clear();
            orderAdapter.setData(list);
        }
    };
}
